package Usuario;

public class Professor extends Usuario {
    public Professor(String nome) {
        super(nome);
    }

    @Override
    public int limiteDeItens() {
        return 5;
    }
}
