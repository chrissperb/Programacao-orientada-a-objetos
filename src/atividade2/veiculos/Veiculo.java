package atividade2.veiculos;

public class Veiculo {
    protected String modelo;
    protected int ano;
    protected String cor;
    protected double preco;

    public Veiculo(String modelo, int ano, String cor, double preco) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
    }

    public void imprimirInformacoes() {
        System.out.printf("""
                
                === Informações do Veículo ===
                Tipo: %s
                Modelo: %s
                Ano: %d
                Cor: %s
                Valor: R$ %.2f
                """, this.getClass().getSimpleName(), modelo, ano, cor, preco);
    }

    public static void executar() {
        GerenciadorVeiculos.executar();
    }

    public boolean sugeridoPara(Cliente cliente) {
        return false;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

}