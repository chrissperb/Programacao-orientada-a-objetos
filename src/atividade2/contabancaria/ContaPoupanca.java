package atividade2.contabancaria;

public class ContaPoupanca extends ContaBancaria {
    private double taxaJuros;
    private static final double TAXA_RENDIMENTO = 6.5;

    public ContaPoupanca(String numeroConta, double saldoInicial, double taxaJuros) {
        super(numeroConta, saldoInicial);
        this.taxaJuros = taxaJuros;
    }

    @Override
    public String getTipoConta() {
        return "Poupança";
    }

    public void calcularJuros() {
        double juros = saldo * (taxaJuros / 100);
        depositar(juros);
        System.out.printf("\nJuros de R$ %.2f creditados na conta!%n", juros);
    }

    @Override
    public void aplicarJuros() {
        double juros = saldo * (TAXA_RENDIMENTO / 100);
        saldo += juros;
        System.out.printf("""
                
                === Rendimento Aplicado na Poupança ===
                Saldo Anterior: R$ %.2f
                Taxa de Rendimento: %.1f%%
                Juros Creditados: R$ %.2f
                Novo Saldo: R$ %.2f
                """, saldo - juros, TAXA_RENDIMENTO, juros, saldo);
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.printf("""
                Taxa de Rendimento: %.1f%%
                Taxa de Juros Mensal: %.2f%%
                """, TAXA_RENDIMENTO, taxaJuros);
    }
}