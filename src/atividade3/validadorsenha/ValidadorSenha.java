package atividade3.validadorsenha;

import main.Main;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ValidadorSenha {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int TAMANHO_MINIMO = 8;
    private static final int TAMANHO_MAXIMO = 20;

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Validador de Senha ===");
            System.out.println("\n1 - Validar Senha");
            System.out.println("2 - Ver Critérios de Segurança");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> validarSenhaUsuario();
                case 2 -> exibirCriterios();
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

    public static void validarSenha(String senha) throws SenhaInvalidaException {
        List<String> erros = new ArrayList<>();

        if (senha == null || senha.trim().isEmpty()) {
            throw new SenhaInvalidaException("A senha não pode ser vazia!");
        }

        if (senha.length() < TAMANHO_MINIMO) {
            erros.add(String.format("Mínimo de %d caracteres", TAMANHO_MINIMO));
        }

        if (senha.length() > TAMANHO_MAXIMO) {
            erros.add(String.format("Máximo de %d caracteres", TAMANHO_MAXIMO));
        }

        if (!senha.matches(".*[A-Z].*")) {
            erros.add("Pelo menos uma letra maiúscula");
        }

        if (!senha.matches(".*[a-z].*")) {
            erros.add("Pelo menos uma letra minúscula");
        }

        if (!senha.matches(".*\\d.*")) {
            erros.add("Pelo menos um número");
        }

        if (!senha.matches(".*[!@#$%^&*()\\-+=<>?].*")) {
            erros.add("Pelo menos um caractere especial (!@#$%^&*()-+=<>?)");
        }

        if (!erros.isEmpty()) {
            throw new SenhaInvalidaException(
                    "A senha deve conter:\n- " + String.join("\n- ", erros)
            );
        }
    }

    private static void validarSenhaUsuario() {
        System.out.println("\nDigite a senha para validação:");
        String senha = scanner.nextLine();

        try {
            validarSenha(senha);
            System.out.println("\nSenha válida! ✓");
            System.out.println("A senha atende a todos os critérios de segurança.");
        } catch (SenhaInvalidaException e) {
            System.out.println("\nSenha inválida! ✗");
            System.out.println(e.getMessage());
        }
    }

    private static void exibirCriterios() {
        System.out.println("\nCritérios de Segurança para Senhas");
        System.out.println("--------------------------------");
        System.out.printf("- Entre %d e %d caracteres%n", TAMANHO_MINIMO, TAMANHO_MAXIMO);
        System.out.println("- Pelo menos uma letra maiúscula");
        System.out.println("- Pelo menos uma letra minúscula");
        System.out.println("- Pelo menos um número");
        System.out.println("- Pelo menos um caractere especial (!@#$%^&*()-+=<>?)");
        System.out.println("--------------------------------");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}