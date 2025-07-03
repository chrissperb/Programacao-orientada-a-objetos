package atividade2.formasgeometricas;

public class Circulo extends FormaGeometrica {
    private double raio;

    public Circulo() {
        this.nome = "Círculo";
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }

    @Override
    public void lerDados() {
        raio = lerMedida("Digite o raio do círculo (em cm):");
    }
}