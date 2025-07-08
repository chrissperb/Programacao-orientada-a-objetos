package atividade3.divisoes;

import main.Main;
import java.util.Scanner;

public class DivisaoInteira {
    private static final Scanner scanner = new Scanner(System.in);

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Divisão Inteira ===");
            System.out.println("\n1 - Realizar Divisão");
            System.out.println("2 - Verificar Divisores");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> realizarDivisao();
                case 2 -> verificarDivisores();
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

    public static int dividir(int numerador, int denominador)
            throws DivisaoInteiraInvalidaException, ArithmeticException {
        if (denominador == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida!");
        }

        double resultadoPreciso = (double) numerador / denominador;
        int resultadoInteiro = numerador / denominador;

        if (Math.abs(resultadoPreciso - resultadoInteiro) > 0) {
            throw new DivisaoInteiraInvalidaException(
                    String.format("A divisão de %d por %d não é exata! (%.2f)",
                            numerador, denominador, resultadoPreciso)
            );
        }

        return resultadoInteiro;
    }

    private static void realizarDivisao() {
        try {
            System.out.println("\nDigite o numerador:");
            int numerador = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o denominador:");
            int denominador = Integer.parseInt(scanner.nextLine());

            int resultado = dividir(numerador, denominador);
            System.out.printf("\n%d ÷ %d = %d%n", numerador, denominador, resultado);
            System.out.println("A divisão é exata!");

        } catch (DivisaoInteiraInvalidaException e) {
            System.out.println("\nErro: " + e.getMessage());
            System.out.println("Dica: Use números que resultem em uma divisão exata.");
        } catch (ArithmeticException e) {
            System.out.println("\nErro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite apenas números inteiros!");
        }
    }

    private static void verificarDivisores() {
        try {
            System.out.println("\nDigite um número para encontrar seus divisores:");
            int numero = Integer.parseInt(scanner.nextLine());

            if (numero == 0) {
                System.out.println("\nErro: Zero não possui divisores!");
                return;
            }

            System.out.printf("\nDivisores exatos de %d:%n", numero);
            boolean encontrouDivisor = false;

            for (int i = 1; i <= Math.abs(numero); i++) {
                try {
                    dividir(numero, i);
                    System.out.printf("%d ", i);
                    encontrouDivisor = true;
                } catch (DivisaoInteiraInvalidaException e) {

                }
            }

            if (!encontrouDivisor) {
                System.out.println("Nenhum divisor exato encontrado!");
            }

        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite apenas números inteiros!");
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