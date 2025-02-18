package model;

public class Usuario extends Entidade {
    private String nome;
    private String email;
    private String senha;

    public Usuario(String id, String nome, String email, String senha) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}