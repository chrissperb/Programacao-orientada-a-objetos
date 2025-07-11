package atividade2.veiculos;

import main.Main;
import java.util.Scanner;

public class Caminhao extends Veiculo {
    private double capacidadeCarga;
    private int numeroEixos;
    private String tipoCarroceria;
    private boolean possuiGuincho;
    private boolean sistemaRastreamento;

    public Caminhao(String modelo, int ano, String cor, double preco,
                    double capacidadeCarga, int numeroEixos, String tipoCarroceria,
                    boolean possuiGuincho, boolean sistemaRastreamento) {
        super(modelo, ano, cor, preco);
        this.capacidadeCarga = capacidadeCarga;
        this.numeroEixos = numeroEixos;
        this.tipoCarroceria = tipoCarroceria;
        this.possuiGuincho = possuiGuincho;
        this.sistemaRastreamento = sistemaRastreamento;
    }

    @Override
    public void imprimirInformacoes() {
        super.imprimirInformacoes();
        System.out.printf("""
            === Especificações do Caminhão ===
            Capacidade de Carga: %.1f toneladas
            Número de Eixos: %d
            Tipo de Carroceria: %s
            Guincho: %s
            Sistema de Rastreamento: %s
            """, capacidadeCarga, numeroEixos, tipoCarroceria,
                possuiGuincho ? "Instalado" : "Não instalado",
                sistemaRastreamento ? "Ativo" : "Inativo");
    }

    public void instalarGuincho() {
        if (possuiGuincho) {
            System.out.println("Este caminhão já possui guincho instalado!");
        } else {
            possuiGuincho = true;
            System.out.println("Guincho instalado com sucesso! Capacidade adicional para reboque.");
        }
    }

    public void ativarRastreamento() {
        if (sistemaRastreamento) {
            System.out.println("Sistema de rastreamento já está ativo!");
        } else {
            sistemaRastreamento = true;
            System.out.println("Sistema de rastreamento ativado! Veículo monitorado 24h.");
        }
    }

    public void calcularCapacidadeMaxima() {
        double capacidadeMaxima = capacidadeCarga;
        if (possuiGuincho) {
            capacidadeMaxima += 2.0; // Guincho adiciona 2 toneladas de capacidade
        }

        System.out.printf("""
            === Análise de Capacidade ===
            Capacidade Base: %.1f toneladas
            Capacidade com Guincho: %.1f toneladas
            Capacidade Máxima Atual: %.1f toneladas
            """, capacidadeCarga, capacidadeCarga + 2.0, capacidadeMaxima);
    }

    public void verificarLicenciamento() {
        String tipoLicenca;
        if (capacidadeCarga <= 3.5) {
            tipoLicenca = "CNH categoria B";
        } else if (capacidadeCarga <= 6.0) {
            tipoLicenca = "CNH categoria C";
        } else {
            tipoLicenca = "CNH categoria E";
        }

        System.out.printf("""
            === Requisitos de Licenciamento ===
            Para conduzir este caminhão é necessário: %s
            Capacidade de carga: %.1f toneladas
            Número de eixos: %d
            """, tipoLicenca, capacidadeCarga, numeroEixos);
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        Main.limparTela();
        System.out.println("=== Cadastro de Caminhão ===");

        try {
            System.out.println("\nDigite o modelo:");
            String modelo = scanner.nextLine().trim();

            System.out.println("Digite o ano:");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite a cor:");
            String cor = scanner.nextLine().trim();

            System.out.println("Digite o preço:");
            double preco = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite a capacidade de carga (em toneladas):");
            double capacidadeCarga = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite o número de eixos:");
            int numeroEixos = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o tipo de carroceria (Ex: Baú, Caçamba, Sider):");
            String tipoCarroceria = scanner.nextLine().trim();

            System.out.println("Possui guincho instalado? (S/N):");
            boolean possuiGuincho = scanner.nextLine().trim().equalsIgnoreCase("S");

            System.out.println("Possui sistema de rastreamento? (S/N):");
            boolean sistemaRastreamento = scanner.nextLine().trim().equalsIgnoreCase("S");

            Caminhao caminhao = new Caminhao(modelo, ano, cor, preco, capacidadeCarga,
                    numeroEixos, tipoCarroceria, possuiGuincho, sistemaRastreamento);
            int opcao;

            do {
                Main.limparTela();
                caminhao.imprimirInformacoes();
                System.out.println("\n=== Operações ===");
                System.out.println("1 - Instalar Guincho");
                System.out.println("2 - Ativar Sistema de Rastreamento");
                System.out.println("3 - Calcular Capacidade Máxima");
                System.out.println("4 - Verificar Requisitos de Licenciamento");
                System.out.println("0 - Voltar");
                System.out.println("\nEscolha uma operação:");

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        caminhao.instalarGuincho();
                        break;
                    case 2:
                        caminhao.ativarRastreamento();
                        break;
                    case 3:
                        caminhao.calcularCapacidadeMaxima();
                        break;
                    case 4:
                        caminhao.verificarLicenciamento();
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