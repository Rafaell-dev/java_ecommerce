package controller;

import model.Produto;
import model.Usuario;


public class AdminController {
    private ProdutoController produtoController;
    private UsuarioController usuarioController;

    public AdminController(ProdutoController produtoController, UsuarioController usuarioController) {
        this.produtoController = produtoController;
        this.usuarioController = usuarioController;
    }

    public void adicionarProduto(Produto produto) {
        produtoController.adicionarProduto(produto);
    }

    public void atualizarEstoque(String idProduto, int estoque) throws Exception {
        produtoController.atualizarEstoque(idProduto, estoque);
    }

    public void atualizarSenhaUsuario(String idUsuario, String novaSenha) throws Exception {
        usuarioController.atualizarSenha(idUsuario, novaSenha);
    }
}