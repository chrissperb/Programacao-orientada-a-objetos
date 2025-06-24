package atividade1;

import java.util.Scanner;
import main.Main;

public class Funcionario {
    private String nome;
    private String cargo;
    private double salario;
    private int tempoServico;

    public Funcionario(String nome, String cargo, double salario, int tempoServico) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.tempoServico = tempoServico;
    }

    private double calcularAumento() {
        if (tempoServico > 5) {
            return 0.20;
        } else if (tempoServico > 3) {
            return 0.15;
        } else if (tempoServico > 1) {
            return 0.10;
        } else {
            return 0.05;
        }
    }

    public void aplicarAumento() {
        double percentual = calcularAumento();
        double valorAumento = salario * percentual;
        double novoSalario = salario + valorAumento;

        System.out.println("\n=== Cálculo de Aumento ===");
        System.out.printf("Salário atual: R$ %.2f%n", salario);
        System.out.printf("Percentual de aumento: %.1f%%%n", percentual * 100);
        System.out.printf("Valor do aumento: R$ %.2f%n", valorAumento);
        System.out.printf("Novo salário: R$ %.2f%n", novoSalario);

        this.salario = novoSalario;
    }

    public void mostrarDados() {
        System.out.println("\n=== Dados do Funcionário ===");
        System.out.println("Nome: " + nome);
        System.out.println("Cargo: " + cargo);
        System.out.printf("Salário: R$ %.2f%n", salario);
        System.out.println("Tempo de serviço: " + tempoServico + " anos");
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        Main.limparTela();
        System.out.println("=== Cadastro de Funcionário ===");

        System.out.println("\nDigite o nome do funcionário:");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome inválido!");
            return;
        }

        System.out.println("Digite o cargo do funcionário:");
        String cargo = scanner.nextLine().trim();
        if (cargo.isEmpty()) {
            System.out.println("Cargo inválido!");
            return;
        }

        try {
            System.out.println("Digite o salário do funcionário:");
            double salario = Double.parseDouble(scanner.nextLine());
            if (salario <= 0) {
                System.out.println("Salário deve ser maior que zero!");
                return;
            }

            System.out.println("Digite o tempo de serviço (em anos):");
            int tempoServico = Integer.parseInt(scanner.nextLine());
            if (tempoServico < 0) {
                System.out.println("Tempo de serviço não pode ser negativo!");
                return;
            }

            Funcionario funcionario = new Funcionario(nome, cargo, salario, tempoServico);
            int opcao;

            do {
                Main.limparTela();
                funcionario.mostrarDados();
                System.out.println("\n=== Operações ===");
                System.out.println("1 - Calcular e aplicar aumento");
                System.out.println("0 - Sair");
                System.out.println("\nEscolha uma operação:");

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        funcionario.aplicarAumento();
                        break;
                    case 0:
                        System.out.println("\nFinalizando...");
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