package atividade2.veiculos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
    private static int proximoId = 1;
    private final int id;
    private final Cliente cliente;
    private final Veiculo veiculo;
    private final double valorVenda;
    private final double valorAquisicao; // Preço de aquisição do veículo
    private final LocalDate dataVenda;
    private final String formaPagamento;
    private final String observacoes;

    public Venda(Cliente cliente, Veiculo veiculo, double valorVenda, double valorAquisicao,
                 LocalDate dataVenda, String formaPagamento, String observacoes) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.valorVenda = valorVenda;
        this.valorAquisicao = valorAquisicao;
        this.dataVenda = dataVenda;
        this.formaPagamento = formaPagamento;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public double getValorAquisicao() {
        return valorAquisicao;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public double calcularLucro() {
        return valorVenda - valorAquisicao;
    }

    public double calcularMargemLucro() {
        if (valorVenda == 0) return 0;
        return (calcularLucro() / valorVenda) * 100;
    }

    public boolean temLucro() {
        return calcularLucro() > 0;
    }

    public void imprimirResumo() {
        double lucro = calcularLucro();
        String statusLucro = lucro > 0 ? "✅ LUCRO" : (lucro < 0 ? "❌ PREJUÍZO" : "⚪ NEUTRO");

        System.out.printf("ID: %d | Cliente: %s | Veículo: %s | Valor: R$ %.2f | Status: %s --> R$ %.2f\n",
                id, cliente.getNome(), veiculo.getModelo(),
                valorVenda, statusLucro, Math.abs(lucro));
    }

    public void imprimirDetalhes() {
        System.out.println("=".repeat(60));
        System.out.println("📋 DETALHES DA VENDA");
        System.out.println("=".repeat(60));
        System.out.printf("ID da Venda: %d\n", id);
        System.out.printf("Data: %s\n", dataVenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("\n👤 CLIENTE:");
        System.out.printf("  Nome: %s\n", cliente.getNome());
        System.out.printf("  CPF: %s\n", cliente.getCpf());
        System.out.printf("  Telefone: %s\n", cliente.getTelefone());

        System.out.println("\n🚗 VEÍCULO:");
        System.out.printf("  %s %d\n", veiculo.getModelo(), veiculo.getAno());
        System.out.printf("  Cor: %s\n", veiculo.getCor());

        System.out.println("\n💰 INFORMAÇÕES FINANCEIRAS:");
        System.out.printf("  Valor de Custo: R$ %.2f\n", valorAquisicao);
        System.out.printf("  Valor de Venda: R$ %.2f\n", valorVenda);
        System.out.printf("  Forma de Pagamento: %s\n", formaPagamento);

        double lucro = calcularLucro();
        double margem = calcularMargemLucro();

        if (lucro > 0) {
            System.out.printf("  ✅ LUCRO: R$ %.2f (%.1f%%)\n", lucro, margem);
        } else if (lucro < 0) {
            System.out.printf("  ❌ PREJUÍZO: R$ %.2f (%.1f%%)\n", Math.abs(lucro), Math.abs(margem));
        } else {
            System.out.println("  ⚪ RESULTADO NEUTRO");
        }

        if (observacoes != null && !observacoes.trim().isEmpty()) {
            System.out.printf("\n📝 OBSERVAÇÕES: %s\n", observacoes);
        }

        System.out.println("=".repeat(60));
    }

    @Override
    public String toString() {
        return String.format("Venda[ID=%d, Cliente=%s, Veículo=%s, Valor=R$%.2f, Lucro=R$%.2f]",
                id, cliente.getNome(), veiculo.getModelo(),
                valorVenda, calcularLucro());
    }
}