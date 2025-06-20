package atividade1;

import java.util.Scanner;
import main.Main;

public class Circulo {
    private double raio;
    private double area;
    private double perimetro;

    public Circulo(double raio) {
        this.raio = raio;
        calcularArea();
        calcularPerimetro();
    }

    private void calcularArea() {
        this.area = Math.PI * Math.pow(raio, 2);
    }

    private void calcularPerimetro() {
        this.perimetro = 2 * Math.PI * raio;
    }

    public void mostrarDados() {
        System.out.printf("Raio: %.2f centimetros%n", raio);
        System.out.printf("Área: %.2f centimetros quadrados%n", area);
        System.out.printf("Perímetro: %.2f centimetros%n", perimetro);
    }

    public static void executar() {
        Main.limparTela();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Cálculo de Círculo ===");
        System.out.println("Digite o raio do círculo em centimetros:");

        try {
            double raio = Double.parseDouble(scanner.nextLine());
            if (raio <= 0) {
                System.out.println("O raio deve ser maior que zero!");
                return;
            }

            Circulo circulo = new Circulo(raio);
            System.out.println("\nResultados:");
            circulo.mostrarDados();

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número válido.");
        }
    }
}