package atividade3.saldoinsuficiente;

public class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo!");
        }
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo!");
        }
        this.saldo += valor;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo!");
        }
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException(
                    String.format("Saldo insuficiente para saque de R$ %.2f!", valor)
            );
        }
        this.saldo -= valor;
    }

    public double getSaldo() {
        return this.saldo;
    }
}