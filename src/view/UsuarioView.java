package view;

import controller.UsuarioController;
import model.Usuario;
import exception.UsuarioNaoEncontradoException;

import java.util.Scanner;

public class UsuarioView {
    private UsuarioController usuarioController;

    public UsuarioView(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public Usuario fazerLogin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("---------- Login ----------");
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        try {
            Usuario usuario = usuarioController.autenticarUsuario(email, senha);
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getNome() + "!");
            return usuario;
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return null;
        }
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