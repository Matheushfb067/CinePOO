package cinema.pagamento;

public class CartaoDebito implements Cartao {

    //Uma interface não pode ter contrutor por isso deve ter seus proprios atributos
    private String numeroCartao;
    private String validade;
    private String CVV;

    public CartaoDebito(String numeroCartao, String validade, String CVV) {
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.CVV = CVV;
    }

    @Override
    public boolean validarcartao() {
        // mantém validação do número do cartão
        if (getNumeroCartao().length() != 16 || !getNumeroCartao().matches("\\d{16}")) {
            return false;
        }

        // valida último dígito do CVV como ímpar
        char ultimoDigito = getCVV().charAt(getCVV().length() - 1);
        int ultimoNumero = Character.getNumericValue(ultimoDigito);
        if (ultimoNumero % 2 == 0) { // agora é inválido se for par
            return false;
        }

        return true;
    }

    @Override
    public boolean autorizar(double valor) {
        if (validarcartao()) {
            System.out.println("Cartão de débito autorizado: R$" + valor);
            return true;
        }
        System.out.println("Cartão de débito inválido.");
        return false;
    }

    public String getNumeroCartao(){
        return numeroCartao;
    }

    public String getValidade(){
        return validade;
    }

    public String getCVV(){
        return CVV;
    }


}