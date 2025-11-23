package cinema.pagamento;

public class PagamentoDinheiro implements Pagamento {

    @Override
    public boolean pagar(double valor) {
        System.out.println("Cinema.Pagamento.CartaoDebito.Pagamento em dinheiro de R$" + valor + " recebido.");
        return true;
    }
}