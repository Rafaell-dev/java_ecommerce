package controller;

import model.*;
import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;

import java.util.List;
import java.util.UUID;

public class CarrinhoController {
    private Carrinho carrinho;
    private ProdutoController produtoController;

    public CarrinhoController(ProdutoController produtoController) {
        this.carrinho = new Carrinho();
        this.produtoController = produtoController;
    }

    public Pedido finalizarCompra(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) throws EstoqueInsuficienteException {
        List<Produto> produtos = carrinho.getProdutos();

        for (Produto produto : produtos) {
            if (produto.getEstoque() <= 0) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto " + produto.getTitulo());
            }
        }

        String idPedido = UUID.randomUUID().toString();
        Pedido pedido = new Pedido(idPedido, usuario, produtos, formaPagamento, enderecoEntrega);

        carrinho.limparCarrinho();

        return pedido;
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