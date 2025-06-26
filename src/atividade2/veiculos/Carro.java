package atividade2.veiculos;

import main.Main;
import java.util.Scanner;

public class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;
    private boolean arCondicionado;

    public Carro(String modelo, int ano, String cor, double preco,
                 int numeroPortas, String tipoCombustivel, boolean arCondicionado) {
        super(modelo, ano, cor, preco);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
        this.arCondicionado = arCondicionado;
    }

    @Override
    public void imprimirInformacoes() {
        super.imprimirInformacoes();
        System.out.printf("""
            === Especificações do Carro ===
            Número de Portas: %d
            Combustível: %s
            Ar Condicionado: %s
            """, numeroPortas, tipoCombustivel, arCondicionado ? "Instalado" : "Não instalado");
    }

    public void ligarArCondicionado() {
        if (!arCondicionado) {
            System.out.println("Este carro não possui ar condicionado!");
            return;
        }
        System.out.println("Ar condicionado ligado! Temperatura agradável.");
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        Main.limparTela();
        System.out.println("=== Cadastro de Carro ===");

        try {
            System.out.println("\nDigite o modelo:");
            String modelo = scanner.nextLine().trim();

            System.out.println("Digite o ano:");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite a cor:");
            String cor = scanner.nextLine().trim();

            System.out.println("Digite o preço:");
            double preco = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite o número de portas:");
            int numeroPortas = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o tipo de combustível:");
            String tipoCombustivel = scanner.nextLine().trim();

            System.out.println("Possui ar condicionado? (S/N):");
            boolean arCondicionado = scanner.nextLine().trim().equalsIgnoreCase("S");

            Carro carro = new Carro(modelo, ano, cor, preco, numeroPortas,
                    tipoCombustivel, arCondicionado);
            int opcao;

            do {
                Main.limparTela();
                carro.imprimirInformacoes();
                System.out.println("\n=== Operações ===");
                System.out.println("1 - Ligar Ar Condicionado");
                System.out.println("0 - Voltar");
                System.out.println("\nEscolha uma operação:");

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        carro.ligarArCondicionado();
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