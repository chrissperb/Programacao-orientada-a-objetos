package atividade2.animais;

public class Peixe extends Animais {
    private String tipoAgua;
    private String tipoNadadeira;

    public Peixe(String nome, String especie, double peso, int idade, String habitat,
                 String tipoAgua, String tipoNadadeira) {
        super(nome, especie, peso, idade, habitat);
        this.tipoAgua = tipoAgua;
        this.tipoNadadeira = tipoNadadeira;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Tipo de Água: " + tipoAgua);
        System.out.println("Tipo de Nadadeira: " + tipoNadadeira);
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " é silencioso como um peixe!");
    }
}