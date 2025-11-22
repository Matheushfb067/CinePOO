public class CartaoCredito implements Cartao{
    private String numcart;
    private String validade;
    private String CVV;
    public CartaoCredito(String numcart, String validade, String CVV) {
        this.numcart = numcart;
        this.validade = validade;
        this.CVV = CVV;
    }

    public String getNumcart() {
        return numcart;
    }

    public String getValidade() {
        return validade;
    }

    public String getCVV() {
        return CVV;
    }

    @Override
    public boolean validarcartao() {
        if(numcart.length()!=16){
            return false;
        }
        if(!numcart.matches("\\d{16}")){
            return false;}
        char ultimodigito=CVV.charAt(CVV.length()-1);
        if(ultimodigito%2!=0){
            return false;
        }
        return true;

    }

    @Override
    public boolean autorizar(double valor) {

        if (validarcartao()){
            System.out.println("Cartão autorizado: R$" + valor);
            return true;
        }
        else {
            System.out.println("cartão invalido");
            return false;
        }

    }
}