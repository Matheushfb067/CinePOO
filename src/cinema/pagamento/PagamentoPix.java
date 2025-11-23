package cinema.pagamento;

public class PagamentoPix implements Pagamento {
    private String chavePix;

    public PagamentoPix(String chavepix) {
        this.chavePix = chavepix;
    }
    @Override
    public boolean pagar(double valor) {
        System.out.println("Cinema.Pagamento.CartaoDebito.Pagamento via PIX de R$" + valor + " usando chave: " + chavePix);
        return true;
    }
}