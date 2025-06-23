package atividade1;

import java.util.Scanner;
import main.Main;

public class Triangulo {
    private double base;
    private double altura;
    private double area;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
        calcularArea();
    }

    private void calcularArea() {
        this.area = (base * altura) / 2;
    }

    public void mostrarDados() {
        System.out.println("\n=== Dados do Triângulo ===");
        System.out.printf("Base: %.2f metros%n", base);
        System.out.printf("Altura: %.2f metros%n", altura);
        System.out.printf("Área: %.2f metros quadrados%n", area);
        System.out.println("\nFórmula utilizada: Area = (base * altura) / 2");
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        Main.limparTela();
        System.out.println("=== Cálculo do Triângulo ===");

        try {
            System.out.println("\nDigite a base do triângulo (em metros):");
            double base = Double.parseDouble(scanner.nextLine());
            if (base <= 0) {
                System.out.println("A base deve ser maior que zero!");
                return;
            }

            System.out.println("Digite a altura do triângulo (em metros):");
            double altura = Double.parseDouble(scanner.nextLine());
            if (altura <= 0) {
                System.out.println("A altura deve ser maior que zero!");
                return;
            }

            Main.limparTela();
            System.out.println("=== Resultados do Triângulo ===");
            Triangulo triangulo = new Triangulo(base, altura);
            triangulo.mostrarDados();

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número válido.");
        }
    }
}