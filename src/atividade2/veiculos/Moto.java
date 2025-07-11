package atividade2.veiculos;

import main.Main;

import java.util.Scanner;

public class Moto extends Veiculo {
    private final int cilindradas;
    private final String tipoPartida;
    private boolean bauInstalado;

    public Moto(String modelo, int ano, String cor, double preco,
                int cilindradas, String tipoPartida, boolean bauInstalado) {
        super(modelo, ano, cor, preco);
        this.cilindradas = cilindradas;
        this.tipoPartida = tipoPartida;
        this.bauInstalado = bauInstalado;
    }

    @Override
    public void imprimirInformacoes() {
        super.imprimirInformacoes();
        System.out.printf("""
                === Especificações da Moto ===
                Cilindradas: %dcc
                Tipo de Partida: %s
                Baú: %s
                """, cilindradas, tipoPartida, bauInstalado ? "Instalado" : "Não instalado");
    }

    public void instalarBau() {
        if (bauInstalado) {
            System.out.println("Esta moto já possui baú instalado!");
        } else {
            bauInstalado = true;
            System.out.println("Baú instalado com sucesso!");
        }
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        Main.limparTela();
        System.out.println("=== Cadastro de Moto ===");

        try {
            System.out.println("\nDigite o modelo:");
            String modelo = scanner.nextLine().trim();

            System.out.println("Digite o ano:");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite a cor:");
            String cor = scanner.nextLine().trim();

            System.out.println("Digite o preço:");
            double preco = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite as cilindradas:");
            int cilindradas = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o tipo de partida (Elétrica/Manual):");
            String tipoPartida = scanner.nextLine().trim();

            System.out.println("Possui baú instalado? (S/N):");
            boolean bauInstalado = scanner.nextLine().trim().equalsIgnoreCase("S");

            Moto moto = new Moto(modelo, ano, cor, preco, cilindradas,
                    tipoPartida, bauInstalado);
            int opcao;

            do {
                Main.limparTela();
                moto.imprimirInformacoes();
                System.out.println("\n=== Operações ===");
                System.out.println("1 - Instalar Baú");
                System.out.println("0 - Voltar");
                System.out.println("\nEscolha uma operação:");

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        moto.instalarBau();
                        break;
                    case 0:
                        System.out.println("\nVoltando ao menu...");
                        break;
                    default:
                        System.out.println("\nOpção inválida!");
                }

                if (opcao != 0) {
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                }
            } while (opcao != 0);

        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida!");
        }
    }
}