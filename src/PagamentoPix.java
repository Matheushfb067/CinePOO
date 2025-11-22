public class PagamentoPix implements Pagamento{
    private String chavepix;

    public PagamentoPix(String chavepix) {
        this.chavepix = chavepix;
    }
    @Override
    public boolean pagar(double valor) {
        System.out.println("Pagamento via PIX de R$" + valor + " usando chave: " + chavepix);
        return true;
    }
}