package atividade1;

import java.util.Scanner;
import main.Main;

public class Livro {
    private String titulo;
    private String autor;
    private int numeroPaginas;
    private int anoPublicacao;

    public Livro(String titulo, String autor, int numeroPaginas, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.anoPublicacao = anoPublicacao;
    }

    public void exibirDetalhes() {
        System.out.println("\n=== Detalhes do Livro ===");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Número de Páginas: " + numeroPaginas);
        System.out.println("Ano de Publicação: " + anoPublicacao);

        // Informações adicionais
        if (numeroPaginas > 500) {
            System.out.println("Classificação: Livro extenso");
        } else if (numeroPaginas > 200) {
            System.out.println("Classificação: Livro médio");
        } else {
            System.out.println("Classificação: Livro curto");
        }
    }

    public static void executar() {
        Main.limparTela();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Cadastro de Livro ===");

        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("O título não pode estar vazio!");
            return;
        }

        System.out.println("Digite o nome do autor:");
        String autor = scanner.nextLine().trim();
        if (autor.isEmpty()) {
            System.out.println("O autor não pode estar vazio!");
            return;
        }

        try {
            System.out.println("Digite o número de páginas:");
            int paginas = Integer.parseInt(scanner.nextLine());
            if (paginas <= 0) {
                System.out.println("O número de páginas deve ser maior que zero!");
                return;
            }

            System.out.println("Digite o ano de publicação:");
            int ano = Integer.parseInt(scanner.nextLine());
            if (ano < 1400 || ano > 2024) {
                System.out.println("Ano de publicação inválido!");
                return;
            }

            Livro livro = new Livro(titulo, autor, paginas, ano);
            livro.exibirDetalhes();

        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida!");
        }
    }
}