package atividade3;

import main.Main;

import java.util.Scanner;

public class DivisaoPorZero {
    private static final Scanner scanner = new Scanner(System.in);

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Demonstração de Divisão por Zero ===");
            System.out.println("\n1 - Realizar Divisão");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> realizarDivisaoSimples();
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

    private static void realizarDivisaoSimples() {
        try {
            System.out.println("\nDigite o numerador:");
            int numerador = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o denominador:");
            int denominador = Integer.parseInt(scanner.nextLine());

            int resultado = dividir(numerador, denominador);
            System.out.printf("\n%d ÷ %d = %d%n", numerador, denominador, resultado);

            if (denominador == 1) {
                System.out.println("Dica: Qualquer número dividido por 1 é ele mesmo!");
            } else if (resultado == 0) {
                System.out.println("Dica: O resultado é zero porque o numerador é menor que o denominador!");
            }

        } catch (ArithmeticException e) {
            System.out.println("\nErro: Não é possível dividir por zero!");
            System.out.println("Dica: Use um denominador diferente de zero.");
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite apenas números inteiros!");
        }
    }

    private static int dividir(int numerador, int denominador) throws ArithmeticException {
        return numerador / denominador;
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}