package atividade1;

import java.util.Scanner;

public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String cumprimentar() {
        if (idade < 12) {
            return "Olá! Eu sou " + nome + " e tenho " + idade + " aninhos!";
        } else if (idade < 18) {
            return "Oi! Me chamo " + nome + " e tenho " + idade + " anos!";
        } else {
            return "Prezados, eu sou " + nome + " e tenho " + idade + " anos.";
        }
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Cadastro de Pessoa ===");

        System.out.println("Digite o nome da pessoa:");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("O nome não pode estar vazio!");
            return;
        }

        System.out.println("Digite a idade da pessoa:");
        try {
            int idade = Integer.parseInt(scanner.nextLine());

            if (idade < 0 || idade > 150) {
                System.out.println("Idade inválida! Digite um valor entre 0 e 150.");
                return;
            }

            Pessoa pessoa = new Pessoa(nome, idade);
            System.out.println("\nCumprimento gerado:");
            System.out.println(pessoa.cumprimentar());

        } catch (NumberFormatException e) {
            System.out.println("Idade inválida! Digite um número inteiro.");
        }
    }
}