package atividade2.animais;

public class Mamifero extends Animais {
    private String tipoPelagem;
    private int mesesGestacao;

    public Mamifero(String nome, String especie, double peso, int idade, String habitat,
                    String tipoPelagem, int mesesGestacao) {
        super(nome, especie, peso, idade, habitat);
        this.tipoPelagem = tipoPelagem;
        this.mesesGestacao = mesesGestacao;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Tipo de Pelagem: " + tipoPelagem);
        System.out.println("Meses de Gestação: " + mesesGestacao);
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " emite um som característico de mamífero!");
    }
}