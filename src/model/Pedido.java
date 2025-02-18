package model;

import java.util.List;

public class Pedido {
    private String id;
    private Usuario usuario;
    private List<Produto> produtos;
    private String metodoPagamento;
    private String enderecoEntrega;

    public Pedido(String id, Usuario usuario, List<Produto> produtos, String metodoPagamento, String enderecoEntrega) {
        this.id = id;
        this.usuario = usuario;
        this.produtos = produtos;
        this.metodoPagamento = metodoPagamento;
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public List<Produto> getProdutos() { return produtos; }
    public String getMetodoPagamento() { return metodoPagamento; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
}