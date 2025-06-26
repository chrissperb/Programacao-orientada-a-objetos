package atividade2.animais;

public class Ave extends Animais {
    private double envergaduraAsas;
    private String corPenas;

    public Ave(String nome, String especie, double peso, int idade, String habitat,
               double envergaduraAsas, String corPenas) {
        super(nome, especie, peso, idade, habitat);
        this.envergaduraAsas = envergaduraAsas;
        this.corPenas = corPenas;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.printf("Envergadura das Asas: %.2f m%n", envergaduraAsas);
        System.out.println("Cor das Penas: " + corPenas);
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " emite um canto característico de ave!");
    }
}