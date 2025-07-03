package atividade3.validadoridade;

import main.Main;

import java.util.Scanner;

public class ValidadorIdade {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int IDADE_MINIMA = 0;
    private static final int IDADE_MAXIMA = 150;

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Validador de Idade ===");
            System.out.println("\n1 - Verificar Idade");
            System.out.println("2 - Classificar Faixa Etária");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> verificarIdadeUsuario();
                case 2 -> classificarFaixaEtaria();
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

    public static void verificarIdade(int idade) throws IdadeInvalidaException {
        if (idade < IDADE_MINIMA || idade > IDADE_MAXIMA) {
            throw new IdadeInvalidaException(
                    String.format("A idade deve estar entre %d e %d anos!", IDADE_MINIMA, IDADE_MAXIMA)
            );
        }
    }

    private static void verificarIdadeUsuario() {
        System.out.println("\nDigite a idade para verificação:");
        try {
            int idade = Integer.parseInt(scanner.nextLine());
            verificarIdade(idade);
            System.out.printf("\nIdade válida: %d anos%n", idade);
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite um número inteiro válido!");
        } catch (IdadeInvalidaException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void classificarFaixaEtaria() {
        System.out.println("\nDigite a idade para classificação:");
        try {
            int idade = Integer.parseInt(scanner.nextLine());
            verificarIdade(idade);
            String faixaEtaria;

            if (idade <= 12) {
                faixaEtaria = "Criança";
            } else if (idade <= 17) {
                faixaEtaria = "Adolescente";
            } else if (idade <= 59) {
                faixaEtaria = "Adulto";
            } else {
                faixaEtaria = "Idoso";
            }

            System.out.printf("\nIdade: %d anos%nClassificação: %s%n", idade, faixaEtaria);
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite um número inteiro válido!");
        } catch (IdadeInvalidaException e) {
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