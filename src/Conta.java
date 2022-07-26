public abstract class Conta implements IConta{

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    private Cliente cliente;
    protected int agencia;
    protected int numero;
    protected double saldo;

    public Conta(Cliente cliente){
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }


    public int getAgencia(){
        return agencia;
    }

    public int getNumero(){
        return numero;
    }

    public double getSaldo(){
        return saldo;
    }

    protected void imprimirStatusConta() {
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
    }

    @Override
    public void sacar(double valor) {
        if(valor > 0 && this.getSaldo() >= valor){
            this.saldo -= valor;
        } else{
            System.out.println("Operação inválida!");
        }
    }

    @Override
    public void depositar(double valor) {
        if(valor > 0){
            this.saldo += valor;
        } else {
            System.out.println("Não é possivel depositar valores negativos!");
        }

    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if(valor > 0 && this.getSaldo() >= valor){
        this.sacar(valor);
        contaDestino.depositar(valor);
        System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Operação inválida");
        }
    }
}
