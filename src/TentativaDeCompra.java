public class TentativaDeCompra implements Runnable{
    //O runnable serve para informar ao java que a classe contem um código que pode ser executado por uma thread!
    private String nomeCliente;
    private Sala salaAlvo;
    private int numeroAssento;

    public TentativaDeCompra(String nomeCliente, Sala salaAlvo, int numeroAssento) {
        this.nomeCliente = nomeCliente;
        this.salaAlvo = salaAlvo;
        this.numeroAssento = numeroAssento;
    }

    @Override
    //Tudo o que está dentro do metodo run será executado de forma concorrente.
    public void run() {
        System.out.println(nomeCliente + " está tentando reservar o assento " + numeroAssento + " na sala " + salaAlvo.getIdSala() + "...");
        //Sincronizar o acesso ao recurso compartilhado (a sala).
        //Isso garante que apenas UMA thread por vez possa executar o bloco de código
        //que altera o estado da sala (ocuparAssentoPorNumero).
        synchronized (salaAlvo) {
            boolean sucesso = salaAlvo.ocuparAssentoPorNumero(numeroAssento);
            if (sucesso) {
                System.out.println(nomeCliente + " RESERVOU o assento " + numeroAssento + " com SUCESSO!");
            } else {
                System.out.println(nomeCliente + " FALHOU ao reservar o assento " + numeroAssento + " (já ocupado ou inválido).");
            }
        }
    }
}
