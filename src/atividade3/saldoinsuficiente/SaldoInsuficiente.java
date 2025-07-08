package atividade3.saldoinsuficiente;

import main.Main;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class SaldoInsuficiente {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static ContaBancaria conta;

    public static void executar() {
        conta = new ContaBancaria(1000.0); // Conta inicial com R$ 1000,00
        int opcao;

        do {
            Main.limparTela();
            System.out.println("=== Sistema Bancário ===");
            System.out.println("\n1 - Consultar Saldo");
            System.out.println("2 - Realizar Depósito");
            System.out.println("3 - Realizar Saque");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> consultarSaldo();
                case 2 -> realizarDeposito();
                case 3 -> realizarSaque();
                case 0 -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void consultarSaldo() {
        System.out.println("\nSaldo atual: " + formatoMoeda.format(conta.getSaldo()));
    }

    private static void realizarDeposito() {
        System.out.println("\nDigite o valor para depósito:");
        try {
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            conta.depositar(valor);
            System.out.println("\nDepósito realizado com sucesso!");
            System.out.println("Novo saldo: " + formatoMoeda.format(conta.getSaldo()));
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void realizarSaque() {
        System.out.println("\nDigite o valor para saque:");
        try {
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            conta.sacar(valor);
            System.out.println("\nSaque realizado com sucesso!");
            System.out.println("Novo saldo: " + formatoMoeda.format(conta.getSaldo()));
        } catch (SaldoInsuficienteException e) {
            System.out.println("\nErro: " + e.getMessage());
            System.out.println("Saldo disponível: " + formatoMoeda.format(conta.getSaldo()));
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}