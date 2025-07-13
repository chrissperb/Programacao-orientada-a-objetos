package atividade2.veiculos;

import main.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static atividade2.veiculos.GerenciadorClientes.*;
import static java.util.stream.Collectors.groupingBy;

public class GerenciadorVendas {
    private final static List<Venda> vendas = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public static void executar() {
        if (vendas.isEmpty()) {
            adicionarVendasExemplo();
        }

        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Sistema de Vendas ===");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Registrar Nova Venda");
            System.out.println("2 - Visualizar Todas as Vendas");
            System.out.println("3 - Buscar Venda por ID");
            System.out.println("4 - Relatório Financeiro Geral");
            System.out.println("5 - Relatório por Período");
            System.out.println("6 - Relatório por Cliente");
            System.out.println("7 - Análise de Lucratividade");
            System.out.println("8 - Top Vendas (Maior Lucro)");
            System.out.println("9 - Vendas com Prejuízo");
            System.out.println("10 - Estatísticas Detalhadas");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("\nDigite a opção desejada:");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        registrarVenda();
                        break;
                    case 2:
                        visualizarTodasVendas();
                        break;
                    case 3:
                        buscarVendaPorId();
                        break;
                    case 4:
                        relatorioFinanceiroGeral();
                        break;
                    case 5:
                        relatorioPorPeriodo();
                        break;
                    case 6:
                        relatorioPorCliente();
                        break;
                    case 7:
                        analiseLucratividade();
                        break;
                    case 8:
                        topVendasLucro();
                        break;
                    case 9:
                        vendasComPrejuizo();
                        break;
                    case 10:
                        estatisticasDetalhadas();
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
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);
    }

    private static void registrarVenda() {
        Main.limparTela();
        System.out.println("=== Registrar Nova Venda ===");

        try {
            // Buscar cliente
            System.out.println("\n1 - Buscar cliente por ID");
            System.out.println("2 - Buscar cliente por CPF");
            System.out.println("3 - Buscar cliente por Nome");
            System.out.println("Digite a opção:");
            int opcaoCliente = Integer.parseInt(scanner.nextLine());

            Cliente cliente;
            switch (opcaoCliente) {
                case 1:
                    cliente = buscarClientePorIdParaSugestao();
                    break;
                case 2:
                    cliente = buscarClientePorCpfParaSugestao();
                    break;
                case 3:
                    cliente = buscarClientePorNomeParaSugestao();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }

            if (cliente == null) {
                System.out.println("❌ Cliente não encontrado!");
                return;
            }

            System.out.println("\n=== Informações do Veículo ===");
            System.out.println("Digite a marca e o modelo do veículo:");
            String modelo = scanner.nextLine().trim();

            System.out.println("Digite o ano do veículo:");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite a cor do veículo:");
            String cor = scanner.nextLine().trim();

            // Criar veículo temporário para a venda
            Veiculo veiculo = new Veiculo(modelo, ano, cor, 0.0);

            // Informações financeiras
            System.out.println("\n=== Informações Financeiras ===");
            System.out.println("Digite o valor de custo do veículo:");
            double valorCusto = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite o valor de venda:");
            double valorVenda = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite a forma de pagamento:");
            String formaPagamento = scanner.nextLine().trim();

            System.out.println("Digite a data da venda (dd/MM/yyyy) ou ENTER para hoje:");
            String dataStr = scanner.nextLine().trim();
            LocalDate dataVenda;
            if (dataStr.isEmpty()) {
                dataVenda = LocalDate.now();
            } else {
                dataVenda = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }

            System.out.println("Digite observações (opcional):");
            String observacoes = scanner.nextLine().trim();

            Venda novaVenda = new Venda(cliente, veiculo, valorVenda, valorCusto,
                    dataVenda, formaPagamento, observacoes);
            vendas.add(novaVenda);

            System.out.println("\n✅ Venda registrada com sucesso!");
            novaVenda.imprimirDetalhes();

        } catch (DateTimeParseException e) {
            System.out.println("❌ Data inválida! Use o formato dd/MM/yyyy");
        } catch (NumberFormatException e) {
            System.out.println("❌ Valor numérico inválido!");
        }
    }

    private static void visualizarTodasVendas() {
        Main.limparTela();
        System.out.println("=== Todas as Vendas ===");

        if (vendas.isEmpty()) {
            System.out.println("\nNenhuma venda registrada.");
            return;
        }

        System.out.println("\n📋 Lista de Vendas:");
        System.out.println("=".repeat(120));
        for (Venda venda : vendas) {
            venda.imprimirResumo();
        }
        System.out.println("=".repeat(120));
        System.out.printf("Total de vendas: %d\n", vendas.size());

        double totalVendas = vendas.stream().mapToDouble(Venda::getValorVenda).sum();
        double totalCustos = vendas.stream().mapToDouble(Venda::getValorAquisicao).sum();
        double lucroTotal = totalVendas - totalCustos;

        System.out.print("\n💰 RESUMO FINANCEIRO:\n");
        System.out.printf("Total em Vendas: R$ %.2f\n", totalVendas);
        System.out.printf("Total em Custos: R$ %.2f\n", totalCustos);
        System.out.printf("Lucro Total: R$ %.2f\n", lucroTotal);
    }

    private static void buscarVendaPorId() {
        Main.limparTela();
        System.out.println("=== Buscar Venda por ID ===");

        try {
            System.out.println("\nDigite o ID da venda:");
            int id = Integer.parseInt(scanner.nextLine());

            Venda venda = vendas.stream()
                    .filter(v -> v.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (venda != null) {
                venda.imprimirDetalhes();
            } else {
                System.out.println("❌ Venda não encontrada!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido!");
        }
    }

    private static void relatorioFinanceiroGeral() {
        Main.limparTela();
        System.out.println("=== Relatório Financeiro Geral ===");

        if (vendas.isEmpty()) {
            System.out.println("\nNenhuma venda registrada.");
            return;
        }

        double totalVendas = vendas.stream().mapToDouble(Venda::getValorVenda).sum();
        double totalCustos = vendas.stream().mapToDouble(Venda::getValorAquisicao).sum();
        double lucroTotal = totalVendas - totalCustos;
        double margemMedia = vendas.stream().mapToDouble(Venda::calcularMargemLucro).average().orElse(0);

        long vendasComLucro = vendas.stream().filter(Venda::temLucro).count();
        long vendasComPrejuizo = vendas.stream().filter(v -> v.calcularLucro() < 0).count();
        long vendasNeutras = vendas.size() - vendasComLucro - vendasComPrejuizo;

        System.out.printf("""
                        
                        💰 RESUMO FINANCEIRO GERAL
                        ==========================
                        Total de Vendas: %d
                        
                        📊 VALORES:
                        - Faturamento Total: R$ %.2f
                        - Custos Totais: R$ %.2f
                        - Lucro/Prejuízo Total: R$ %.2f
                        - Margem Média: %.1f%%
                        
                        📈 ANÁLISE DE DESEMPENHO:
                        - Vendas com Lucro: %d (%.1f%%)
                        - Vendas com Prejuízo: %d (%.1f%%)
                        - Vendas Neutras: %d (%.1f%%)
                        
                        """,
                vendas.size(),
                totalVendas, totalCustos, lucroTotal, margemMedia,
                vendasComLucro, (vendasComLucro * 100.0 / vendas.size()),
                vendasComPrejuizo, (vendasComPrejuizo * 100.0 / vendas.size()),
                vendasNeutras, (vendasNeutras * 100.0 / vendas.size()));

        if (lucroTotal > 0) {
            System.out.println("✅ RESULTADO: LUCRO GERAL");
        } else if (lucroTotal < 0) {
            System.out.println("❌ RESULTADO: PREJUÍZO GERAL");
        } else {
            System.out.println("⚪ RESULTADO: NEUTRO");
        }
    }

    private static void relatorioPorPeriodo() {
        Main.limparTela();
        System.out.println("=== Relatório por Período ===");

        try {
            System.out.println("\nDigite a data inicial (dd/MM/yyyy):");
            String dataInicialStr = scanner.nextLine().trim();
            LocalDate dataInicial = LocalDate.parse(dataInicialStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("Digite a data final (dd/MM/yyyy):");
            String dataFinalStr = scanner.nextLine().trim();
            LocalDate dataFinal = LocalDate.parse(dataFinalStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            List<Venda> vendasPeriodo = vendas.stream()
                    .filter(v -> !v.getDataVenda().isBefore(dataInicial) && !v.getDataVenda().isAfter(dataFinal))
                    .toList();

            if (vendasPeriodo.isEmpty()) {
                System.out.println("❌ Nenhuma venda encontrada no período!");
                return;
            }

            System.out.printf("\n📅 PERÍODO: %s a %s\n",
                    dataInicial.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    dataFinal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("=".repeat(80));

            for (Venda venda : vendasPeriodo) {
                venda.imprimirResumo();
            }

            System.out.println("=".repeat(80));

            double totalVendas = vendasPeriodo.stream().mapToDouble(Venda::getValorVenda).sum();
            double totalCustos = vendasPeriodo.stream().mapToDouble(Venda::getValorAquisicao).sum();
            double lucroTotal = totalVendas - totalCustos;

            System.out.print("\n💰 RESUMO DO PERÍODO:\n");
            System.out.printf("Vendas: %d\n", vendasPeriodo.size());
            System.out.printf("Faturamento: R$ %.2f\n", totalVendas);
            System.out.printf("Custos: R$ %.2f\n", totalCustos);
            System.out.printf("Resultado: R$ %.2f\n", lucroTotal);

        } catch (DateTimeParseException e) {
            System.out.println("❌ Data inválida! Use o formato dd/MM/yyyy");
        }
    }

    private static void relatorioPorCliente() {
        Main.limparTela();
        System.out.println("=== Relatório por Cliente ===");

        Map<String, List<Venda>> vendasPorCliente = vendas.stream()
                .collect(groupingBy(v -> v.getCliente().getNome()));

        if (vendasPorCliente.isEmpty()) {
            System.out.println("❌ Nenhuma venda registrada!");
            return;
        }

        System.out.println("\n👥 VENDAS POR CLIENTE:");
        System.out.println("=".repeat(100));

        for (Map.Entry<String, List<Venda>> entry : vendasPorCliente.entrySet()) {
            String nomeCliente = entry.getKey();
            List<Venda> vendasCliente = entry.getValue();

            double totalVendas = vendasCliente.stream().mapToDouble(Venda::getValorVenda).sum();
            double totalLucro = vendasCliente.stream().mapToDouble(Venda::calcularLucro).sum();

            System.out.printf("\n👤 %s\n", nomeCliente);
            System.out.printf("   Vendas: %d | Faturamento: R$ %.2f | Lucro: R$ %.2f\n",
                    vendasCliente.size(), totalVendas, totalLucro);

            for (Venda venda : vendasCliente) {
                System.out.printf("   - %s\n", venda.toString());
            }
        }

        System.out.println("=".repeat(100));
    }

    private static void analiseLucratividade() {
        Main.limparTela();
        System.out.println("=== Análise de Lucratividade ===");

        if (vendas.isEmpty()) {
            System.out.println("❌ Nenhuma venda registrada!");
            return;
        }

        List<Venda> vendasOrdenadas = vendas.stream()
                .sorted((v1, v2) -> Double.compare(v2.calcularLucro(), v1.calcularLucro()))
                .toList();

        System.out.println("\n📊 ANÁLISE DE LUCRATIVIDADE:");
        System.out.println("=".repeat(120));

        for (Venda venda : vendasOrdenadas) {
            double lucro = venda.calcularLucro();
            double margem = venda.calcularMargemLucro();
            String status = lucro > 0 ? "✅" : (lucro < 0 ? "❌" : "⚪");

            System.out.printf("%s ID: %d | %s | Venda: R$ %.2f | Lucro: R$ %.2f (%.1f)\n",
                    status, venda.getId(), venda.getVeiculo().getModelo(), venda.getValorVenda(), lucro, margem);
        }

        System.out.println("=".repeat(120));

        OptionalDouble maiorLucro = vendas.stream().mapToDouble(Venda::calcularLucro).max();
        OptionalDouble menorLucro = vendas.stream().mapToDouble(Venda::calcularLucro).min();
        double lucroMedio = vendas.stream().mapToDouble(Venda::calcularLucro).average().orElse(0);

        System.out.print("\n📈 ESTATÍSTICAS:\n");
        System.out.printf("Maior Lucro: R$ %.2f\n", maiorLucro.orElse(0));
        System.out.printf("Menor Lucro: R$ %.2f\n", menorLucro.orElse(0));
        System.out.printf("Lucro Médio: R$ %.2f\n", lucroMedio);
    }

    private static void topVendasLucro() {
        Main.limparTela();
        System.out.println("=== Top 10 Vendas com Maior Lucro ===");

        List<Venda> topVendas = vendas.stream()
                .filter(Venda::temLucro)
                .sorted((v1, v2) -> Double.compare(v2.calcularLucro(), v1.calcularLucro()))
                .limit(10)
                .toList();

        if (topVendas.isEmpty()) {
            System.out.println("❌ Nenhuma venda com lucro encontrada!");
            return;
        }

        System.out.println("\n🏆 TOP VENDAS COM LUCRO:");
        System.out.println("=".repeat(120));

        for (int i = 0; i < topVendas.size(); i++) {
            Venda venda = topVendas.get(i);
            System.out.printf("%d. ", i + 1);
            venda.imprimirResumo();
        }

        System.out.println("=".repeat(120));
    }

    private static void vendasComPrejuizo() {
        Main.limparTela();
        System.out.println("=== Vendas com Prejuízo ===");

        List<Venda> vendasPrejuizo = vendas.stream()
                .filter(v -> v.calcularLucro() < 0)
                .sorted(Comparator.comparingDouble(Venda::calcularLucro))
                .toList();

        if (vendasPrejuizo.isEmpty()) {
            System.out.println("✅ Nenhuma venda com prejuízo encontrada!");
            return;
        }

        System.out.println("\n⚠️ VENDAS COM PREJUÍZO:");
        System.out.println("=".repeat(120));

        for (Venda venda : vendasPrejuizo) {
            venda.imprimirResumo();
        }

        System.out.println("=".repeat(120));

        double prejuizoTotal = vendasPrejuizo.stream().mapToDouble(Venda::calcularLucro).sum();
        System.out.printf("💸 PREJUÍZO TOTAL: R$ %.2f\n", Math.abs(prejuizoTotal));
    }

    private static void estatisticasDetalhadas() {
        Main.limparTela();
        System.out.println("=== Estatísticas Detalhadas ===");

        if (vendas.isEmpty()) {
            System.out.println("❌ Nenhuma venda registrada!");
            return;
        }

        DoubleSummaryStatistics statsVenda = vendas.stream()
                .mapToDouble(Venda::getValorVenda)
                .summaryStatistics();

        DoubleSummaryStatistics statsLucro = vendas.stream()
                .mapToDouble(Venda::calcularLucro)
                .summaryStatistics();

        DoubleSummaryStatistics statsMargem = vendas.stream()
                .mapToDouble(Venda::calcularMargemLucro)
                .summaryStatistics();

        System.out.printf("""
                        
                        📊 ESTATÍSTICAS COMPLETAS
                        =========================
                        
                        💰 VALORES DE VENDA:
                        - Maior Venda: R$ %.2f
                        - Menor Venda: R$ %.2f
                        - Venda Média: R$ %.2f
                        - Total Vendido: R$ %.2f
                        
                        📈 LUCRO/PREJUÍZO:
                        - Maior Lucro: R$ %.2f
                        - Menor Resultado: R$ %.2f
                        - Lucro Médio: R$ %.2f
                        - Lucro Total: R$ %.2f
                        
                        📊 MARGEM DE LUCRO:
                        - Maior Margem: %.1f%%
                        - Menor Margem: %.1f%%
                        - Margem Média: %.1f%%
                        
                        """,
                statsVenda.getMax(), statsVenda.getMin(), statsVenda.getAverage(), statsVenda.getSum(),
                statsLucro.getMax(), statsLucro.getMin(), statsLucro.getAverage(), statsLucro.getSum(),
                statsMargem.getMax(), statsMargem.getMin(), statsMargem.getAverage());

        Map<String, List<Venda>> vendasPorPagamento = vendas.stream()
                .collect(groupingBy(Venda::getFormaPagamento));

        System.out.println("💳 ANÁLISE POR FORMA DE PAGAMENTO:");
        for (Map.Entry<String, List<Venda>> entry : vendasPorPagamento.entrySet()) {
            String forma = entry.getKey();
            List<Venda> vendasForma = entry.getValue();
            double totalForma = vendasForma.stream().mapToDouble(Venda::getValorVenda).sum();
            double lucroForma = vendasForma.stream().mapToDouble(Venda::calcularLucro).sum();

            System.out.printf("- %s: %d vendas | Total: R$ %.2f | Lucro: R$ %.2f\n",
                    forma, vendasForma.size(), totalForma, lucroForma);
        }

        Map<String, List<Venda>> vendasPorMarca = vendas.stream()
                .collect(groupingBy(v -> v.getVeiculo().getModelo()));

        System.out.println("\n🚗 ANÁLISE POR MARCA DE VEÍCULO:");
        for (Map.Entry<String, List<Venda>> entry : vendasPorMarca.entrySet()) {
            String marca = entry.getKey();
            List<Venda> vendasMarca = entry.getValue();
            double totalMarca = vendasMarca.stream().mapToDouble(Venda::getValorVenda).sum();
            double lucroMarca = vendasMarca.stream().mapToDouble(Venda::calcularLucro).sum();

            System.out.printf("- %s: %d vendas | Total: R$ %.2f | Lucro: R$ %.2f\n",
                    marca, vendasMarca.size(), totalMarca, lucroMarca);
        }
    }

    private static void adicionarVendasExemplo() {
        Cliente cliente1 = new Cliente("João Silva", "12345678901", "(11) 99999-1234",
                "joao@email.com", "Rua A, 123", LocalDate.of(1985, 3, 15),
                "Engenheiro", 8500.00, 180, 75, "Sedentário");

        Cliente cliente2 = new Cliente("Maria Santos", "98765432100", "(11) 88888-5678",
                "maria@email.com", "Rua B, 456", LocalDate.of(1990, 7, 22),
                "Médica", 12000.00, 165, 60, "Atleta");

        Veiculo veiculo1 = new Veiculo("Honda Civic", 2020, "Preto", 85000.0);
        Veiculo veiculo2 = new Veiculo("Toyota Corolla", 2019, "Branco", 78000.0);
        Veiculo veiculo3 = new Veiculo("Ford Focus", 2021, "Azul", 92000.0);

        vendas.add(new Venda(cliente1, veiculo1, 87000.0, 80000.0,
                LocalDate.of(2024, 1, 15), "Financiamento", "Primeira venda do ano"));

        vendas.add(new Venda(cliente2, veiculo2, 76000.0, 75000.0,
                LocalDate.of(2024, 2, 10), "À Vista", "Cliente médica"));

        vendas.add(new Venda(cliente1, veiculo3, 89000.0, 95000.0,
                LocalDate.of(2024, 3, 5), "Financiamento", "Venda com prejuízo"));
    }
}