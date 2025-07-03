package atividade3.calculadora;

import main.Main;

import java.util.Scanner;

public class Calculadora {
    private static final Scanner scanner = new Scanner(System.in);

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Calculadora com Tratamento de Exceções ===");
            System.out.println("\n1 - Soma");
            System.out.println("2 - Subtração");
            System.out.println("3 - Multiplicação");
            System.out.println("4 - Divisão");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma operação:");

            opcao = lerOpcao();
            if (opcao != 0) {
                realizarOperacao(opcao);
            }
        } while (opcao != 0);
    }

    private static void realizarOperacao(int opcao) {
        try {
            System.out.println("\nDigite o primeiro número:");
            double num1 = lerNumero();

            System.out.println("Digite o segundo número:");
            double num2 = lerNumero();

            double resultado = switch (opcao) {
                case 1 -> somar(num1, num2);
                case 2 -> subtrair(num1, num2);
                case 3 -> multiplicar(num1, num2);
                case 4 -> dividir(num1, num2);
                default -> throw new IllegalArgumentException("Operação inválida!");
            };

            String operador = switch (opcao) {
                case 1 -> "+";
                case 2 -> "-";
                case 3 -> "*";
                case 4 -> "/";
                default -> "";
            };

            System.out.printf("\n%.2f %s %.2f = %.2f%n", num1, operador, num2, resultado);

        } catch (DivisaoPorZeroException e) {
            System.out.println("\nErro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Valor numérico inválido!");
        } catch (Exception e) {
            System.out.println("\nErro inesperado: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static double somar(double a, double b) {
        return a + b;
    }

    private static double subtrair(double a, double b) {
        return a - b;
    }

    private static double multiplicar(double a, double b) {
        return a * b;
    }

    private static double dividir(double a, double b) throws DivisaoPorZeroException {
        if (b == 0) {
            throw new DivisaoPorZeroException("Não é possível dividir por zero!");
        }
        return a / b;
    }

    private static double lerNumero() {
        String entrada = scanner.nextLine().replace(",", ".");
        if (entrada.isEmpty()) {
            throw new NumberFormatException("O valor não pode estar vazio!");
        }
        return Double.parseDouble(entrada);
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
