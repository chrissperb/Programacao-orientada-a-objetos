package atividade2.contabancaria;

public class ContaPoupanca extends ContaBancaria {
    private double taxaJuros;

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
    public void mostrarDados() {
        super.mostrarDados();
        System.out.printf("Taxa de Juros: %.2f%%%n", taxaJuros);
    }
}