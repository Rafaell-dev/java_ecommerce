package controller;

import model.Produto;
import exception.ProdutoNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private List<Produto> produtos;

    public ProdutoController() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProdutoPorId(String id) throws ProdutoNaoEncontradoException {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado.");
    }

    public void atualizarEstoque(String id, int estoque) throws ProdutoNaoEncontradoException {
        Produto produto = buscarProdutoPorId(id);
        produto.setEstoque(estoque);
    }
}