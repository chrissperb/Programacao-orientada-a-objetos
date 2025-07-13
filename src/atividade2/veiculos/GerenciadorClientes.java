package atividade2.veiculos;

import main.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static atividade2.veiculos.GerenciadorVeiculos.menuSugestaoBiotipo;

public class GerenciadorClientes {
    private final static List<Cliente> clientes = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public static void executar() {
        if (clientes.isEmpty()) {
            adicionarClientesExemplo();
        }

        int opcao;
        do {
            Main.limparTela();
            System.out.println("=== Sistema de Clientes ===");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar Novo Cliente");
            System.out.println("2 - Visualizar Todos os Clientes");
            System.out.println("3 - Buscar Cliente por ID");
            System.out.println("4 - Buscar Cliente por CPF");
            System.out.println("5 - Buscar Cliente por Nome");
            System.out.println("6 - Clientes Ativos");
            System.out.println("7 - Clientes Inativos");
            System.out.println("8 - Editar Cliente");
            System.out.println("9 - Ativar/Desativar Cliente");
            System.out.println("10 - Análise de Crédito");
            System.out.println("11 - Relatório de Clientes");
            System.out.println("12 - Sugerir Carro por biotipo do Cliente");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("\nDigite a opção desejada:");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        visualizarTodosClientes();
                        break;
                    case 3:
                        buscarClientePorId();
                        break;
                    case 4:
                        buscarClientePorCpf();
                        break;
                    case 5:
                        buscarClientePorNome();
                        break;
                    case 6:
                        visualizarClientesAtivos();
                        break;
                    case 7:
                        visualizarClientesInativos();
                        break;
                    case 8:
                        editarCliente();
                        break;
                    case 9:
                        ativarDesativarCliente();
                        break;
                    case 10:
                        analisarCredito();
                        break;
                    case 11:
                        relatorioClientes();
                        break;
                    case 12:
                        menuSugestaoBiotipo();
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

    private static void cadastrarCliente() {
        Main.limparTela();
        System.out.println("=== Cadastro de Novo Cliente ===");

        try {
            System.out.println("\nDigite o nome completo:");
            String nome = scanner.nextLine().trim();

            System.out.println("Digite o CPF (apenas números):");
            String cpf = scanner.nextLine().trim();

            System.out.println("Digite o telefone:");
            String telefone = scanner.nextLine().trim();

            System.out.println("Digite o email:");
            String email = scanner.nextLine().trim();

            System.out.println("Digite a altura (em cm):");
            int altura = scanner.nextInt();

            System.out.println("Digite o peso (em kg e sem virgulas):");
            int peso = scanner.nextInt();

            System.out.println("Digite o endereço completo:");
            String endereco = scanner.nextLine().trim();

            System.out.println("Digite a data de nascimento (dd/MM/yyyy):");
            String dataStr = scanner.nextLine().trim();
            LocalDate dataNascimento = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("Digite a profissão:");
            String profissao = scanner.nextLine().trim();

            System.out.println("Digite a renda mensal:");
            double rendaMensal = Double.parseDouble(scanner.nextLine());

            Cliente novoCliente = new Cliente(nome, cpf, telefone, email, endereco,
                    dataNascimento, profissao, rendaMensal, altura, peso, null);
            clientes.add(novoCliente);

            System.out.println("\n✅ Cliente cadastrado com sucesso!");
            novoCliente.imprimirInformacoes();

        } catch (DateTimeParseException e) {
            System.out.println("❌ Data inválida! Use o formato dd/MM/yyyy");
        } catch (NumberFormatException e) {
            System.out.println("❌ Valor numérico inválido!");
        }
    }

    private static void visualizarTodosClientes() {
        Main.limparTela();
        System.out.println("=== Todos os Clientes ===");

        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n📋 Lista de Clientes:");
        System.out.println("=".repeat(80));
        for (Cliente cliente : clientes) {
            cliente.imprimirResumo();
        }
        System.out.println("=".repeat(80));
        System.out.printf("Total de clientes: %d\n", clientes.size());
    }

    public static void buscarClientePorId() {
        Main.limparTela();
        System.out.println("=== Buscar Cliente por ID ===");

        try {
            System.out.println("\nDigite o ID do cliente:");
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = clientes.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
                cliente.imprimirInformacoes();
            } else {
                System.out.println("❌ Cliente não encontrado!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido!");
        }
    }

    public static void buscarClientePorCpf() {
        Main.limparTela();
        System.out.println("=== Buscar Cliente por CPF ===");

        System.out.println("\nDigite o CPF:");
        String cpf = scanner.nextLine().trim();

        Cliente cliente = clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            cliente.imprimirInformacoes();
        } else {
            System.out.println("❌ Cliente não encontrado!");
        }
    }

    public static void buscarClientePorNome() {
        Main.limparTela();
        System.out.println("=== Buscar Cliente por Nome ===");

        System.out.println("\nDigite o nome (ou parte do nome):");
        String nome = scanner.nextLine().trim().toLowerCase();

        List<Cliente> clientesEncontrados = clientes.stream()
                .filter(c -> c.getNome().toLowerCase().contains(nome))
                .toList();

        if (!clientesEncontrados.isEmpty()) {
            System.out.println("\n📋 Clientes encontrados:");
            System.out.println("=".repeat(80));
            for (Cliente cliente : clientesEncontrados) {
                cliente.imprimirResumo();
            }
            System.out.println("=".repeat(80));
        } else {
            System.out.println("❌ Nenhum cliente encontrado!");
        }
    }

    public static Cliente buscarClientePorIdParaSugestao() {
        System.out.println("\nDigite o ID do cliente:");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = clientes.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
                System.out.println("✅ Cliente encontrado: " + cliente.getNome());
                return cliente;
            } else {
                System.out.println("❌ Cliente não encontrado!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido!");
            return null;
        }
    }

    public static Cliente buscarClientePorCpfParaSugestao() {
        System.out.println("\nDigite o CPF do cliente:");
        String cpf = scanner.nextLine().trim();

        Cliente cliente = clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            System.out.println("✅ Cliente encontrado: " + cliente.getNome());
            return cliente;
        } else {
            System.out.println("❌ Cliente não encontrado!");
            return null;
        }
    }

    public static Cliente buscarClientePorNomeParaSugestao() {
        System.out.println("\nDigite o nome (ou parte do nome) do cliente:");
        String nome = scanner.nextLine().trim().toLowerCase();

        List<Cliente> clientesEncontrados = clientes.stream()
                .filter(c -> c.getNome().toLowerCase().contains(nome))
                .toList();

        if (clientesEncontrados.isEmpty()) {
            System.out.println("❌ Nenhum cliente encontrado!");
            return null;
        }

        if (clientesEncontrados.size() == 1) {
            Cliente cliente = clientesEncontrados.getFirst();
            System.out.println("✅ Cliente encontrado: " + cliente.getNome());
            return cliente;
        }

        System.out.println("\n📋 Múltiplos clientes encontrados:");
        System.out.println("=".repeat(80));
        for (int i = 0; i < clientesEncontrados.size(); i++) {
            Cliente cliente = clientesEncontrados.get(i);
            System.out.printf("%d. ID: %d - %s - CPF: %s\n",
                    i + 1, cliente.getId(), cliente.getNome(), cliente.getCpf());
        }
        System.out.println("=".repeat(80));

        System.out.println("\nDigite o número do cliente desejado (ou 0 para cancelar):");
        try {
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0) {
                System.out.println("Operação cancelada.");
                return null;
            }

            if (opcao > 0 && opcao <= clientesEncontrados.size()) {
                Cliente clienteSelecionado = clientesEncontrados.get(opcao - 1);
                System.out.println("✅ Cliente selecionado: " + clienteSelecionado.getNome());
                return clienteSelecionado;
            } else {
                System.out.println("❌ Opção inválida!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida!");
            return null;
        }
    }

    private static void visualizarClientesAtivos() {
        Main.limparTela();
        System.out.println("=== Clientes Ativos ===");

        List<Cliente> clientesAtivos = clientes.stream()
                .filter(Cliente::isAtivo)
                .toList();

        if (!clientesAtivos.isEmpty()) {
            System.out.println("\n📋 Clientes Ativos:");
            System.out.println("=".repeat(80));
            for (Cliente cliente : clientesAtivos) {
                cliente.imprimirResumo();
            }
            System.out.println("=".repeat(80));
            System.out.printf("Total de clientes ativos: %d\n", clientesAtivos.size());
        } else {
            System.out.println("❌ Nenhum cliente ativo encontrado!");
        }
    }

    private static void visualizarClientesInativos() {
        Main.limparTela();
        System.out.println("=== Clientes Inativos ===");

        List<Cliente> clientesInativos = clientes.stream()
                .filter(c -> !c.isAtivo())
                .toList();

        if (!clientesInativos.isEmpty()) {
            System.out.println("\n📋 Clientes Inativos:");
            System.out.println("=".repeat(80));
            for (Cliente cliente : clientesInativos) {
                cliente.imprimirResumo();
            }
            System.out.println("=".repeat(80));
            System.out.printf("Total de clientes inativos: %d\n", clientesInativos.size());
        } else {
            System.out.println("✅ Todos os clientes estão ativos!");
        }
    }

    private static void editarCliente() {
        Main.limparTela();
        System.out.println("=== Editar Cliente ===");

        try {
            System.out.println("\nDigite o ID do cliente:");
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = clientes.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (cliente == null) {
                System.out.println("❌ Cliente não encontrado!");
                return;
            }

            cliente.imprimirInformacoes();

            System.out.println("\n=== Editar Informações ===");
            System.out.println("1 - Nome");
            System.out.println("2 - Telefone");
            System.out.println("3 - Email");
            System.out.println("4 - Endereço");
            System.out.println("5 - Profissão");
            System.out.println("6 - Renda Mensal");
            System.out.println("7 - Peso");
            System.out.println("8 - Altura");
            System.out.println("9 - Estilo de Vida");
            System.out.println("0 - Cancelar");
            System.out.println("\nO que deseja editar?");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("Novo nome:");
                    cliente.setNome(scanner.nextLine().trim());
                    break;
                case 2:
                    System.out.println("Novo telefone:");
                    cliente.setTelefone(scanner.nextLine().trim());
                    break;
                case 3:
                    System.out.println("Novo email:");
                    cliente.setEmail(scanner.nextLine().trim());
                    break;
                case 4:
                    System.out.println("Novo endereço:");
                    cliente.setEndereco(scanner.nextLine().trim());
                    break;
                case 5:
                    System.out.println("Nova profissão:");
                    cliente.setProfissao(scanner.nextLine().trim());
                    break;
                case 6:
                    System.out.println("Nova renda mensal:");
                    cliente.setRendaMensal(Double.parseDouble(scanner.nextLine()));
                    break;
                case 7:
                    System.out.println("Novo peso (em kg e sem virgula):");
                    cliente.setPeso(scanner.nextInt());
                    break;
                case 8:
                    System.out.println("Nova altura:");
                    cliente.setAltura(scanner.nextInt());
                    break;
                case 9:
                    System.out.println("Novo estilo de vida:");
                    cliente.setEstiloDeVida(scanner.nextLine().trim());
                    break;
                case 0:
                    System.out.println("Operação cancelada.");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }

            System.out.println("\n✅ Cliente atualizado com sucesso!");
            cliente.imprimirInformacoes();

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida!");
        }
    }

    private static void ativarDesativarCliente() {
        Main.limparTela();
        System.out.println("=== Ativar/Desativar Cliente ===");

        try {
            System.out.println("\nDigite o ID do cliente:");
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = clientes.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (cliente == null) {
                System.out.println("❌ Cliente não encontrado!");
                return;
            }

            cliente.imprimirInformacoes();

            if (cliente.isAtivo()) {
                System.out.println("\nDeseja desativar este cliente? (S/N):");
                if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
                    cliente.desativar();
                }
            } else {
                System.out.println("\nDeseja ativar este cliente? (S/N):");
                if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
                    cliente.ativar();
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido!");
        }
    }

    private static void analisarCredito() {
        Main.limparTela();
        System.out.println("=== Análise de Crédito ===");

        try {
            System.out.println("\nDigite o ID do cliente:");
            int id = Integer.parseInt(scanner.nextLine());

            atividade2.veiculos.Cliente cliente = clientes.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (cliente == null) {
                System.out.println("❌ Cliente não encontrado!");
                return;
            }

            cliente.imprimirInformacoes();
            cliente.calcularCapacidadeFinanciamento();

            System.out.println("\nDeseja simular financiamento para um veículo? (S/N):");
            if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
                System.out.println("Digite o valor do veículo:");
                double valorVeiculo = Double.parseDouble(scanner.nextLine());

                if (cliente.podeFinanciar(valorVeiculo)) {
                    System.out.println("✅ Cliente APROVADO para financiamento!");
                } else {
                    System.out.println("❌ Cliente NÃO APROVADO para financiamento!");
                }

                double parcela = valorVeiculo / 60;
                System.out.printf("Valor da parcela (60x): R$ %.2f\n", parcela);
                System.out.printf("Percentual da renda: %.1f%%\n", (parcela / cliente.getRendaMensal()) * 100);
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida!");
        }
    }

    private static void relatorioClientes() {
        Main.limparTela();
        System.out.println("=== Relatório de Clientes ===");

        long totalClientes = clientes.size();
        long clientesAtivos = clientes.stream().filter(Cliente::isAtivo).count();
        long clientesInativos = totalClientes - clientesAtivos;

        double rendaMedia = clientes.stream()
                .mapToDouble(Cliente::getRendaMensal)
                .average()
                .orElse(0.0);

        System.out.printf("""
                
                📊 RESUMO GERAL:
                ================
                Total de Clientes: %d
                Clientes Ativos: %d
                Clientes Inativos: %d
                Renda Média: R$ %.2f
                
                """, totalClientes, clientesAtivos, clientesInativos, rendaMedia);

        if (!clientes.isEmpty()) {
            System.out.println("🏆 CLIENTE COM MAIOR RENDA:");
            clientes.stream()
                    .max(Comparator.comparingDouble(Cliente::getRendaMensal)).ifPresent(Cliente::imprimirResumo);
        }
    }

    private static void adicionarClientesExemplo() {
        clientes.add(new Cliente("João Silva Santos", "12345678901", "(11) 99999-1234",
                "joao.silva@email.com", "Rua das Flores, 123 - São Paulo/SP",
                LocalDate.of(1985, 3, 15), "Engenheiro", 8500.00, 180, 75, "Sedentario"));

        clientes.add(new Cliente("Maria Oliveira Costa", "98765432100", "(11) 88888-5678",
                "maria.costa@email.com", "Av. Paulista, 456 - São Paulo/SP",
                LocalDate.of(1990, 7, 22), "Médica", 12000.00, 165, 60, "Atleta"));

        clientes.add(new Cliente("Carlos Pereira Lima", "11122233344", "(11) 77777-9999",
                "carlos.lima@email.com", "Rua Augusta, 789 - São Paulo/SP",
                LocalDate.of(1978, 12, 8), "Advogado", 9800.00, 175, 80, "Atleta"));
    }
}