import controller.*;
import model.*;
import view.*;
import exception.*;
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
        AdminView adminView = new AdminView(adminController);

        produtoController.adicionarProduto(new Produto("1", "Cabo USB", "Cabos", 10.0, 100));
        produtoController.adicionarProduto(new Produto("2", "Carregador", "Carregadores", 20.0, 50));


        usuarioController.adicionarUsuario(new Usuario("1", "Filipe", "fbt@discente.ifpe.edu.br ", "123456"));
        usuarioController.adicionarUsuario(new Usuario("2", "Rafael", "rnva@discente.ifpe.edu.br", "1234"));

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
                    "7 = Administração\n" +
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
                    String idUsuario = UUID.randomUUID().toString();
                    Usuario usuario = new Usuario(idUsuario, nome, email, senha);
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
                    // Área de administração
                    int adminEscolha;
                    do {
                        System.out.print("----------Área Administração----------\n" +
                                "1 = Adicionar produto\n" +
                                "2 = Atualizar estoque \n" +
                                "0 = Voltar ao menu principal\n" +
                                "Escolha uma opção: ");

                        // Tratamento de entrada inválida para a escolha do menu de administração
                        while (!sc.hasNextInt()) {
                            System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                            sc.next();
                        }
                        adminEscolha = sc.nextInt();
                        sc.nextLine();

                        if (adminEscolha == 1) {
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
                            idProduto = UUID.randomUUID().toString();
                            System.out.print("Estoque: ");
                            while (!sc.hasNextInt()) {
                                System.out.println("Entrada inválida! Digite um valor inteiro para o estoque.");
                                sc.next();
                            }
                            int estoque = sc.nextInt();
                            sc.nextLine();
                            Produto novoProduto = new Produto(idProduto, titulo, categoria, preco, estoque);
                            adminView.adicionarProduto(novoProduto);
                            System.out.println("Produto adicionado com sucesso!");
                        } else if (adminEscolha == 2) {
                            // Atualizar estoque
                            produtoView.exibirProdutos();
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
                                adminView.atualizarEstoque(prodId, novoEstoque);
                                System.out.println("Estoque atualizado com sucesso!");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (adminEscolha != 0) {
                            System.out.println("Opção inválida!");
                        }
                    } while (adminEscolha != 0);
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