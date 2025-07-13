package atividade2.veiculos;

public class Carro extends Veiculo {
    private final int portas;
    private final String combustivel;
    private final boolean arCondicionado;

    public Carro(String modelo, int ano, String cor, double preco,
                 int portas, String combustivel, boolean arCondicionado) {
        super(modelo, ano, cor, preco);
        this.portas = portas;
        this.combustivel = combustivel;
        this.arCondicionado = arCondicionado;
    }

    @Override
    public void imprimirInformacoes() {
        super.imprimirInformacoes();
        System.out.printf("""
                === Especificações do Carro ===
                Portas: %d
                Combustível: %s
                Ar Condicionado: %s
                """, portas, combustivel, arCondicionado ? "Sim" : "Não");
    }

    public int getPortas() {
        return portas;
    }

    @Override
    public boolean sugeridoPara(Cliente cliente) {
        return cliente.getIdade() >= 25 && cliente.getAltura() >= 1.6;
    }
}