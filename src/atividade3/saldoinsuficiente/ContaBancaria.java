package atividade3.saldoinsuficiente;

public class ContaBancaria {
    private int numero;
    private double saldo;

    public ContaBancaria(int numero, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo!");
        }
        this.numero = numero;
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

    public void transferir(ContaBancaria destino, double valor)
            throws TransferenciaInvalidaException, SaldoInsuficienteException {
        if (valor <= 0) {
            throw new TransferenciaInvalidaException("Valor da transferência deve ser positivo!");
        }
        if (destino == null) {
            throw new TransferenciaInvalidaException("Conta de destino inválida!");
        }
        if (this.numero == destino.numero) {
            throw new TransferenciaInvalidaException("Não é possível transferir para a mesma conta!");
        }

        this.sacar(valor);
        destino.depositar(valor);
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getNumero() {
        return this.numero;
    }
}