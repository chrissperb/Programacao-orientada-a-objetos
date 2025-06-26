package main;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = menuPrincipal();
            if (opcao != 0) {
                menuAtividade(opcao);
            }
        } while (opcao != 0);

        System.out.println("Obrigado por avaliar esta atividade!");
        scanner.close();
    }

    private static int menuPrincipal() {
        limparTela();
        System.out.println("==============================================");
        System.out.println("Universidade Unyleya");
        System.out.println("Curso Analise e Desenvolvimento de Sistemas");
        System.out.println("Disciplina: Programacao Orientada a Objetos");
        System.out.println("Escolha uma opcao:");
        System.out.println("1 - Atividade 1");
        System.out.println("2 - Atividade 2");
        System.out.println("3 - Atividade 3");
        System.out.println("4 - Atividade Final");
        System.out.println("0 - Sair");
        System.out.println("==============================================");
        System.out.println("Digite a opcao desejada:");

        return lerOpcao();
    }

    private static void menuAtividade(int atividade) {
        limparTela();
        int opcao;
        do {
            System.out.println("==============================================");
            System.out.println("Universidade Unyleya");
            System.out.println("Curso Analise e Desenvolvimento de Sistemas");
            System.out.println("Disciplina: Programacao Orientada a Objetos");
            System.out.println("\nAtividade " + atividade + ":");

            switch (atividade) {
                case 1:
                    System.out.println("1 - Animal");
                    System.out.println("2 - Carro");
                    System.out.println("3 - Circulo");
                    System.out.println("4 - ContaBancaria");
                    System.out.println("5 - Funcionario");
                    System.out.println("6 - Livro");
                    System.out.println("7 - Pessoa");
                    System.out.println("8 - Produto");
                    System.out.println("9 - Retangulo");
                    System.out.println("10 - Triangulo");
                    break;
                case 2:
                    System.out.println("1 - Autenticador");
                    System.out.println("2 - Animais");
                    System.out.println("3 - Calculadora");
                    System.out.println("4 - ContaBancaria");
                    System.out.println("5 - FormaGeometrica");
                    System.out.println("6 - Veiculo");
                    break;
                case 3:
                    System.out.println("1 - Calculadora");
                    System.out.println("2 - ContaBancaria");
                    System.out.println("3 - ConversorStrinngParaInteger");
                    System.out.println("4 - ConversorTemperatura");
                    System.out.println("5 - DivisaoInteira");
                    System.out.println("6 - DivisaoPorZero");
                    System.out.println("7 - LeitorDeArquivo");
                    System.out.println("8 - SaldoInsuficiente");
                    System.out.println("9 - ValidadorSenha");
                    break;
                case 4:
                    System.out.println("1 - Projeto Final");
                    break;
            }
            System.out.println("0 - Voltar ao menu principal");
            System.out.println("==============================================");
            System.out.println("Digite a opcao desejada:");

            opcao = lerOpcao();
            if (opcao != 0) {
                executarClasse(atividade, opcao);
            }
        } while (opcao != 0);
    }

    private static void executarClasse(int atividade, int opcao) {
        limparTela();
        System.out.println("\nExecutando Atividade " + atividade + " - Opcao " + opcao);

        switch (atividade) {
            case 1:
                switch (opcao) {
                    case 1:
                        System.out.println("Executando classe Animal...");
                        atividade1.Animal.executar();
                        break;
                    case 2:
                        System.out.println("Executando classe Carro...");
                        atividade1.Carro.executar();
                        break;
                    case 3:
                        System.out.println("Executando classe Circulo...");
                        atividade1.Circulo.executar();
                        break;
                    case 4:
                        System.out.println("Executando classe ContaBancaria...");
                        atividade1.ContaBancaria.executar();
                        break;
                    case 5:
                        System.out.println("Executando classe Funcionario...");
                        atividade1.Funcionario.executar();
                        break;
                    case 6:
                        System.out.println("Executando classe Livro...");
                        atividade1.Livro.executar();
                        break;
                    case 7:
                        System.out.println("Executando classe Pessoa...");
                        atividade1.Pessoa.executar();
                        break;
                    case 8:
                        System.out.println("Executando classe Produto...");
                        atividade1.Produto.executar();
                        break;
                    case 9:
                        System.out.println("Executando classe Retangulo...");
                        atividade1.Retangulo.executar();
                        break;
                    case 10:
                        System.out.println("Executando classe Triangulo...");
                        atividade1.Triangulo.executar();
                        break;
                }
                break;
            case 2:
                switch (opcao){
 /*                   case 1:
                        System.out.println("Executando classe Autenticador...");
                        atividade2.Autenticador.executar();
                        break;*/
                    case 2:
                        System.out.println("Executando classe Animais...");
                        atividade2.animais.Animais.executar();
                        break;
                    case 3:
                        System.out.println("Executando classe Calculadora...");
                        atividade2.Calculadora.executar();
                        break;
                    case 4:
                        System.out.println("Executando classe ContaBancaria...");
                        atividade2.contabancaria.ContaBancaria.executar();
                        break;
/*                    case 5:
                        System.out.println("Executando classe FormaGeometrica...");
                        atividade2.FormaGeometrica.executar();
                        break;*/
                    case 6:
                        System.out.println("Executando classe Veiculo...");
                        atividade2.veiculos.Veiculo.executar();
                        break;
                }
        }

        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida! Digite um numero.");
            return -1;
        }
    }

    public static void limparTela() {
        {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}