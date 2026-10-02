package Acervo;

public class Revista extends ItemBiblioteca {
    public Revista(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int prazo() {
        return 7;
    }

    @Override
    public double multa() {
        return 1.00;
    }
}
