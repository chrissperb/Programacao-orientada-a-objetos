package atividade2.veiculos;

import main.Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GerenciadorVeiculos {
    private final static List<Veiculo> veiculos = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public static void executar() {
        // Adicionar alguns veículos de exemplo se a lista estiver vazia
        if (veiculos.isEmpty()) {
            adicionarVeiculosExemplo();
        }

        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Sistema de Veículos ===");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Visualizar Todos os Veículos");
            System.out.println("3 - Buscar Veículo por Modelo");
            System.out.println("4 - Filtrar por Tipo de Veículo");
            System.out.println("5 - Filtrar por Faixa de Preço");
            System.out.println("6 - Relatório de Veículos");
            System.out.println("7 - Ir para Sistema de Clientes");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("\nDigite a opção desejada:");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        menuCadastroVeiculo();
                        break;
                    case 2:
                        visualizarTodosVeiculos();
                        break;
                    case 3:
                        buscarVeiculoPorModelo();
                        break;
                    case 4:
                        filtrarPorTipoVeiculo();
                        break;
                    case 5:
                        filtrarPorFaixaPreco();
                        break;
                    case 6:
                        relatorioVeiculos();
                        break;
                    case 7:
                        Main.limparTela();
                        System.out.println("=== Sistema de Clientes da Concessionária ===");
                        System.out.println("Gerenciamento completo de clientes");
                        System.out.println("Pressione ENTER para acessar...");
                        scanner.nextLine();
                        GerenciadorClientes.executar();
                        return;
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
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);
    }

    private static void menuCadastroVeiculo() {
        Main.limparTela();
        System.out.println("=== Cadastro de Veículo ===");
        System.out.println("\nEscolha o tipo de veículo:");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.println("3 - Caminhão");
        System.out.println("0 - Voltar");
        System.out.println("\nDigite a opção desejada:");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarCarro();
                    break;
                case 2:
                    cadastrarMoto();
                    break;
                case 3:
                    cadastrarCaminhao();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
        }
    }

    private static void cadastrarCarro() {
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
            int portas = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o tipo de combustível:");
            String combustivel = scanner.nextLine().trim();

            System.out.println("Possui ar condicionado? (S/N):");
            boolean arCondicionado = scanner.nextLine().trim().equalsIgnoreCase("S");

            Carro carro = new Carro(modelo, ano, cor, preco, portas, combustivel, arCondicionado);
            veiculos.add(carro);

            System.out.println("\n✅ Carro cadastrado com sucesso!");
            carro.imprimirInformacoes();

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada numérica inválida!");
        }
    }

    private static void cadastrarMoto() {
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

            Moto moto = new Moto(modelo, ano, cor, preco, cilindradas, tipoPartida, bauInstalado);
            veiculos.add(moto);

            System.out.println("\n✅ Moto cadastrada com sucesso!");
            moto.imprimirInformacoes();

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada numérica inválida!");
        }
    }

    private static void cadastrarCaminhao() {
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
            int eixos = Integer.parseInt(scanner.nextLine());

            System.out.println("Possui baú refrigerado? (S/N):");
            boolean bauRefrigerado = scanner.nextLine().trim().equalsIgnoreCase("S");

            Caminhao caminhao = new Caminhao(modelo, ano, cor, preco, capacidadeCarga, eixos, bauRefrigerado);
            veiculos.add(caminhao);

            System.out.println("\n✅ Caminhão cadastrado com sucesso!");
            caminhao.imprimirInformacoes();

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada numérica inválida!");
        }
    }

    private static void visualizarTodosVeiculos() {
        Main.limparTela();
        System.out.println("=== Todos os Veículos ===");

        if (veiculos.isEmpty()) {
            System.out.println("\nNenhum veículo cadastrado.");
            return;
        }

        System.out.println("\n🚗 Lista de Veículos:");
        System.out.println("=".repeat(100));

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            String tipoVeiculo = veiculo.getClass().getSimpleName();

            System.out.printf("%d. [%s] %s %d - %s - R$ %.2f\n",
                    i + 1, tipoVeiculo, veiculo.modelo, veiculo.ano, veiculo.cor, veiculo.preco);
        }

        System.out.println("=".repeat(100));
        System.out.printf("Total de veículos: %d\n", veiculos.size());

        System.out.println("\nDeseja ver detalhes de algum veículo? (Digite o número ou 0 para sair):");
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            if (opcao > 0 && opcao <= veiculos.size()) {
                Main.limparTela();
                veiculos.get(opcao - 1).imprimirInformacoes();
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        } catch (NumberFormatException e) {
            // Ignora entrada inválida
        }
    }

    private static void buscarVeiculoPorModelo() {
        Main.limparTela();
        System.out.println("=== Buscar Veículo por Modelo ===");

        System.out.println("\nDigite o modelo (ou parte do modelo):");
        String modelo = scanner.nextLine().trim().toLowerCase();

        List<Veiculo> veiculosEncontrados = veiculos.stream()
                .filter(v -> v.modelo.toLowerCase().contains(modelo))
                .toList();

        if (!veiculosEncontrados.isEmpty()) {
            System.out.println("\n🚗 Veículos encontrados:");
            System.out.println("=".repeat(100));
            for (int i = 0; i < veiculosEncontrados.size(); i++) {
                Veiculo veiculo = veiculosEncontrados.get(i);
                String tipoVeiculo = veiculo.getClass().getSimpleName();
                System.out.printf("%d. [%s] %s %d - %s - R$ %.2f\n",
                        i + 1, tipoVeiculo, veiculo.modelo, veiculo.ano, veiculo.cor, veiculo.preco);
            }
            System.out.println("=".repeat(100));
        } else {
            System.out.println("❌ Nenhum veículo encontrado!");
        }
    }

    private static void filtrarPorTipoVeiculo() {
        Main.limparTela();
        System.out.println("=== Filtrar por Tipo de Veículo ===");

        System.out.println("\nEscolha o tipo:");
        System.out.println("1 - Carros");
        System.out.println("2 - Motos");
        System.out.println("3 - Caminhões");
        System.out.println("0 - Voltar");
        System.out.println("\nDigite a opção:");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            String tipoFiltro;

            switch (opcao) {
                case 1:
                    tipoFiltro = "Carro";
                    break;
                case 2:
                    tipoFiltro = "Moto";
                    break;
                case 3:
                    tipoFiltro = "Caminhao";
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }

            String finalTipoFiltro = tipoFiltro;
            List<Veiculo> veiculosFiltrados = veiculos.stream()
                    .filter(v -> v.getClass().getSimpleName().equals(finalTipoFiltro))
                    .toList();

            if (!veiculosFiltrados.isEmpty()) {
                System.out.println("\n🚗 " + tipoFiltro + "s encontrados:");
                System.out.println("=".repeat(100));
                for (int i = 0; i < veiculosFiltrados.size(); i++) {
                    Veiculo veiculo = veiculosFiltrados.get(i);
                    System.out.printf("%d. %s %d - %s - R$ %.2f\n",
                            i + 1, veiculo.modelo, veiculo.ano, veiculo.cor, veiculo.preco);
                }
                System.out.println("=".repeat(100));
            } else {
                System.out.println("❌ Nenhum " + tipoFiltro.toLowerCase() + " encontrado!");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida!");
        }
    }

    private static void filtrarPorFaixaPreco() {
        Main.limparTela();
        System.out.println("=== Filtrar por Faixa de Preço ===");

        try {
            System.out.println("\nDigite o preço mínimo:");
            double precoMin = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite o preço máximo:");
            double precoMax = Double.parseDouble(scanner.nextLine());

            List<Veiculo> veiculosFiltrados = veiculos.stream()
                    .filter(v -> v.preco >= precoMin && v.preco <= precoMax)
                    .toList();

            if (!veiculosFiltrados.isEmpty()) {
                System.out.printf("\n🚗 Veículos na faixa R$ %.2f - R$ %.2f:\n", precoMin, precoMax);
                System.out.println("=".repeat(100));
                for (int i = 0; i < veiculosFiltrados.size(); i++) {
                    Veiculo veiculo = veiculosFiltrados.get(i);
                    String tipoVeiculo = veiculo.getClass().getSimpleName();
                    System.out.printf("%d. [%s] %s %d - %s - R$ %.2f\n",
                            i + 1, tipoVeiculo, veiculo.modelo, veiculo.ano, veiculo.cor, veiculo.preco);
                }
                System.out.println("=".repeat(100));
            } else {
                System.out.println("❌ Nenhum veículo encontrado nesta faixa de preço!");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada numérica inválida!");
        }
    }

    private static void relatorioVeiculos() {
        Main.limparTela();
        System.out.println("=== Relatório de Veículos ===");

        if (veiculos.isEmpty()) {
            System.out.println("\nNenhum veículo cadastrado.");
            return;
        }

        long totalVeiculos = veiculos.size();
        long totalCarros = veiculos.stream().filter(v -> v instanceof Carro).count();
        long totalMotos = veiculos.stream().filter(v -> v instanceof Moto).count();
        long totalCaminhoes = veiculos.stream().filter(v -> v instanceof Caminhao).count();

        double valorTotalEstoque = veiculos.stream()
                .mapToDouble(v -> v.preco)
                .sum();

        double precoMedio = veiculos.stream()
                .mapToDouble(v -> v.preco)
                .average()
                .orElse(0.0);

        System.out.printf("""
                
                📊 RESUMO GERAL:
                ================
                Total de Veículos: %d
                • Carros: %d
                • Motos: %d
                • Caminhões: %d
                
                Valor Total do Estoque: R$ %.2f
                Preço Médio: R$ %.2f
                
                """, totalVeiculos, totalCarros, totalMotos, totalCaminhoes, valorTotalEstoque, precoMedio);

        if (!veiculos.isEmpty()) {
            System.out.println("🏆 VEÍCULO MAIS CARO:");
            veiculos.stream()
                    .max(Comparator.comparingDouble(v -> v.preco))
                    .ifPresent(v -> {
                        String tipo = v.getClass().getSimpleName();
                        System.out.printf("[%s] %s %d - R$ %.2f\n", tipo, v.modelo, v.ano, v.preco);
                    });

            System.out.println("\n💰 VEÍCULO MAIS BARATO:");
            veiculos.stream()
                    .min(Comparator.comparingDouble(v -> v.preco))
                    .ifPresent(v -> {
                        String tipo = v.getClass().getSimpleName();
                        System.out.printf("[%s] %s %d - R$ %.2f\n", tipo, v.modelo, v.ano, v.preco);
                    });
        }
    }

    private static void adicionarVeiculosExemplo() {
        veiculos.add(new Carro("Honda Civic", 2023, "Preto", 95000.00, 4, "Flex", true));
        veiculos.add(new Carro("Toyota Corolla", 2024, "Branco", 105000.00, 4, "Híbrido", true));
        veiculos.add(new Moto("Honda CB 600F", 2023, "Azul", 35000.00, 600, "Elétrica", false));
        veiculos.add(new Moto("Yamaha MT-03", 2024, "Vermelho", 28000.00, 321, "Elétrica", true));
        veiculos.add(new Caminhao("Mercedes Axor", 2022, "Branco", 280000.00, 15.0, 3, false));
        veiculos.add(new Caminhao("Volvo FH", 2023, "Azul", 450000.00, 25.0, 4, true));
    }
}