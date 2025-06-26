package atividade2.contabancaria;

public class ContaCorrente extends ContaBancaria {
    private double limite;

    public ContaCorrente(String numeroConta, double saldoInicial, double limite) {
        super(numeroConta, saldoInicial);
        this.limite = limite;
    }

    @Override
    public String getTipoConta() {
        return "Corrente";
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= (saldo + limite)) {
            saldo -= valor;
            System.out.printf("\nSaque de R$ %.2f realizado com sucesso!%n", valor);
        } else {
            System.out.println("\nSaldo insuficiente ou valor inválido!");
        }
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.printf("Limite: R$ %.2f%n", limite);
    }
}