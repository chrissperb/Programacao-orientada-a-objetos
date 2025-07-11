package atividade2.veiculos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Cliente {
    private static int proximoId = 1;
    private final int id;
    private String nome;
    private final String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private final LocalDate dataNascimento;
    private String profissao;
    private double rendaMensal;
    private final LocalDate dataCadastro;
    private boolean ativo;

    public Cliente(String nome, String cpf, String telefone, String email,
                   String endereco, LocalDate dataNascimento, String profissao,
                   double rendaMensal) {
        this.id = proximoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.profissao = profissao;
        this.rendaMensal = rendaMensal;
        this.dataCadastro = LocalDate.now();
        this.ativo = true;
    }

    public void imprimirInformacoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        System.out.printf("""
                        
                        === Informações do Cliente ===
                        ID: %d
                        Nome: %s
                        CPF: %s
                        Telefone: %s
                        Email: %s
                        Endereço: %s
                        Data de Nascimento: %s (Idade: %d anos)
                        Profissão: %s
                        Renda Mensal: R$ %.2f
                        Data de Cadastro: %s
                        Status: %s
                        """, id, nome, cpf, telefone, email, endereco,
                dataNascimento.format(formatter), idade, profissao,
                rendaMensal, dataCadastro.format(formatter),
                ativo ? "Ativo" : "Inativo");
    }

    public void imprimirResumo() {
        System.out.printf("ID: %d | Nome: %s | CPF: %s | Telefone: %s | Status: %s\n",
                id, nome, cpf, telefone, ativo ? "Ativo" : "Inativo");
    }

    public boolean podeFinanciar(double valorVeiculo) {
        // Regra: renda mensal deve ser pelo menos 30% do valor da parcela
        // Considerando financiamento em 60 meses
        double parcela = valorVeiculo / 60;
        return rendaMensal >= (parcela * 3.33); // 30% da renda
    }

    public void calcularCapacidadeFinanciamento() {
        double capacidadeMaxima = (rendaMensal * 0.30) * 60; // 30% da renda em 60 meses
        System.out.printf("""
                === Análise de Crédito ===
                Renda Mensal: R$ %.2f
                Capacidade de Financiamento (60x): R$ %.2f
                Valor máximo da parcela: R$ %.2f
                """, rendaMensal, capacidadeMaxima, rendaMensal * 0.30);
    }

    public void desativar() {
        this.ativo = false;
        System.out.println("Cliente desativado com sucesso!");
    }

    public void ativar() {
        this.ativo = true;
        System.out.println("Cliente ativado com sucesso!");
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }
}