package view;

import controller.ProdutoController;
import model.Produto;

public class ProdutoView {
    private ProdutoController produtoController;

    public ProdutoView(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    public void exibirProdutos() {
        for (Produto produto : produtoController.listarProdutos()) {
            System.out.println("ID: " + produto.getId() + ", Título: " + produto.getTitulo() + ", Preço: " + produto.getPreco());
        }
    }
}