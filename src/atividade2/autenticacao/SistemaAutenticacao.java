package atividade2.autenticacao;

import main.Main;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAutenticacao {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Autenticavel> usuarios = new ArrayList<>();
    private static final String SENHA_PADRAO = "123456";
    private static final String CODIGO_ADMIN = "ADMIN123";

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Sistema de Autenticação ===");
            System.out.println("\n1 - Cadastrar Usuário");
            System.out.println("2 - Cadastrar Administrador");
            System.out.println("3 - Listar Usuários");
            System.out.println("4 - Realizar Autenticação");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarAdministrador();
                case 3 -> listarUsuarios();
                case 4 -> realizarAutenticacao();
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

    private static void cadastrarUsuario() {
        System.out.println("\nDigite o nome do usuário:");
        String nome = scanner.nextLine();

        usuarios.add(new Usuario(nome, SENHA_PADRAO));
        System.out.println("\nUsuário cadastrado com sucesso!");
        System.out.println("Senha padrão: " + SENHA_PADRAO);
    }

    private static void cadastrarAdministrador() {
        System.out.println("\nDigite o nome do administrador:");
        String nome = scanner.nextLine();

        usuarios.add(new Administrador(nome, SENHA_PADRAO, CODIGO_ADMIN));
        System.out.println("\nAdministrador cadastrado com sucesso!");
        System.out.println("Senha padrão: " + SENHA_PADRAO);
        System.out.println("Código de Admin: " + CODIGO_ADMIN);
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("\nNenhum usuário cadastrado!");
            return;
        }

        System.out.println("\n=== Usuários Cadastrados ===");
        for (int i = 0; i < usuarios.size(); i++) {
            Autenticavel usuario = usuarios.get(i);
            System.out.printf("%d - %s (Autenticado: %s)%n",
                    i + 1,
                    usuario.getIdentificacao(),
                    (usuario instanceof Usuario) ?
                            ((Usuario) usuario).isAutenticado() ? "Sim" : "Não" :
                            ((Administrador) usuario).isAutenticado() ? "Sim" : "Não"
            );
        }
    }

    private static void realizarAutenticacao() {
        if (usuarios.isEmpty()) {
            System.out.println("\nNenhum usuário cadastrado!");
            return;
        }

        listarUsuarios();
        System.out.println("\nDigite o número do usuário para autenticar:");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println("\nUsuário inválido!");
            return;
        }

        System.out.println("\nDigite a senha:");
        String senha = scanner.nextLine();

        Autenticavel usuario = usuarios.get(indice);
        boolean autenticado = usuario.autenticar(senha);

        System.out.println("\n" + usuario.getIdentificacao());
        System.out.println("Status: " + (autenticado ? "Autenticado com sucesso!" : "Falha na autenticação!"));
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}