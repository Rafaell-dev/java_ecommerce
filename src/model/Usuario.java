package model;

public class Usuario extends Entidade {
    private String nome;
    private String email;
    private String senha;
    private String tipo; // "COMUM" ou "ADMIN"


    public Usuario(String id, String nome, String email, String senha, String tipo) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTipo() { return tipo; }

    public void setSenha(String senha) { this.senha = senha; }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}