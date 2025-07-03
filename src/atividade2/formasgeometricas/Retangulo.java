package atividade2.formasgeometricas;

public class Retangulo extends FormaGeometrica {
    private double base;
    private double altura;

    public Retangulo() {
        this.nome = "Retângulo";
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    @Override
    public void lerDados() {
        base = lerMedida("Digite a base do retângulo (em cm):");
        altura = lerMedida("Digite a altura do retângulo (em cm):");
    }
}