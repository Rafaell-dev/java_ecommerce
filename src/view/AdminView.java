package view;

import controller.AdminController;
import model.Produto;
import exception.ProdutoNaoEncontradoException;
import exception.UsuarioNaoEncontradoException;

public class AdminView {
    private AdminController adminController;

    public AdminView(AdminController adminController) {
        this.adminController = adminController;
    }

    public void exibirOpcoesAdmin() {
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Atualizar Estoque");
        System.out.println("3. Atualizar Senha do Usuário");
    }

    public void adicionarProduto(Produto produto) {
        adminController.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void atualizarEstoque(String idProduto, int estoque) {
        try {
            adminController.atualizarEstoque(idProduto, estoque);
            System.out.println("Estoque atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarSenhaUsuario(String idUsuario, String novaSenha) {
        try {
            adminController.atualizarSenhaUsuario(idUsuario, novaSenha);
            System.out.println("Senha atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}