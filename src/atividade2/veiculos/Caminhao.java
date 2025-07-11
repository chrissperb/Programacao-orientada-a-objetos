package atividade2.veiculos;

public class Caminhao extends Veiculo {
    private final double capacidadeCarga;
    private final int eixos;
    private final boolean bauRefrigerado;

    public Caminhao(String modelo, int ano, String cor, double preco,
                    double capacidadeCarga, int eixos, boolean bauRefrigerado) {
        super(modelo, ano, cor, preco);
        this.capacidadeCarga = capacidadeCarga;
        this.eixos = eixos;
        this.bauRefrigerado = bauRefrigerado;
    }

    @Override
    public void imprimirInformacoes() {
        super.imprimirInformacoes();
        System.out.printf("""
                === Especificações do Caminhão ===
                Capacidade de Carga: %.1f toneladas
                Número de Eixos: %d
                Baú Refrigerado: %s
                """, capacidadeCarga, eixos, bauRefrigerado ? "Sim" : "Não");
    }
}