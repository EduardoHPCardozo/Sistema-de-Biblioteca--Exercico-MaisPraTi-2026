package Biblioteca;

import Acervo.ItemBiblioteca;
import Usuario.Usuario;

public class Biblioteca {
    private ItemBiblioteca[] acervo;

    public Biblioteca(int tamanhoAcervo) {
        this.acervo = new ItemBiblioteca[tamanhoAcervo];
    }

    public void adicionarItem(ItemBiblioteca item) {
        for (int i = 0; i < acervo.length; i++) {
            if (acervo[i] == null) {
                acervo[i] = item;
                return;
            }
        }
        System.out.println("Acervo cheio.");
    }

    public void emprestar(ItemBiblioteca item, Usuario usuario) {
        if (!item.isDisponivel()) {
            System.out.println("Empréstimo recusado: item indisponível.");
            return;
        }

        if (!usuario.podeEmprestar()) {
            System.out.println("Empréstimo recusado: limite de itens atingido para " + usuario.getNome() + ".");
            return;
        }

        item.emprestar();
        usuario.registrarEmprestimo();
        System.out.println("Empréstimo realizado: " + item.getTitulo() + " para " + usuario.getNome() + ".");
    }

    public void devolver(ItemBiblioteca item, Usuario usuario) {
        if (item.isDisponivel()) {
            System.out.println("Devolução recusada: o item já está disponível.");
            return;
        }

        item.devolver();
        usuario.registrarDevolucao();
        System.out.println("Devolução realizada: " + item.getTitulo() + ".");
    }

    public void listarAcervo() {
        for (ItemBiblioteca item : acervo) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }
}
