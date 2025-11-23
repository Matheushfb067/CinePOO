package cinema.pagamento;

public class CartaoCredito implements Cartao {

    //Uma interface não pode ter contrutor por isso deve ter seus proprios atributos
    private String numeroCartao;
    private String validade;
    private String CVV;

    public CartaoCredito(String numeroCartao, String validade, String CVV) {
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.CVV = CVV;
    }

    @Override
    public boolean validarcartao() {
        if(numeroCartao.length()!=16){
            return false;
        }
        if(!numeroCartao.matches("\\d{16}")){
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