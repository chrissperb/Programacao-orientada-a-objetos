package atividade2.autenticacao;

public interface Autenticavel {
    boolean autenticar(String senha);
    String getIdentificacao();
}