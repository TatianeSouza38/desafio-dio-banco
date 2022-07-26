
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    private String nome;
    static ArrayList<Conta> contas;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public static void main (String[] args){
        contas = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes(){
        System.out.println("\n======= Selecione a operação que deseja realizar =======");
        System.out.println("\n|      (1) - Criar conta        |");
        System.out.println("|      (2) - Sacar              |");
        System.out.println("|      (3) - Transferir         |");
        System.out.println("|      (4) -  Depositar         |");
        System.out.println("|      (5) -  Finalizar         |");
        System.out.println("|      (5) -  Contas         |");

        int operacao = sc.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                sacar();
                break;
            case 3:
                transferir();
                break;
            case 4:
                depositar();
                break;
            case 5:
                System.out.println("Operação finalizada!");
                System.exit(0);
                break;
            case 6:
                contasCriadas();
                break;

            default:
                System.out.println("\nOperação inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta(){
        System.out.println("\nTipo de conta");
        System.out.println("\n(1) Conta Corrente");
        System.out.println("(2) Conta Poupança");
        int tipoDeConta = sc.nextInt();

        switch (tipoDeConta) {
            case 1:
                criarContaCorrente();
                break;
            case 2:
                criarContaPoupanca();
                System.out.println("criar conta poupança");
                break;
            default:
                System.out.println("\nTipo de conta inválido!");
                criarConta();
                break;
        }
    }

    public static void criarContaCorrente(){
        System.out.println("Nome do titular: ");
        String nome = sc.next();

        System.out.println("Email: ");
        String email = sc.next();

        Cliente cliente = new Cliente(nome, email);
        Conta conta = new ContaCorrente(cliente);

        contas.add(conta);
        System.out.println("Conta criada com sucesso!");
        conta.imprimirExtrato();
        operacoes();
    }
    public static void criarContaPoupanca(){
        System.out.println("Nome do titular: ");
        String nome = sc.next();

        System.out.println("Email: ");
        String email = sc.next();

        Cliente cliente = new Cliente(nome, email);
        Conta conta = new ContaPoupanca(cliente);

        contas.add(conta);
        conta.imprimirExtrato();
        System.out.println("Conta criada com sucesso!");
        operacoes();
    }

    private static Conta encontrarConta(int numeroConta){
        Conta conta = null;
        if(contas.size() > 0){
            for(Conta c: contas) {
                if(c.getNumero() == numeroConta);
                conta = c;
            }
        }
        return conta;
    }

    private static void sacar() {
        System.out.println("Número da conta: ");
        int numeroDaConta = sc.nextInt();
        Conta conta = encontrarConta(numeroDaConta);

        if(conta != null){
            System.out.println("\nDigite o valor que deseja sacar: ");
            double valorASerSacado = sc.nextDouble();
            conta.sacar(valorASerSacado);
            conta.imprimirExtrato();
            System.out.println("Saque realizado com sucesso!");

        } else {
            System.out.println("C\nconta não encontrada");

        } operacoes();
    }

    public static void transferir(){
        System.out.println("Numero da conta do remetente: ");
        int numeroRemetente = sc.nextInt();

        Conta contaRemetente = encontrarConta(numeroRemetente);

        if(contaRemetente != null){

            System.out.println("Número da conta do destinatáiro");
            int numeroDestinatario = sc.nextInt();
            Conta contaDestinatario = encontrarConta(numeroDestinatario);

            if(contaDestinatario != null){
                System.out.println("Digite o valor a ser transferido: ");
                double valorASerTransferido = sc.nextDouble();
                contaRemetente.transferir( valorASerTransferido, contaDestinatario);
                contaRemetente.imprimirExtrato();

            } else System.out.println("Conta não encontrada!");
        } else {
            System.out.println("Conta não encontrada!");
        } operacoes();
    }

    public static void depositar(){
        System.out.println("Número da conta: ");
        int numeroConta = sc.nextInt();
        Conta conta = encontrarConta(numeroConta);

        if(conta != null){
            System.out.println("\nDigite o valor que deseja depositar: ");
            double valorASerDepositado = sc.nextDouble();
            conta.depositar(valorASerDepositado);

            System.out.println("\nDeposito realizado com sucesso!");
            conta.imprimirExtrato();
        } else {
            System.out.println("C\nconta não encontrada");

        } operacoes();
    }

    public static void contasCriadas(){
        if(contas.size() > 0){
            for(Conta conta: contas){
                System.out.println(conta);
            }
        } else System.out.println("Não existe nenhuma conta cadastrada!");
          operacoes();
    }
}



