import controller.*;
import model.*;
import view.*;
import exception.*;

public class Main {
    public static void main(String[] args) {
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

        // Adicionando alguns produtos e usuários para teste
        produtoController.adicionarProduto(new Produto("1", "Cabo USB", "Cabos", 10.0, 100));
        produtoController.adicionarProduto(new Produto("2", "Carregador", "Carregadores", 20.0, 50));
        usuarioController.adicionarUsuario(new Usuario("1", "Filipe", "filipe@example.com", "gacessorios1234"));

        // Exibindo produtos e informações do usuário
        produtoView.exibirProdutos();
        usuarioView.exibirUsuario("1");

        // Adicionando produto ao carrinho
        carrinhoView.adicionarProdutoAoCarrinho("1");
        carrinhoView.exibirCarrinho();

        // Testando exceções
        try {
            produtoController.buscarProdutoPorId("999"); // Produto não existe
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}