package view;

import controller.CarrinhoController;
import model.Produto;
import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;

public class CarrinhoView {
    private CarrinhoController carrinhoController;

    public CarrinhoView(CarrinhoController carrinhoController) {
        this.carrinhoController = carrinhoController;
    }

    public void exibirCarrinho() {
        for (Produto produto : carrinhoController.listarProdutosNoCarrinho()) {
            System.out.println("Produto: " + produto.getTitulo() + ", Preço: " + produto.getPreco());
        }
    }

    public void adicionarProdutoAoCarrinho(String idProduto) {
        try {
            carrinhoController.adicionarProdutoAoCarrinho(idProduto);
            System.out.println("Produto adicionado ao carrinho com sucesso!");
        } catch (ProdutoNaoEncontradoException | EstoqueInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }
}