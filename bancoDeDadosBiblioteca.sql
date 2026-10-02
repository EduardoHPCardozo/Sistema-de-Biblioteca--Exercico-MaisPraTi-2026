-- EXERCÍCIO 2 - MODELAGEM DO BANCO DE DADOS

DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS usuario;

CREATE TABLE item (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(100) UNIQUE NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('Livro', 'Revista')),
    autor VARCHAR(100) NOT NULL,
    edicao VARCHAR(100) NOT NULL,
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('Aluno', 'Professor')),
    limite_itens INT NOT NULL
);

CREATE TABLE emprestimo (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INT NOT NULL REFERENCES item(id),
    usuario_id INT NOT NULL REFERENCES usuario(id),
    data_retirada DATE NOT NULL DEFAULT CURRENT_DATE,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao DATE,
    valor_multa NUMERIC(10, 2) NOT NULL DEFAULT 0.00
);

-- Dados de teste
INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel)
VALUES
    ('101', 'O Hobbit', 'Livro', 'J.R.R. Tolkien', 'HarperCollins', FALSE),
    ('102', 'Harry Potter', 'Livro', 'J.K. Rowling', 'Rocco', TRUE),
    ('103', 'O Chamado de Cthulhu', 'Livro', 'H.P. Lovecraft', 'DarkSide', TRUE),
    ('201', 'Batman', 'Revista', 'DC Comics', 'EBAL', TRUE),
    ('202', 'Turma da Mônica', 'Revista', 'Mauricio de Sousa', 'Abril', TRUE);

INSERT INTO usuario (nome, tipo, limite_itens)
VALUES
    ('Frodo Bolseiro', 'Aluno', 3),
    ('Gandalf, o Branco', 'Professor', 5);

-- Um empréstimo em aberto e um já devolvido
INSERT INTO emprestimo
    (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES
    (1, 1, '2026-09-20', '2026-10-04', NULL, 0.00),
    (4, 2, '2026-09-01', '2026-09-08', '2026-09-10', 2.00);

-- EXERCÍCIO 3 - CONSULTAS SQL

-- 1. Listar todo o acervo, com código, título, tipo e disponibilidade.
SELECT codigo, titulo, tipo, disponivel
FROM item;

-- 2. Listar os empréstimos em aberto, com o nome do usuário e o título do item.
SELECT u.nome, i.titulo
FROM emprestimo e
JOIN usuario u ON e.usuario_id = u.id
JOIN item i ON e.item_id = i.id
WHERE e.data_devolucao IS NULL;

-- 3. Calcular o total de multas acumuladas por usuário.
SELECT u.nome, COALESCE(SUM(e.valor_multa), 0) AS total_multas
FROM usuario u
LEFT JOIN emprestimo e ON u.id = e.usuario_id
GROUP BY u.id, u.nome;

-- 4. Listar os itens que nunca foram emprestados.
SELECT i.codigo, i.titulo
FROM item i
LEFT JOIN emprestimo e ON i.id = e.item_id
WHERE e.id IS NULL;
