package atividade2.formasgeometricas;

import main.Main;

import java.util.Scanner;

public abstract class FormaGeometrica {
    protected static final Scanner scanner = new Scanner(System.in);
    protected String nome;

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public abstract void lerDados();

    private static FormaGeometrica formaAtual;

    public static void executar() {
        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Cálculos Geométricos ===");
            System.out.println("\n1 - Criar Quadrado");
            System.out.println("2 - Criar Retângulo");
            System.out.println("3 - Criar Círculo");
            System.out.println("4 - Criar Triangulo");
            System.out.println("5 - Mostrar Cálculos");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma opção:");

            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);
    }

    private static void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> criarQuadrado();
                case 2 -> criarRetangulo();
                case 3 -> criarCirculo();
                case 4 -> criarTriangulo();
                case 5 -> mostrarCalculos();
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

    private static void criarQuadrado() {
        formaAtual = new Quadrado();
        formaAtual.lerDados();
    }

    private static void criarRetangulo() {
        formaAtual = new Retangulo();
        formaAtual.lerDados();
    }

    private static void criarCirculo() {
        formaAtual = new Circulo();
        formaAtual.lerDados();
    }

    private static void criarTriangulo() {
        formaAtual = new Triangulo();
        formaAtual.lerDados();
    }

    private static void mostrarCalculos() {
        if (formaAtual == null) {
            System.out.println("\nNenhuma forma geométrica foi criada ainda!");
            return;
        }

        System.out.printf("""
                
                === %s ===
                Área: %.2f
                Perímetro: %.2f
                """, formaAtual.nome, formaAtual.calcularArea(), formaAtual.calcularPerimetro());
    }

    protected static double lerMedida(String campo) {
        while (true) {
            try {
                System.out.println(campo + ":");
                double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (valor <= 0) {
                    throw new IllegalArgumentException("O valor deve ser maior que zero!");
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
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