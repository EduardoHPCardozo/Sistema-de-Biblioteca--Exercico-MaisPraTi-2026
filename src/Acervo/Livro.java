package Acervo;

public class Livro extends ItemBiblioteca {
    public Livro(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int prazo() {
        return 14;
    }

    @Override
    public double multa() {
        return 0.50;
    }
}
