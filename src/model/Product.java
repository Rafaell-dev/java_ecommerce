package model;

public class Product {
    private String id;
    private String title;
    private String category;
    private double price;
    private int stock;

    public Product(String id, String title, String category, double price, int stock) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters e Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
}