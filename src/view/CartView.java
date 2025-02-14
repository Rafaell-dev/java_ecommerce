package view;

import model.Product;
import controller.CartController;

public class CartView {
    private CartController cartController;

    public CartView(CartController cartController) {
        this.cartController = cartController;
    }

    public void displayCart() {
        for (Product product : cartController.getCartProducts()) {
            System.out.println("Product: " + product.getTitle() + ", Price: " + product.getPrice());
        }
    }
}