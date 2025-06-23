package atividade1;

import java.util.Scanner;
import main.Main;

public class ContaBancaria {
    private String numeroConta;
    private String titular;
    private double saldo;

    public ContaBancaria(String numeroConta, String titular, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso!%n", valor);
        } else {
            System.out.println("Valor de depósito inválido!");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado com sucesso!%n", valor);
        } else if (valor > saldo) {
            System.out.println("Saldo insuficiente para realizar o saque!");
        } else {
            System.out.println("Valor de saque inválido!");
        }
    }

    public void mostrarDados() {
        System.out.println("\n=== Dados da Conta ===");
        System.out.println("Número da conta: " + numeroConta);
        System.out.println("Titular: " + titular);
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        Main.limparTela();
        System.out.println("=== Sistema Bancário ===");

        System.out.println("\nDigite o número da conta:");
        String numeroConta = scanner.nextLine().trim();
        if (numeroConta.isEmpty()) {
            System.out.println("Número da conta inválido!");
            return;
        }

        System.out.println("Digite o nome do titular:");
        String titular = scanner.nextLine().trim();
        if (titular.isEmpty()) {
            System.out.println("Nome do titular inválido!");
            return;
        }

        try {
            System.out.println("Digite o saldo inicial:");
            double saldoInicial = Double.parseDouble(scanner.nextLine());
            if (saldoInicial < 0) {
                System.out.println("Saldo inicial não pode ser negativo!");
                return;
            }

            ContaBancaria conta = new ContaBancaria(numeroConta, titular, saldoInicial);
            int opcao;

            do {
                Main.limparTela();
                conta.mostrarDados();
                System.out.println("\n=== Operações ===");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("0 - Sair");
                System.out.println("\nEscolha uma operação:");

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("\nDigite o valor para depósito:");
                        double valorDeposito = Double.parseDouble(scanner.nextLine());
                        conta.depositar(valorDeposito);
                        break;
                    case 2:
                        System.out.println("\nDigite o valor para saque:");
                        double valorSaque = Double.parseDouble(scanner.nextLine());
                        conta.sacar(valorSaque);
                        break;
                    case 0:
                        System.out.println("\nFinalizando operações...");
                        break;
                    default:
                        System.out.println("\nOpção inválida!");
                }

                if (opcao != 0) {
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                }
            } while (opcao != 0);

        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida!");
        }
    }
}