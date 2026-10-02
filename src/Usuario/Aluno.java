package Usuario;

public class Aluno extends Usuario {
    public Aluno(String nome) {
        super(nome);
    }

    @Override
    public int limiteDeItens() {
        return 3;
    }
}
