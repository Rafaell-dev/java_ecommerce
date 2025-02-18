package model;

public class Produto extends Entidade {
    private String titulo;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(String id, String titulo, String categoria, double preco, int estoque) {
        super(id);
        this.titulo = titulo;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getTitulo() { return titulo; }
    public String getCategoria() { return categoria; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    public void setEstoque(int estoque) { this.estoque = estoque; }
}