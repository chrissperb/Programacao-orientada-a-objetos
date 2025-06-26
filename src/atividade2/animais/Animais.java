package atividade2.animais;

import main.Main;
import java.util.Scanner;

public abstract class Animais {
    protected String nome;
    protected String especie;
    protected double peso;
    protected int idade;
    protected String habitat;
    protected static final Scanner scanner = new Scanner(System.in);

    public Animais(String nome, String especie, double peso, int idade, String habitat) {
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
        this.idade = idade;
        this.habitat = habitat;
    }

    public void mostrarDados() {
        System.out.println("\n=== Dados do Animal ===");
        System.out.println("Nome: " + nome);
        System.out.println("Espécie: " + especie);
        System.out.printf("Peso: %.2f kg%n", peso);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Habitat: " + habitat);
    }

    public abstract void emitirSom();

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Catálogo de Animais ===");
            System.out.println("\n1 - Cadastrar Mamífero");
            System.out.println("2 - Cadastrar Ave");
            System.out.println("3 - Cadastrar Peixe");
            System.out.println("4 - Exibir Último Animal");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static Animais ultimoAnimalCadastrado = null;

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarMamifero();
            case 2 -> cadastrarAve();
            case 3 -> cadastrarPeixe();
            case 4 -> exibirUltimoAnimal();
            case 0 -> System.out.println("\nVoltando ao menu principal...");
            default -> System.out.println("\nOpção inválida!");
        }

        if (opcao != 0) {
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    protected static String lerString(String campo) {
        System.out.println(campo + ":");
        String valor = scanner.nextLine().trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode estar vazio!");
        }
        return valor;
    }

    protected static double lerDouble(String campo) {
        String valor = lerString(campo);
        try {
            double numero = Double.parseDouble(valor);
            if (numero <= 0) {
                throw new IllegalArgumentException(campo + " deve ser maior que zero!");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número válido!");
        }
    }

    protected static int lerInteiro(String campo) {
        String valor = lerString(campo);
        try {
            int numero = Integer.parseInt(valor);
            if (numero < 0) {
                throw new IllegalArgumentException(campo + " não pode ser negativo!");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número válido!");
        }
    }

    private static void exibirUltimoAnimal() {
        Main.limparTela();
        if (ultimoAnimalCadastrado == null) {
            System.out.println("Nenhum animal foi cadastrado ainda!");
        } else {
            ultimoAnimalCadastrado.mostrarDados();
            ultimoAnimalCadastrado.emitirSom();
        }
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarMamifero() {
        try {
            Main.limparTela();
            System.out.println("=== Cadastro de Mamífero ===\n");
            String nome = lerString("Nome do animal");
            String especie = lerString("Espécie");
            double peso = lerDouble("Peso (kg)");
            int idade = lerInteiro("Idade (anos)");
            String habitat = lerString("Habitat");
            String tipoPelagem = lerString("Tipo de Pelagem");
            int mesesGestacao = lerInteiro("Meses de Gestação");

            ultimoAnimalCadastrado = new Mamifero(nome, especie, peso, idade, habitat, tipoPelagem, mesesGestacao);
            System.out.println("\nMamífero cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void cadastrarAve() {
        try {
            Main.limparTela();
            System.out.println("=== Cadastro de Ave ===\n");
            String nome = lerString("Nome do animal");
            String especie = lerString("Espécie");
            double peso = lerDouble("Peso (kg)");
            int idade = lerInteiro("Idade (anos)");
            String habitat = lerString("Habitat");
            double envergadura = lerDouble("Envergadura das Asas (m)");
            String corPenas = lerString("Cor das Penas");

            ultimoAnimalCadastrado = new Ave(nome, especie, peso, idade, habitat, envergadura, corPenas);
            System.out.println("\nAve cadastrada com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void cadastrarPeixe() {
        try {
            Main.limparTela();
            System.out.println("=== Cadastro de Peixe ===\n");
            String nome = lerString("Nome do animal");
            String especie = lerString("Espécie");
            double peso = lerDouble("Peso (kg)");
            int idade = lerInteiro("Idade (anos)");
            String habitat = lerString("Habitat");
            String tipoAgua = lerString("Tipo de Água (doce/salgada)");
            String tipoNadadeira = lerString("Tipo de Nadadeira");

            ultimoAnimalCadastrado = new Peixe(nome, especie, peso, idade, habitat, tipoAgua, tipoNadadeira);
            System.out.println("\nPeixe cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
}