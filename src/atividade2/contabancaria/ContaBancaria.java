package atividade2.contabancaria;

import main.Main;
import java.util.Scanner;

public abstract class ContaBancaria {
    protected String numeroConta;
    protected double saldo;
    protected static final Scanner scanner = new Scanner(System.in);
    private static ContaBancaria ultimaContaCriada;

    public ContaBancaria(String numeroConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    public abstract String getTipoConta();

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.printf("\nDepósito de R$ %.2f realizado com sucesso!%n", valor);
        } else {
            System.out.println("\nValor de depósito inválido!");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.printf("\nSaque de R$ %.2f realizado com sucesso!%n", valor);
        } else {
            System.out.println("\nSaldo insuficiente ou valor inválido!");
        }
    }

    public void mostrarDados() {
        System.out.printf("""
            
            === Dados da Conta %s ===
            Número: %s
            Saldo: R$ %.2f
            """, getTipoConta(), numeroConta, saldo);
    }

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Sistema Bancário ===");
            System.out.println("\n1 - Criar Conta Corrente");
            System.out.println("2 - Criar Conta Poupança");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Consultar Saldo");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> criarContaCorrente();
                case 2 -> criarContaPoupanca();
                case 3 -> realizarDeposito();
                case 4 -> realizarSaque();
                case 5 -> consultarSaldo();
                case 0 -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void criarContaCorrente() {
        try {
            System.out.println("\n=== Criação de Conta Corrente ===");
            String numero = lerString("Número da conta");
            double saldoInicial = lerValor("Saldo inicial");
            double limite = lerValor("Limite de cheque especial");

            ultimaContaCriada = new ContaCorrente(numero, saldoInicial, limite);
            System.out.println("\nConta Corrente criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    private static void criarContaPoupanca() {
        try {
            System.out.println("\n=== Criação de Conta Poupança ===");
            String numero = lerString("Número da conta");
            double saldoInicial = lerValor("Saldo inicial");
            double taxaJuros = lerValor("Taxa de juros (%)");

            ultimaContaCriada = new ContaPoupanca(numero, saldoInicial, taxaJuros);
            System.out.println("\nConta Poupança criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    private static void realizarDeposito() {
        if (verificarContaExiste()) {
            try {
                double valor = lerValor("Valor do depósito");
                ultimaContaCriada.depositar(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro no depósito: " + e.getMessage());
            }
        }
    }

    private static void realizarSaque() {
        if (verificarContaExiste()) {
            try {
                double valor = lerValor("Valor do saque");
                ultimaContaCriada.sacar(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro no saque: " + e.getMessage());
            }
        }
    }

    private static void consultarSaldo() {
        if (verificarContaExiste()) {
            ultimaContaCriada.mostrarDados();
        }
    }

    private static boolean verificarContaExiste() {
        if (ultimaContaCriada == null) {
            System.out.println("\nNenhuma conta foi criada ainda!");
            return false;
        }
        return true;
    }

    private static String lerString(String campo) {
        System.out.println(campo + ":");
        String valor = scanner.nextLine().trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode estar vazio!");
        }
        return valor;
    }

    private static double lerValor(String campo) {
        String valor = lerString(campo);
        try {
            double numero = Double.parseDouble(valor);
            if (numero < 0) {
                throw new IllegalArgumentException(campo + " não pode ser negativo!");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número válido!");
        }
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}