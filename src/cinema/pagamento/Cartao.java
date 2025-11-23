package cinema.pagamento;

public abstract interface Cartao {

    public abstract boolean validarcartao();
    public abstract boolean autorizar(double valor);

}