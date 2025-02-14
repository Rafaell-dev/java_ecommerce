import controller.*;
import model.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        // Inicializando controladores
        ProductController productController = new ProductController();
        UserController userController = new UserController();
        CartController cartController = new CartController();
        AdminController adminController = new AdminController(productController, userController);

        // Inicializando views
        ProductView productView = new ProductView(productController);
        UserView userView = new UserView(userController);
        CartView cartView = new CartView(cartController);
        AdminView adminView = new AdminView(adminController);

        // Adicionando alguns produtos e usuários para teste
        productController.addProduct(new Product("1", "Cabo USB", "Cabos", 10.0, 100));
        productController.addProduct(new Product("2", "Carregador", "Carregadores", 20.0, 50));
        userController.addUser(new User("1", "João", "joao@example.com", "senha123"));

        // Exibindo produtos e informações do usuário
        productView.displayProducts();
        userView.displayUserInfo("1");

        // Adicionando produto ao carrinho
        Product product = productController.getProductById("1");
        if (product != null) {
            cartController.addProductToCart(product);
            cartView.displayCart();
        }
    }
}