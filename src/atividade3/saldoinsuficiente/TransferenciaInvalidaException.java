package atividade3.saldoinsuficiente;

public class TransferenciaInvalidaException extends Exception {
    public TransferenciaInvalidaException(String mensagem) {
        super(mensagem);
    }
}