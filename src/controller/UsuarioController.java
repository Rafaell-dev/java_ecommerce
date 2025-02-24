package controller;

import model.Usuario;
import exception.UsuarioNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios;

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }


    public void atualizarUsuario(String id, String novoNome, String novoEmail, String novaSenha, String novoTipo) throws UsuarioNaoEncontradoException {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setNome(novoNome);
        usuario.setEmail(novoEmail);
        usuario.setSenha(novaSenha);
        usuario.setTipo(novoTipo);
    }

    public void removerUsuario(String id) throws UsuarioNaoEncontradoException {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarios.remove(usuario);
    }

    public Usuario autenticarUsuario(String email, String senha) throws UsuarioNaoEncontradoException {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.autenticar(senha)) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException("E-mail ou senha incorretos.");
    }

    public Usuario buscarUsuarioPorId(String id) throws UsuarioNaoEncontradoException {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado.");
    }

    public void atualizarSenha(String id, String novaSenha) throws UsuarioNaoEncontradoException {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setSenha(novaSenha);
    }
}