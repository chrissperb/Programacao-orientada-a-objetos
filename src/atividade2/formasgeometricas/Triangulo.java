package atividade2.formasgeometricas;

public class Triangulo extends FormaGeometrica {
    private double lado1;
    private double lado2;
    private double lado3;

    @Override
    public void lerDados() {
        do {
            lado1 = lerMedida("Digite o primeiro lado do triângulo");
            lado2 = lerMedida("Digite o segundo lado do triângulo");
            lado3 = lerMedida("Digite o terceiro lado do triângulo");

            if (isTrianguloValido()) {
                System.out.println("\nErro: Os lados informados não formam um triângulo válido!");
                System.out.println("Para formar um triângulo, a soma de dois lados deve ser maior que o terceiro lado.");
                System.out.println("\nPressione ENTER para tentar novamente...");
                scanner.nextLine();
            }
        } while (isTrianguloValido());

        this.nome = getTipoTriangulo();
    }

    private boolean isTrianguloValido() {
        return (!(lado1 + lado2 > lado3)) ||
                (!(lado2 + lado3 > lado1)) ||
                (!(lado1 + lado3 > lado2));
    }

    @Override
    public double calcularArea() {
        double s = calcularPerimetro() / 2;
        return Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }

    public String getTipoTriangulo() {
        if (lado1 == lado2 && lado2 == lado3 && lado1 == lado3) {
            return "Triangulo Equilátero";
        } else if (lado1 == lado2 || lado2 == lado3 || lado1 == lado3) {
            return "Triangulo Isósceles";
        } else {
            return "Triangulo Escaleno";
        }
    }

    public Triangulo() {
    }
}