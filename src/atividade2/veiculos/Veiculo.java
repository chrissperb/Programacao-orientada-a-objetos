package atividade2.veiculos;

import main.Main;

import java.util.Scanner;

public class Veiculo {
    protected String modelo;
    protected int ano;
    protected String cor;
    protected double preco;

    public Veiculo(String modelo, int ano, String cor, double preco) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
    }

    public void imprimirInformacoes() {
        System.out.printf("""
            
            === Informações do Veículo ===
            Modelo: %s
            Ano: %d
            Cor: %s
            Valor: R$ %.2f
            """, modelo, ano, cor, preco);
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        Main.limparTela();
        int opcao;

        do {
            System.out.println("=== Sistema de Veículos ===");
            System.out.println("\nEscolha o tipo de veículo:");
            System.out.println("1 - Carro");
            System.out.println("2 - Moto");
            System.out.println("3 - Caminhão");
            System.out.println("0 - Voltar");
            System.out.println("\nDigite a opção desejada:");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        Main.limparTela();
                        System.out.println("=== Cadastro de Carro ===");
                        Carro.executar();
                        break;
                    case 2:
                        Main.limparTela();
                        System.out.println("=== Cadastro de Moto ===");
                        Moto.executar();
                        break;
                    case 3:
                        Main.limparTela();
                        System.out.println("=== Cadastro de Caminhão ===");
                        Caminhao.executar();
                        break;
                    case 0:
                        System.out.println("\nVoltando ao menu principal...");
                        break;
                    default:
                        System.out.println("\nOpção inválida!");
                        break;
                }

                if (opcao != 0) {
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
                opcao = -1;
                scanner.nextLine();
            }

        } while (opcao != 0);
    }
}