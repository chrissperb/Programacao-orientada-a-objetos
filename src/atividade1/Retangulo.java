package atividade1;

import java.util.Scanner;
import main.Main;

public class Retangulo {
    private double comprimento;
    private double largura;
    private double area;
    private double perimetro;

    public Retangulo(double comprimento, double largura) {
        this.comprimento = comprimento;
        this.largura = largura;
        calcularArea();
        calcularPerimetro();
    }

    private void calcularArea() {
        this.area = comprimento * largura;
    }

    private void calcularPerimetro() {
        this.perimetro = 2 * (comprimento + largura);
    }

    public void mostrarDados() {
        System.out.printf("Comprimento: %.2f metros%n", comprimento);
        System.out.printf("Largura: %.2f metros%n", largura);
        System.out.printf("Área: %.2f metros quadrados%n", area);
        System.out.printf("Perímetro: %.2f metros%n", perimetro);
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        Main.limparTela();
        System.out.println("=== Cálculo de Retângulo ===");

        System.out.println("\nDigite o comprimento do retângulo (em metros):");
        try {
            double comprimento = Double.parseDouble(scanner.nextLine());
            if (comprimento <= 0) {
                System.out.println("O comprimento deve ser maior que zero!");
                return;
            }

            System.out.println("Digite a largura do retângulo (em metros):");
            double largura = Double.parseDouble(scanner.nextLine());
            if (largura <= 0) {
                System.out.println("A largura deve ser maior que zero!");
                return;
            }

            Main.limparTela();
            System.out.println("=== Resultados do Retângulo ===\n");
            Retangulo retangulo = new Retangulo(comprimento, largura);
            retangulo.mostrarDados();

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número válido.");
        }
    }
}