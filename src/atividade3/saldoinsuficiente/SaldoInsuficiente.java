package atividade3.saldoinsuficiente;

import main.Main;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public class SaldoInsuficiente {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static final Map<Integer, ContaBancaria> contas = new HashMap<>();
    private static ContaBancaria contaAtual;

    public static void executar() {
        inicializarContas();
        int opcao;

        do {
            Main.limparTela();
            System.out.println("=== Sistema Bancário ===");
            System.out.println("\nConta Atual: " + contaAtual.getNumero());
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Realizar Depósito");
            System.out.println("3 - Realizar Saque");
            System.out.println("4 - Realizar Transferência");
            System.out.println("5 - Trocar Conta");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void inicializarContas() {
        contas.put(1, new ContaBancaria(1, 1000.0));
        contas.put(2, new ContaBancaria(2, 500.0));
        contas.put(3, new ContaBancaria(3, 2000.0));
        contaAtual = contas.get(1);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> consultarSaldo();
                case 2 -> realizarDeposito();
                case 3 -> realizarSaque();
                case 4 -> realizarTransferencia();
                case 5 -> trocarConta();
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
        System.out.printf("\nConta: %d%nSaldo atual: %s%n",
                contaAtual.getNumero(),
                formatoMoeda.format(contaAtual.getSaldo())
        );
    }

    private static void realizarDeposito() {
        System.out.println("\nDigite o valor para depósito:");
        try {
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            contaAtual.depositar(valor);
            System.out.println("\nDepósito realizado com sucesso!");
            System.out.println("Novo saldo: " + formatoMoeda.format(contaAtual.getSaldo()));
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void realizarSaque() {
        System.out.println("\nDigite o valor para saque:");
        try {
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            contaAtual.sacar(valor);
            System.out.println("\nSaque realizado com sucesso!");
            System.out.println("Novo saldo: " + formatoMoeda.format(contaAtual.getSaldo()));
        } catch (SaldoInsuficienteException e) {
            System.out.println("\nErro: " + e.getMessage());
            System.out.println("Saldo disponível: " + formatoMoeda.format(contaAtual.getSaldo()));
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void realizarTransferencia() {
        System.out.println("\nContas disponíveis:");
        contas.forEach((numero, conta) -> {
            if (numero != contaAtual.getNumero()) {
                System.out.printf("Conta %d - Saldo: %s%n",
                        numero,
                        formatoMoeda.format(conta.getSaldo())
                );
            }
        });

        System.out.println("\nDigite o número da conta de destino:");
        try {
            int numeroDestino = Integer.parseInt(scanner.nextLine());
            ContaBancaria contaDestino = contas.get(numeroDestino);

            if (contaDestino == null) {
                System.out.println("\nErro: Conta de destino não encontrada!");
                return;
            }

            System.out.println("\nDigite o valor para transferência:");
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));

            contaAtual.transferir(contaDestino, valor);

            System.out.println("\nTransferência realizada com sucesso!");
            System.out.printf("Novo saldo da conta %d: %s%n",
                    contaAtual.getNumero(),
                    formatoMoeda.format(contaAtual.getSaldo())
            );
        } catch (TransferenciaInvalidaException | SaldoInsuficienteException e) {
            System.out.println("\nErro: " + e.getMessage());
            System.out.println("Saldo disponível: " + formatoMoeda.format(contaAtual.getSaldo()));
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite um valor numérico válido!");
        }
    }

    private static void trocarConta() {
        System.out.println("\nContas disponíveis:");
        contas.forEach((numero, conta) ->
                System.out.printf("Conta %d - Saldo: %s%n",
                        numero,
                        formatoMoeda.format(conta.getSaldo())
                )
        );

        System.out.println("\nDigite o número da conta:");
        try {
            int numero = Integer.parseInt(scanner.nextLine());
            ContaBancaria novaConta = contas.get(numero);

            if (novaConta != null) {
                contaAtual = novaConta;
                System.out.println("\nConta alterada com sucesso!");
            } else {
                System.out.println("\nErro: Conta não encontrada!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite um número válido!");
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