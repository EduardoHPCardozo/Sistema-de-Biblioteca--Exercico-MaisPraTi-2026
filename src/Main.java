import Acervo.Livro;
import Acervo.Revista;
import Biblioteca.Biblioteca;
import Usuario.Aluno;
import Usuario.Professor;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(6);

        Livro oHobbit = new Livro("101", "O Hobbit");
        Livro harryPotter = new Livro("102", "Harry Potter");
        Livro cthulhu = new Livro("103", "O Chamado de Cthulhu");
        Revista batman = new Revista("201", "Batman");
        Revista turmaDaMonica = new Revista("202", "Turma da Mônica");

        biblioteca.adicionarItem(oHobbit);
        biblioteca.adicionarItem(harryPotter);
        biblioteca.adicionarItem(cthulhu);
        biblioteca.adicionarItem(batman);
        biblioteca.adicionarItem(turmaDaMonica);

        Aluno aluno = new Aluno("Frodo Bolseiro");
        Professor professor = new Professor("Gandalf, o Branco");

        System.out.println("ACERVO INICIAL:");
        biblioteca.listarAcervo();

        System.out.println("\nTESTE DE EMPRÉSTIMO:");
        biblioteca.emprestar(oHobbit, aluno);
        biblioteca.emprestar(harryPotter, aluno);
        biblioteca.emprestar(cthulhu, aluno);
        biblioteca.emprestar(batman, aluno);

        System.out.println("\nTESTE COM PROFESSOR:");
        biblioteca.emprestar(turmaDaMonica, professor);

        System.out.println("\nACERVO APÓS OS EMPRÉSTIMOS:");
        biblioteca.listarAcervo();

        System.out.println("\nTESTE DE DEVOLUÇÃO:");
        biblioteca.devolver(oHobbit, aluno);
    }
}
