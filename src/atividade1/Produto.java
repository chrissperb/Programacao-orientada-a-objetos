package atividade1;

import java.util.Scanner;
import main.Main;

public class Produto {
    private String nome;
    private double preco;
    private String categoria;
    private int quantidade;

    public Produto(String nome, double preco, String categoria, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    private double calcularDesconto() {
        if (quantidade >= 10) {
            return 0.15;
        } else if (preco >= 100) {
            return 0.10;
        } else {
            return 0.05;
        }
    }

    public void mostrarDetalhes() {
        double desconto = calcularDesconto();
        double precoComDesconto = preco * (1 - desconto);

        System.out.println("\n=== Detalhes do Produto ===");
        System.out.println("Nome: " + nome);
        System.out.println("Categoria: " + categoria);
        System.out.printf("Preço unitário: R$ %.2f%n", preco);
        System.out.println("Quantidade: " + quantidade);
        System.out.printf("Desconto aplicado: %.1f%%%n", desconto * 100);
        System.out.printf("Preço com desconto: R$ %.2f%n", precoComDesconto);
        System.out.printf("Valor total da compra: R$ %.2f%n", precoComDesconto * quantidade);
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        Main.limparTela();
        System.out.println("=== Cadastro de Produto ===");

        System.out.println("\nDigite o nome do produto:");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("O nome não pode estar vazio!");
            return;
        }

        System.out.println("Digite a categoria do produto:");
        String categoria = scanner.nextLine().trim();
        if (categoria.isEmpty()) {
            System.out.println("A categoria não pode estar vazia!");
            return;
        }

        try {
            System.out.println("Digite o preço do produto:");
            double preco = Double.parseDouble(scanner.nextLine());
            if (preco <= 0) {
                System.out.println("O preço deve ser maior que zero!");
                return;
            }

            System.out.println("Digite a quantidade desejada:");
            int quantidade = Integer.parseInt(scanner.nextLine());
            if (quantidade <= 0) {
                System.out.println("A quantidade deve ser maior que zero!");
                return;
            }

            Main.limparTela();
            Produto produto = new Produto(nome, preco, categoria, quantidade);
            produto.mostrarDetalhes();

        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida!");
        }
    }
}