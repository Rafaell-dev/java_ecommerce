package view;

import controller.ProdutoController;
import model.Produto;

import java.util.List;

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

    public void exibirResultadosPesquisa(List<Produto> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            System.out.println("---------- Resultados da Pesquisa ----------");
            for (Produto produto : resultados) {
                System.out.println("ID: " + produto.getId() + ", Título: " + produto.getTitulo() + ", Categoria: " + produto.getCategoria() + ", Preço: " + produto.getPreco());
            }
        }
    }
}