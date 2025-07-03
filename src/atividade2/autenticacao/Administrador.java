package atividade2.autenticacao;

public class Administrador implements Autenticavel {
    private String nome;
    private String senha;
    private String codigoAdmin;
    private boolean autenticado;

    public Administrador(String nome, String senha, String codigoAdmin) {
        this.nome = nome;
        this.senha = senha;
        this.codigoAdmin = codigoAdmin;
        this.autenticado = false;
    }

    @Override
    public boolean autenticar(String senha) {
        this.autenticado = this.senha.equals(senha);
        return this.autenticado;
    }

    @Override
    public String getIdentificacao() {
        return "Administrador: " + nome;
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}