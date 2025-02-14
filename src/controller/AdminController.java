package controller;

import model.Product;
import model.User;

public class AdminController {
    private ProductController productController;
    private UserController userController;

    public AdminController(ProductController productController, UserController userController) {
        this.productController = productController;
        this.userController = userController;
    }

    public void addProduct(Product product) {
        productController.addProduct(product);
    }

    public void updateProductStock(String id, int stock) {
        productController.updateProductStock(id, stock);
    }

    public void updateUserPassword(String id, String newPassword) {
        userController.updateUserPassword(id, newPassword);
    }
}