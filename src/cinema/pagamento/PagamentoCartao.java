package cinema.pagamento;

public class PagamentoCartao implements Pagamento {

    private Cartao cartao;

    public PagamentoCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public boolean pagar(double valor) {
        return cartao.autorizar(valor);
    }

    public void setCartao(Cartao cartao){
        this.cartao=cartao;
    }
}