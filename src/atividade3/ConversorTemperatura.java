package atividade3;

import main.Main;

import java.util.Scanner;

public class ConversorTemperatura {
    private static final Scanner scanner = new Scanner(System.in);
    private static final double ZERO_ABSOLUTO = -273.15;

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Conversor de Temperatura ===");
            System.out.println("\n1 - Celsius para Fahrenheit");
            System.out.println("2 - Fahrenheit para Celsius");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> converterParaFahrenheit();
                case 2 -> converterParaCelsius();
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

    private static void converterParaFahrenheit() {
        System.out.println("\nDigite a temperatura em Celsius:");
        double celsius = lerTemperatura();

        try {
            double fahrenheit = celsiusParaFahrenheit(celsius);
            System.out.printf("\n%.2f°C = %.2f°F%n", celsius, fahrenheit);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void converterParaCelsius() {
        System.out.println("\nDigite a temperatura em Fahrenheit:");
        double fahrenheit = lerTemperatura();

        try {
            double celsius = fahrenheitParaCelsius(fahrenheit);
            System.out.printf("\n%.2f°F = %.2f°C%n", fahrenheit, celsius);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    public static double celsiusParaFahrenheit(double celsius) {
        if (celsius < ZERO_ABSOLUTO) {
            throw new IllegalArgumentException(
                    String.format("A temperatura não pode ser menor que %.2f°C (zero absoluto)!", ZERO_ABSOLUTO)
            );
        }
        return (celsius * 9 / 5) + 32;
    }

    public static double fahrenheitParaCelsius(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        if (celsius < ZERO_ABSOLUTO) {
            throw new IllegalArgumentException(
                    String.format("A temperatura resultante (%.2f°C) não pode ser menor que %.2f°C (zero absoluto)!",
                            celsius, ZERO_ABSOLUTO)
            );
        }
        return celsius;
    }

    private static double lerTemperatura() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido:");
            }
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