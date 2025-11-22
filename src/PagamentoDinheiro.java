public class PagamentoDinheiro implements Pagamento {

    @Override
    public boolean pagar(double valor) {
        System.out.println("Pagamento em dinheiro de R$" + valor + " recebido.");
        return true;
    }
}