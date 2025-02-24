import controller.*;
import model.*;
import view.*;
import exception.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicializando controladores
        ProdutoController produtoController = new ProdutoController();
        UsuarioController usuarioController = new UsuarioController();
        CarrinhoController carrinhoController = new CarrinhoController(produtoController);
        AdminController adminController = new AdminController(produtoController, usuarioController);

        // Inicializando views
        ProdutoView produtoView = new ProdutoView(produtoController);
        UsuarioView usuarioView = new UsuarioView(usuarioController);
        CarrinhoView carrinhoView = new CarrinhoView(carrinhoController);
        AdminView adminView = new AdminView(usuarioController, adminController);

        produtoController.adicionarProduto(new Produto("1", "Carregador Baseus 2 Apple ", "Geral", 50.0, 50));
        produtoController.adicionarProduto(new Produto("2", "Carregador Baseus Apple", "Geral", 50.0, 50));
        produtoController.adicionarProduto(new Produto("3", "Combo Essager Carregamento Rápido", "Geral", 60.0, 50));
        produtoController.adicionarProduto(new Produto("4", "Fone com Fio Baseus HZ20", "Geral", 15.0, 50));
        produtoController.adicionarProduto(new Produto("5", "Cabo Baseus Lightning 20W", "Geral", 40.0, 50));

        usuarioController.adicionarUsuario(new Usuario("1", "Filipe", "fbt@discente.ifpe.edu.br ", "123456", "COMUM"));
        usuarioController.adicionarUsuario(new Usuario("2", "Rafael", "rnva@discente.ifpe.edu.br", "1234", "ADMIN"));

        Usuario usuarioAutenticado = null;
        int escolha;
        do {
            System.out.print("----------E-Commerce----------\n" +
                    "1 = Criar usuário\n" +
                    "2 = Listar produtos\n" +
                    "3 = Adicionar produtos ao carrinho\n" +
                    "4 = Exibir carrinho\n" +
                    "5 = Finalizar compra\n" +
                    "6 = Login\n" +
                    "7 = Pesquisar produto\n"+
                    "8 = Administração\n" +
                    "0 = Sair \n" +
                    "Escolha uma opção: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                sc.next();
            }
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    // Cadastro de usuário
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Tipo (COMUM/ADMIN): ");
                    String tipo = sc.nextLine();
                    String idUsuario = UUID.randomUUID().toString();
                    Usuario usuario = new Usuario(idUsuario, nome, email, senha, tipo);
                    usuarioController.adicionarUsuario(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    // Listar produtos
                    produtoView.exibirProdutos();
                    break;

                case 3:
                    // Adicionar produto ao carrinho
                    produtoView.exibirProdutos();
                    System.out.print("ID do Produto: ");
                    String idProduto = sc.nextLine();
                    try {
                        carrinhoView.adicionarProdutoAoCarrinho(idProduto);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    // Exibir carrinho
                    carrinhoView.exibirCarrinho();
                    break;

                case 5:
                    // Finalizar compra
                    if (usuarioAutenticado == null) {
                        System.out.println("Você precisa estar logado para finalizar a compra.");
                        usuarioAutenticado = usuarioView.fazerLogin();
                    } else {
                        carrinhoView.finalizarCompra(usuarioAutenticado);
                    }
                    break;

                case 6:
                    // Login
                    usuarioAutenticado = usuarioView.fazerLogin();
                    break;

                case 7:
                    // Pesquisar produtos
                    System.out.print("Digite o termo de pesquisa (nome, categoria ou descrição): ");
                    String termoPesquisa = sc.nextLine();
                    List<Produto> resultados = produtoController.pesquisarProdutos(termoPesquisa);
                    produtoView.exibirResultadosPesquisa(resultados);
                    break;

                case 8:
                    // Área de administração
                    adminView.exibirMenuAdministracao(usuarioAutenticado);
                    break;

                case 0:
                    System.out.println("Encerrando o E-Commerce, até mais!!!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
        sc.close();
    }
}