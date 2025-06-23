package atividade1;

import java.util.Scanner;
import main.Main;

public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private boolean ligado;
    private int velocidade;

    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.ligado = false;
        this.velocidade = 0;
    }

    public void ligar() {
        if (!ligado) {
            ligado = true;
            System.out.println("O carro foi ligado. Vrum vrum!");
        } else {
            System.out.println("O carro já está ligado!");
        }
    }

    public void desligar() {
        if (ligado) {
            if (velocidade == 0) {
                ligado = false;
                System.out.println("O carro foi desligado.");
            } else {
                System.out.println("Pare o carro antes de desligar!");
            }
        } else {
            System.out.println("O carro já está desligado!");
        }
    }

    public void acelerar() {
        if (ligado) {
            velocidade += 10;
            System.out.printf("Acelerando... Velocidade atual: %d km/h%n", velocidade);
        } else {
            System.out.println("O carro precisa estar ligado para acelerar!");
        }
    }

    public void frear() {
        if (velocidade > 0) {
            velocidade -= 10;
            System.out.printf("Freando... Velocidade atual: %d km/h%n", velocidade);
        } else {
            System.out.println("O carro já está parado!");
        }
    }

    public void mostrarDados() {
        System.out.println("\n=== Dados do Carro ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Status: " + (ligado ? "Ligado" : "Desligado"));
        System.out.println("Velocidade: " + velocidade + " km/h");
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        Main.limparTela();
        System.out.println("=== Simulador de Carro ===");

        System.out.println("\nDigite a marca do carro:");
        String marca = scanner.nextLine().trim();
        if (marca.isEmpty()) {
            System.out.println("Marca inválida!");
            return;
        }

        System.out.println("Digite o modelo do carro:");
        String modelo = scanner.nextLine().trim();
        if (modelo.isEmpty()) {
            System.out.println("Modelo inválido!");
            return;
        }

        try {
            System.out.println("Digite o ano do carro:");
            int ano = Integer.parseInt(scanner.nextLine());
            if (ano < 1886 || ano > 2024) {
                System.out.println("Ano inválido!");
                return;
            }

            Carro carro = new Carro(marca, modelo, ano);
            int opcao;

            do {
                Main.limparTela();
                carro.mostrarDados();
                System.out.println("\n=== Operações ===");
                System.out.println("1 - Ligar");
                System.out.println("2 - Desligar");
                System.out.println("3 - Acelerar");
                System.out.println("4 - Frear");
                System.out.println("0 - Sair");
                System.out.println("\nEscolha uma operação:");

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> carro.ligar();
                    case 2 -> carro.desligar();
                    case 3 -> carro.acelerar();
                    case 4 -> carro.frear();
                    case 0 -> System.out.println("\nEncerrando simulação...");
                    default -> System.out.println("\nOpção inválida!");
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