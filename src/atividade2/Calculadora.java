package atividade2;

import main.Main;
import java.util.Scanner;

public class Calculadora {
    private static final Scanner scanner = new Scanner(System.in);

    public static int somar(int a, int b) {
        return a + b;
    }

    public static double somar(double a, double b) {
        return a + b;
    }

    public static int subtrair(int a, int b) {
        return a - b;
    }

    public static double subtrair(double a, double b) {
        return a - b;
    }

    public static int multiplicar(int a, int b) {
        return a * b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static int dividir(int a, int b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero!");
        return a / b;
    }

    public static double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero!");
        return a / b;
    }

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Calculadora ===");
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
            System.out.println("\nDeseja usar números decimais? (S/N):");
            boolean usarDecimal = scanner.nextLine().trim().equalsIgnoreCase("S");

            if (usarDecimal) {
                realizarOperacaoDecimal(opcao);
            } else {
                realizarOperacaoInteira(opcao);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Valor numérico inválido!");
        } catch (ArithmeticException e) {
            System.out.println("\nErro: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void realizarOperacaoDecimal(int opcao) {
        System.out.println("Digite o primeiro número:");
        double a = Double.parseDouble(scanner.nextLine().replace(",", "."));

        System.out.println("Digite o segundo número:");
        double b = Double.parseDouble(scanner.nextLine().replace(",", "."));

        double resultado = switch (opcao) {
            case 1 -> somar(a, b);
            case 2 -> subtrair(a, b);
            case 3 -> multiplicar(a, b);
            case 4 -> dividir(a, b);
            default -> 0;
        };

        String operador = switch (opcao) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> "";
        };

        System.out.printf("\n%.2f %s %.2f = %.2f%n", a, operador, b, resultado);
    }

    private static void realizarOperacaoInteira(int opcao) {
        System.out.println("Digite o primeiro número:");
        int a = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o segundo número:");
        int b = Integer.parseInt(scanner.nextLine());

        int resultado = switch (opcao) {
            case 1 -> somar(a, b);
            case 2 -> subtrair(a, b);
            case 3 -> multiplicar(a, b);
            case 4 -> dividir(a, b);
            default -> 0;
        };

        String operador = switch (opcao) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> "";
        };

        System.out.printf("\n%d %s %d = %d%n", a, operador, b, resultado);
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}