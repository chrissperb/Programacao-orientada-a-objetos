package atividade3;

import main.Main;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class LeitorDeArquivo {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DIRETORIO_ARQUIVOS = "arquivos";

    public static void executar() {
        int opcao;
        criarDiretorio();

        do {
            Main.limparTela();
            System.out.println("=== Leitor de Arquivos ===");
            System.out.println("\n1 - Ler arquivo");
            System.out.println("2 - Criar arquivo de teste");
            System.out.println("3 - Listar arquivos disponíveis");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> lerArquivo();
                case 2 -> criarArquivoTeste();
                case 3 -> listarArquivos();
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

    private static void lerArquivo() {
        System.out.println("\nDigite o nome do arquivo para ler:");
        String nomeArquivo = scanner.nextLine();

        File arquivo = new File(DIRETORIO_ARQUIVOS, nomeArquivo);

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            System.out.println("\nConteúdo do arquivo:");
            System.out.println("--------------------");

            String linha;
            int numeroLinha = 1;
            while ((linha = leitor.readLine()) != null) {
                System.out.printf("%3d| %s%n", numeroLinha++, linha);
            }

            System.out.println("--------------------");

        } catch (FileNotFoundException e) {
            System.out.println("\nErro: Arquivo não encontrado!");
            System.out.println("Dica: Verifique se o nome do arquivo está correto e se ele existe no diretório '" + DIRETORIO_ARQUIVOS + "'");
        } catch (IOException e) {
            System.out.println("\nErro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void criarArquivoTeste() {
        try {
            File arquivo = new File(DIRETORIO_ARQUIVOS, "teste.txt");

            try (PrintWriter escritor = new PrintWriter(new FileWriter(arquivo))) {
                escritor.println("Este é um arquivo de teste!");
                escritor.println("Segunda linha do arquivo.");
                escritor.println("Terceira linha do arquivo.");
                escritor.println("Use este arquivo para testar o leitor.");
            }

            System.out.println("\nArquivo de teste 'teste.txt' criado com sucesso!");

        } catch (IOException e) {
            System.out.println("\nErro ao criar arquivo de teste: " + e.getMessage());
        }
    }

    private static void listarArquivos() {
        File diretorio = new File(DIRETORIO_ARQUIVOS);
        File[] arquivos = diretorio.listFiles();

        if (arquivos != null && arquivos.length > 0) {
            System.out.println("\nArquivos disponíveis:");
            System.out.println("--------------------");
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    System.out.printf("- %s (%.2f KB)%n",
                            arquivo.getName(),
                            arquivo.length() / 1024.0);
                }
            }
            System.out.println("--------------------");
        } else {
            System.out.println("\nNenhum arquivo encontrado no diretório '" + DIRETORIO_ARQUIVOS + "'");
            System.out.println("Dica: Use a opção 2 para criar um arquivo de teste.");
        }
    }

    private static void criarDiretorio() {
        File diretorio = new File(DIRETORIO_ARQUIVOS);
        if (!diretorio.exists()) {
            if (diretorio.mkdir()) {
                System.out.println("Diretório '" + DIRETORIO_ARQUIVOS + "' criado com sucesso!");
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