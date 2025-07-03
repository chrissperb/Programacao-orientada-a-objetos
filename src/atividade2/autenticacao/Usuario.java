package atividade2.autenticacao;

public class Usuario implements Autenticavel {
    private String nome;
    private String senha;
    private boolean autenticado;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.autenticado = false;
    }

    @Override
    public boolean autenticar(String senha) {
        this.autenticado = this.senha.equals(senha);
        return this.autenticado;
    }

    @Override
    public String getIdentificacao() {
        return "Usuário: " + nome;
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}