package view;

import controller.AdminController;
import controller.UsuarioController;
import model.Administrador;
import model.Produto;
import exception.UsuarioNaoEncontradoException;
import model.Usuario;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminView {
    private AdminController adminController;
    private UsuarioController usuarioController;

    public AdminView(UsuarioController usuarioController, AdminController adminController) {
        this.usuarioController = usuarioController;
        this.adminController = adminController;
    }

    public void exibirMenuAdministracao(Usuario usuarioAutenticado) {
        if (usuarioAutenticado == null || !usuarioAutenticado.getTipo().equals("ADMIN")) {
            System.out.println("Acesso negado! Você precisa estar logado como administrador para acessar esta área.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int escolha;

        do {
            System.out.print("----------Área Administração----------\n" +
                    "1 = Gerenciar produtos\n" +
                    "2 = Gerenciar usuários\n" +
                    "3 = Gerenciar administradores\n" + // Nova opção
                    "0 = Voltar ao menu principal\n" +
                    "Escolha uma opção: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                sc.next();
            }
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    // Gerenciar produtos
                    exibirMenuProdutos();
                    break;

                case 2:
                    // Gerenciar usuários
                    exibirMenuUsuarios();
                    break;

                case 3:
                    // Gerenciar administradores
                    exibirMenuAdministradores();
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    private void exibirMenuProdutos() {
        Scanner sc = new Scanner(System.in);
        int escolha;

        do {
            System.out.print("---------- Gerenciamento de Produtos ----------\n" +
                    "1 = Adicionar produto\n" +
                    "2 = Atualizar estoque\n" +
                    "0 = Voltar ao menu de administração\n" +
                    "Escolha uma opção: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                sc.next();
            }
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    // Adicionar produto
                    System.out.print("Título do Produto: ");
                    String titulo = sc.nextLine();
                    System.out.print("Preço: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Entrada inválida! Digite um valor numérico para o preço.");
                        sc.next();
                    }
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();
                    String idProduto = UUID.randomUUID().toString();
                    System.out.print("Estoque: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Entrada inválida! Digite um valor inteiro para o estoque.");
                        sc.next();
                    }
                    int estoque = sc.nextInt();
                    sc.nextLine();
                    Produto novoProduto = new Produto(idProduto, titulo, categoria, preco, estoque);
                    adminController.adicionarProduto(novoProduto);
                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                    // Atualizar estoque
                    System.out.print("ID do Produto: ");
                    String prodId = sc.nextLine();
                    System.out.print("Novo Estoque: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Entrada inválida! Digite um valor inteiro para o estoque.");
                        sc.next();
                    }
                    int novoEstoque = sc.nextInt();
                    sc.nextLine();
                    try {
                        adminController.atualizarEstoque(prodId, novoEstoque);
                        System.out.println("Estoque atualizado com sucesso!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu de administração...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    public void exibirMenuUsuarios() {
        Scanner sc = new Scanner(System.in);
        int escolha;

        do {
            System.out.print("---------- Gerenciamento de Usuários ----------\n" +
                    "1 = Cadastrar usuário\n" +
                    "2 = Listar usuários\n" +
                    "3 = Atualizar usuário\n" +
                    "4 = Remover usuário\n" +
                    "0 = Voltar ao menu de administração\n" +
                    "Escolha uma opção: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                sc.next();
            }
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    // Cadastrar usuário
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Tipo (COMUM/ADMIN): ");
                    String tipo = sc.nextLine();
                    String id = UUID.randomUUID().toString();
                    Usuario usuario = new Usuario(id, nome, email, senha, tipo);
                    usuarioController.adicionarUsuario(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    // Listar usuários
                    List<Usuario> usuarios = usuarioController.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("---------- Lista de Usuários ----------");
                        for (Usuario u : usuarios) {
                            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Email: " + u.getEmail() + ", Tipo: " + u.getTipo());
                        }
                    }
                    break;

                case 3:
                    // Atualizar usuário
                    System.out.print("ID do Usuário: ");
                    String idAtualizar = sc.nextLine();
                    System.out.print("Novo Nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo Email: ");
                    String novoEmail = sc.nextLine();
                    System.out.print("Nova Senha: ");
                    String novaSenha = sc.nextLine();
                    System.out.print("Novo Tipo (COMUM/ADMIN): ");
                    String novoTipo = sc.nextLine();
                    try {
                        usuarioController.atualizarUsuario(idAtualizar, novoNome, novoEmail, novaSenha, novoTipo);
                        System.out.println("Usuário atualizado com sucesso!");
                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    // Remover usuário
                    System.out.print("ID do Usuário: ");
                    String idRemover = sc.nextLine();
                    try {
                        usuarioController.removerUsuario(idRemover);
                        System.out.println("Usuário removido com sucesso!");
                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu de administração...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    private void exibirMenuAdministradores() {
        Scanner sc = new Scanner(System.in);
        int escolha;

        do {
            System.out.print("---------- Gerenciamento de Administradores ----------\n" +
                    "1 = Cadastrar administrador\n" +
                    "2 = Listar administradores\n" +
                    "3 = Atualizar administrador\n" +
                    "4 = Remover administrador\n" +
                    "0 = Voltar ao menu de administração\n" +
                    "Escolha uma opção: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                sc.next();
            }
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    // Cadastrar administrador
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    String id = UUID.randomUUID().toString();
                    String tipo = "ADMIN";
                    Administrador administrador = new Administrador(id, nome, email, senha, tipo);
                    adminController.cadastrarAdministrador(administrador);
                    System.out.println("Administrador cadastrado com sucesso!");
                    break;

                case 2:
                    // Listar administradores
                    List<Administrador> administradores = adminController.listarAdministradores();
                    if (administradores.isEmpty()) {
                        System.out.println("Nenhum administrador cadastrado.");
                    } else {
                        System.out.println("---------- Lista de Administradores ----------");
                        for (Administrador adm : administradores) {
                           if (adm.getTipo().equalsIgnoreCase("ADMIN")){
                               System.out.println("ID: " + adm.getId() + ", Nome: " + adm.getNome() + ", Email: " + adm.getEmail());
                           }
                        }
                    }
                    break;

                case 3:
                    // Atualizar administrador
                    System.out.print("ID do Administrador: ");
                    String idAtualizar = sc.nextLine();
                    System.out.print("Novo Nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo Email: ");
                    String novoEmail = sc.nextLine();
                    System.out.print("Nova Senha: ");
                    String novaSenha = sc.nextLine();
                    try {
                        adminController.atualizarAdministrador(idAtualizar, novoNome, novoEmail, novaSenha);
                        System.out.println("Administrador atualizado com sucesso!");
                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    // Remover administrador
                    System.out.print("ID do Administrador: ");
                    String idRemover = sc.nextLine();
                    try {
                        adminController.removerAdministrador(idRemover);
                        System.out.println("Administrador removido com sucesso!");
                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu de administração...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }
}