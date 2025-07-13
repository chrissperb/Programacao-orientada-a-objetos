package atividade2.veiculos;

public class Caminhao extends Veiculo {
    private double capacidadeCarga;
    private int eixos;
    private boolean bauRefrigerado;

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
                Capacidade de Carga: %.2f toneladas
                Eixos: %d
                Baú Refrigerado: %s
                """, capacidadeCarga, eixos, bauRefrigerado ? "Sim" : "Não");
    }

    @Override
    public boolean sugeridoPara(Cliente cliente) {
        return cliente.getIdade() >= 30 && cliente.getAltura() >= 1.7;
    }
}
