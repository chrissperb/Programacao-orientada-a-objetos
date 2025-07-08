package atividade3;

import main.Main;
import java.util.Scanner;

public class ConversorStringParaInteger {
    private static final Scanner scanner = new Scanner(System.in);

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Conversor String para Integer ===");
            System.out.println("\n1 - Converter Número");
            System.out.println("2 - Converter Lista de Números");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> converterNumero();
                case 2 -> converterLista();
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

    public static int converterParaInteiro(String valor) throws NumberFormatException {
        valor = valor.trim();
        if (valor.isEmpty()) {
            throw new NumberFormatException("O valor não pode estar vazio!");
        }
        if (valor.contains(" ")) {
            throw new NumberFormatException("O valor não pode conter espaços!");
        }
        return Integer.parseInt(valor);
    }

    private static void converterNumero() {
        System.out.println("\nDigite um número inteiro:");
        String entrada = scanner.nextLine();

        try {
            int numero = converterParaInteiro(entrada);
            System.out.printf("\nConversão realizada com sucesso!%n");
            System.out.printf("String \"%s\" -> Inteiro %d%n", entrada, numero);
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Não foi possível converter para número!");
            System.out.println("Motivo: " + (e.getMessage().contains("For input string") ?
                    "O valor deve conter apenas dígitos!" : e.getMessage()));
            System.out.println("Valor inserido: \"" + entrada + "\"");
            System.out.println("\nDica: Digite apenas números inteiros, sem letras, símbolos ou espaços.");
        }
    }

    private static void converterLista() {
        System.out.println("\nDigite vários números separados por vírgula:");
        String entrada = scanner.nextLine();

        String[] valores = entrada.split(",");
        int sucessos = 0;
        int falhas = 0;

        System.out.println("\nResultado da conversão:");
        System.out.println("--------------------");

        for (String valor : valores) {
            try {
                int numero = converterParaInteiro(valor);
                System.out.printf("✓ \"%s\" -> %d%n", valor, numero);
                sucessos++;
            } catch (NumberFormatException e) {
                System.out.printf("✗ \"%s\" -> Erro: %s%n", valor,
                        e.getMessage().contains("For input string") ?
                                "Valor inválido" : e.getMessage());
                falhas++;
            }
        }

        System.out.println("--------------------");
        System.out.printf("Total: %d números processados%n", valores.length);
        System.out.printf("Sucessos: %d%n", sucessos);
        System.out.printf("Falhas: %d%n", falhas);
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}