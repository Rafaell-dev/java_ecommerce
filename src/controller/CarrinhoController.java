package controller;

import model.Carrinho;
import model.Produto;
import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;

import java.util.List;

public class CarrinhoController {
    private Carrinho carrinho;
    private ProdutoController produtoController;

    public CarrinhoController(ProdutoController produtoController) {
        this.carrinho = new Carrinho();
        this.produtoController = produtoController;
    }

    public void adicionarProdutoAoCarrinho(String idProduto) throws ProdutoNaoEncontradoException, EstoqueInsuficienteException {
        Produto produto = produtoController.buscarProdutoPorId(idProduto);
        if (produto.getEstoque() <= 0) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto " + produto.getTitulo());
        }
        carrinho.adicionarProduto(produto);
    }

    public List<Produto> listarProdutosNoCarrinho() {
        return carrinho.getProdutos();
    }

    public void limparCarrinho() {
        carrinho.limparCarrinho();
    }
}