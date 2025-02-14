package model;

import java.util.List;

public class Order {
    private String id;
    private User user;
    private List<Product> products;
    private String paymentMethod;
    private String deliveryAddress;

    public Order(String id, User user, List<Product> products, String paymentMethod, String deliveryAddress) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
    }

    // Getters e Setters
    public String getId() { return id; }
    public User getUser() { return user; }
    public List<Product> getProducts() { return products; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getDeliveryAddress() { return deliveryAddress; }
}