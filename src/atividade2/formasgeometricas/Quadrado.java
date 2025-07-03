package atividade2.formasgeometricas;

public class Quadrado extends FormaGeometrica {
    private double lado;

    public Quadrado() {
        this.nome = "Quadrado";
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public void lerDados() {
        lado = lerMedida("Digite o lado do quadrado (em cm):");
    }
}