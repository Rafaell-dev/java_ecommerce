package view;

import model.Product;
import controller.ProductController;

public class ProductView {
    private ProductController productController;

    public ProductView(ProductController productController) {
        this.productController = productController;
    }

    public void displayProducts() {
        for (Product product : productController.getProducts()) {
            System.out.println("ID: " + product.getId() + ", Title: " + product.getTitle() + ", Price: " + product.getPrice());
        }
    }
}