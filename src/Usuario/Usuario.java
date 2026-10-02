package Usuario;

public abstract class Usuario {
    private String nome;
    private int quantidadeEmprestada;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    public abstract int limiteDeItens();

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public boolean podeEmprestar() {
        return quantidadeEmprestada < limiteDeItens();
    }

    public void registrarEmprestimo() {
        quantidadeEmprestada++;
    }

    public void registrarDevolucao() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }
}
