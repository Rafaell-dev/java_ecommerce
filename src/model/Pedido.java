package model;

import java.util.List;

public class Pedido {
    private String id;
    private Usuario usuario;
    private List<Produto> produtos;
    private FormaPagamento formaPagamento;
    private String enderecoEntrega;

    public Pedido(String id, Usuario usuario, List<Produto> produtos, FormaPagamento formaPagamento, String enderecoEntrega) {
        this.id = id;
        this.usuario = usuario;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.enderecoEntrega = enderecoEntrega;
    }

    // Getters e Setters
    public String getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public List<Produto> getProdutos() { return produtos; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public String getEnderecoEntrega() { return enderecoEntrega; }

    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
}