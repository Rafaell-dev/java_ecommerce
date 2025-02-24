package controller;

import model.Administrador;
import model.Produto;
import model.Usuario;
import exception.UsuarioNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private List<Administrador> administradores;
    private ProdutoController produtoController;
    private UsuarioController usuarioController;

    public AdminController(ProdutoController produtoController, UsuarioController usuarioController) {
        this.administradores = new ArrayList<>();
        this.produtoController = produtoController;
        this.usuarioController = usuarioController;
    }

    public void adicionarProduto(Produto produto) {
        produtoController.adicionarProduto(produto);
    }

    public void atualizarEstoque(String idProduto, int novoEstoque) throws Exception {
        produtoController.atualizarEstoque(idProduto, novoEstoque);
    }

    public void atualizarSenhaUsuario(String idUsuario, String novaSenha) throws Exception {
        usuarioController.atualizarSenha(idUsuario, novaSenha);
    }

    public void cadastrarAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }

    public List<Administrador> listarAdministradores() {
        return administradores;
    }

    public void atualizarAdministrador(String id, String novoNome, String novoEmail, String novaSenha) throws UsuarioNaoEncontradoException {
        Administrador administrador = buscarAdministradorPorId(id);
        administrador.setNome(novoNome);
        administrador.setEmail(novoEmail);
        administrador.setSenha(novaSenha);
    }

    public void removerAdministrador(String id) throws UsuarioNaoEncontradoException {
        Administrador administrador = buscarAdministradorPorId(id);
        administradores.remove(administrador);
    }

    private Administrador buscarAdministradorPorId(String id) throws UsuarioNaoEncontradoException {
        for (Administrador administrador : administradores) {
            if (administrador.getId().equals(id)) {
                return administrador;
            }
        }
        throw new UsuarioNaoEncontradoException("Administrador com ID " + id + " não encontrado.");
    }
}