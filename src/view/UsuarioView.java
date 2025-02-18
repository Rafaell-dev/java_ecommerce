package view;

import controller.UsuarioController;
import model.Usuario;
import exception.UsuarioNaoEncontradoException;

public class UsuarioView {
    private UsuarioController usuarioController;

    public UsuarioView(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void exibirUsuario(String id) {
        try {
            Usuario usuario = usuarioController.buscarUsuarioPorId(id);
            System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail());
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}