package controller;

import model.Cart;
import model.Product;

import java.util.List;

public class CartController {
    private Cart cart;

    public CartController() {
        this.cart = new Cart();
    }

    public void addProductToCart(Product product) {
        cart.addProduct(product);
    }

    public List<Product> getCartProducts() {
        return cart.getProducts();
    }

    public void clearCart() {
        cart.clearCart();
    }
}