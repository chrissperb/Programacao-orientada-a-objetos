package atividade1;

import java.util.Scanner;
import main.Main;

public class Animal {
    private String nome;
    private String tipoAlimentacao;
    private String especie;

    public Animal(String nome, String tipoAlimentacao, String especie) {
        this.nome = nome;
        this.tipoAlimentacao = tipoAlimentacao;
        this.especie = especie;
    }

    public String emitirSom() {
        return switch (especie.toLowerCase()) {
            case "cachorro" -> "Au Au!";
            case "gato" -> "Miau!";
            case "leao" -> "Roar!";
            case "vaca" -> "Muuu!";
            case "galinha" -> "Có Có!";
            default -> "Som desconhecido";
        };
    }

    public void mostrarDados() {
        System.out.println("\n=== Detalhes do Animal ===");
        System.out.println("Nome: " + nome);
        System.out.println("Espécie: " + especie);
        System.out.println("Alimentação: " + tipoAlimentacao);
        System.out.println("Som característico: " + emitirSom());
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        Main.limparTela();
        System.out.println("=== Cadastro de Animal ===");

        System.out.println("\nDigite o nome do animal:");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("O nome não pode estar vazio!");
            return;
        }

        System.out.println("\nDigite a espécie do animal (cachorro, gato, leao, vaca, galinha):");
        String especie = scanner.nextLine().trim();
        if (especie.isEmpty()) {
            System.out.println("A espécie não pode estar vazia!");
            return;
        }

        System.out.println("\nDigite o tipo de alimentação do animal:");
        String tipoAlimentacao = scanner.nextLine().trim();
        if (tipoAlimentacao.isEmpty()) {
            System.out.println("O tipo de alimentação não pode estar vazio!");
            return;
        }

        Main.limparTela();
        Animal animal = new Animal(nome, tipoAlimentacao, especie);
        animal.mostrarDados();
    }
}
