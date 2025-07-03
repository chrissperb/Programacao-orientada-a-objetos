package atividade2.contabancaria;

public class ContaCorrente extends ContaBancaria {
    private double limite;
    private static final double TAXA_JUROS = 15.0;

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
    public void aplicarJuros() {
        if (saldo < 0) {
            double juros = Math.abs(saldo) * (TAXA_JUROS / 100);
            saldo -= juros;
            System.out.printf("""
                    
                    === Juros Aplicados na Conta Corrente ===
                    Saldo Negativo: R$ %.2f
                    Taxa de Juros: %.1f%%
                    Juros Cobrados: R$ %.2f
                    Novo Saldo: R$ %.2f
                    """, saldo + juros, TAXA_JUROS, juros, saldo);
        } else {
            System.out.println("\nNão há juros a serem cobrados pois o saldo está positivo.");
        }
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.printf("""
                Limite: R$ %.2f
                Taxa de Juros: %.1f%%
                """, limite, TAXA_JUROS);
    }
}