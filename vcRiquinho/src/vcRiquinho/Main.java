package vcRiquinho;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Repositorio repositorio = new Repositorio();

    public static void main(String[] args) {
        System.out.println("Bem-vindo à VcRiquinho!");
        while (true) {
            int escolha = menu();
            if (escolha == 0) {
                System.out.println("Obrigado por usar o VcRiquinho! Até mais.");
                break;
            }
            
            Cliente cliente = executarOpcaoInicial(escolha);
            if (cliente == null) continue;
            
            while (true) {
                int opcaoCliente = menuCliente();
                if (opcaoCliente == 0) break;

                executaOpcaoCliente(cliente, opcaoCliente);
            }
        }
    }

    private static int menu() {
        System.out.println("\n---------- Menu -----------");
        System.out.print("\nEscolha uma opção abaixo: ");
        System.out.println("\n1. Criar conta");
        System.out.println("2. Consultar uma conta");
        System.out.println("0. Sair");
        System.out.println("\n---------------------------");
        return scanner.nextInt();
    }

    private static int menuCliente() {
        System.out.println("\n\n---------- Menu -----------");
        System.out.print("\nEscolha uma opção: ");
        System.out.println("\n1. Criar nova conta");
        System.out.println("2. Consultar suas contas");
        System.out.println("3. Acessar uma conta");
        System.out.println("0. Sair do perfil");
        System.out.println("\n---------------------------");
        return scanner.nextInt();
    }

    private static int mostrarMenuConta(Conta conta) {
        System.out.println("\n\n---------- Menu -----------");
        System.out.print("\nEscolha uma opção: ");
        System.out.println("\n1. Sacar");
        System.out.println("2. Depositar");
        if (!(conta instanceof ContaCorrente)) System.out.println("3. Simular rendimento");
        if (conta instanceof ContaInvestimentoAutomatico) System.out.println("4. Adicionar produto de investimento");
        System.out.println("0. Sair");
        System.out.println("\n---------------------------");
        return scanner.nextInt();
    }

    private static Cliente executarOpcaoInicial(int escolha) {
        scanner.nextLine(); 
        switch (escolha) {
            case 1:
                Cliente cliente = criarCliente();
                System.out.println("\nConta criada com sucesso!");
                System.out.println(cliente);
                return cliente;
            case 2:
                return localizarConta();
            default:
                System.out.println("\nOpção inválida! Escolha novamente.");
                return null;
        }
    }

    private static void executaOpcaoCliente(Cliente cliente, int opcao) {
        scanner.nextLine(); 
        switch (opcao) {
            case 1 -> {
                associarContas(cliente);
                System.out.println("Conta criada com sucesso!");
            }
            case 2 -> mostrarContas(cliente);
            case 3 -> {
                Conta conta = entrarConta(cliente);
                if (conta != null) processarConta(conta);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void processarConta(Conta conta) {
        while (true) {
            int opcaoConta = mostrarMenuConta(conta);
            if (opcaoConta == 0) break;

            switch (opcaoConta) {
                case 1 -> sacarValor(conta);
                case 2 -> depositarValor(conta);
                case 3 -> System.out.println("Rendimentos simulados: " + simularInvestimento(conta));
                case 4 -> adicionarProdutoInvestimento(conta);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static Cliente criarCliente() {
        System.out.println("\n\n---------- CADASTRO -----------");
        System.out.print("\nNome: ");
        String nome = scanner.nextLine();
        System.out.print("\nE-mail: ");
        String email = scanner.nextLine();
        System.out.println("\nTipo de conta:");
        System.out.print("Escolha: ");
        System.out.println("\n1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        int tipo = scanner.nextInt();
        System.out.println("\n-------------------------------");
        scanner.nextLine();

        Cliente cliente = (tipo == 1) ? criarPessoaFisica(nome, email) : criarPessoaJuridica(nome, email);
        Conta conta = criarConta(cliente);
        cliente.adicionarConta(conta);
        repositorio.adicionarCliente(cliente);
        return cliente;
    }

    private static Conta criarConta(Cliente cliente) {
        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("\n1. Corrente");
        System.out.println("2. Investimento Automático");
        System.out.println("3. CDI");
        System.out.print("Escolha: ");
        int tipoConta = scanner.nextInt();

        System.out.print("\nDigite o valor inicial de depósito: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();

        Conta conta = null;

        switch (tipoConta) {
            case 1:
                conta = new ContaCorrente(cliente, saldo);
                break;
            case 2:
                conta = new ContaInvestimentoAutomatico(cliente, saldo);
                break;
            case 3:
                conta = new ContaCdi(cliente, saldo);
                break;
            default:
                System.out.println("\nOpção inválida.");
                return null;
        }

        return conta; 
    }


    private static Cliente criarPessoaFisica(String nome, String email) {
        System.out.print("\nDigite seu CPF: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        return new PessoaFisica(nome, email, cpf);
    }

    private static Cliente criarPessoaJuridica(String nome, String email) {
        System.out.print("\nDigite seu CNPJ: ");
        String cnpj = scanner.nextLine();
        return new PessoaJuridica(nome, email, cnpj);
    }

    private static Cliente localizarConta() {
        System.out.print("Digite seu e-mail: ");
        String emailConta = scanner.nextLine();
        Cliente cliente = repositorio.buscarCliente(emailConta);
        if (cliente == null) System.out.println("Conta não encontrada!");
        return cliente;
    }

    private static void associarContas(Cliente cliente) {
        cliente.adicionarConta(criarConta(cliente));
    }

    private static void mostrarContas(Cliente cliente) {
        System.out.println("\n----------- Contas Disponíveis: ------------");
        for (Conta conta : cliente.getContas()) {
            System.out.println(conta.getClass().getSimpleName());
            System.out.println(conta.toString());
            System.out.println("\n------------------------------------------");
        }
    }

    private static Conta entrarConta(Cliente cliente) {
        System.out.print("\nNúmero da conta: ");
        int numeroConta = scanner.nextInt();
        return cliente.localizarConta(numeroConta);
    }

    private static void sacarValor(Conta conta) {
        System.out.print("\nValor para saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        conta.sacar(valor);
    }

    private static void depositarValor(Conta conta) {
        System.out.print("\nValor para depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        conta.depositar(valor);
    }

    private static double simularInvestimento(Conta conta) {
        System.out.print("\nPeríodo de dias desejados (30, 60, 90 ou 180): ");
        int dias = scanner.nextInt();
        scanner.nextLine();
        return conta.calcularRendimento(dias);
    }
    
    private static void adicionarProdutoInvestimento(Conta conta) {
        System.out.println("\n-- Produtos disponíveis de investimento automático: --");
        System.out.print("\nEscolha uma opção: ");
        System.out.println("\n1. Renda Fixa");
        System.out.println("2. Renda Variável");
        System.out.println("\n------------------------------------------------------");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        String descricao;
        ProdutosInvestimento produtoInvestido;

        switch (escolha) {
            case 1:
                System.out.print("\nPeríodo de carência: ");
                int periodo = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("\nRendimento mensal esperado: ");
                double rendimento = scanner.nextInt();
                scanner.nextLine();
                descricao = "Renda fixa é um tipo de investimento em que o investidor sabe previamente a taxa de rentabilidade e o prazo de aplicação.";
                produtoInvestido = new RendaFixa("Renda Fixa", descricao, rendimento, periodo);
                ((ContaInvestimentoAutomatico) conta).adicionarProduto(produtoInvestido);
                System.out.println("Produto de investimento adicionado com sucesso: " + produtoInvestido);
                break;

            case 2:
                System.out.print("Rendimento esperado: ");
                double rendimentoEsperado = scanner.nextDouble();
                scanner.nextLine();
                descricao = "Renda variável é um tipo de investimento que não garante um retorno fixo, podendo ser mais ou menos rentável.";
                produtoInvestido = new RendaVariavel("Renda Variável", descricao, rendimentoEsperado);
                ((ContaInvestimentoAutomatico) conta).adicionarProduto(produtoInvestido);
                System.out.println("Produto de investimento adicionado com sucesso: " + produtoInvestido);
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }
        
    }

}