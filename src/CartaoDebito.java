public class CartaoDebito extends CartaoCredito{
    public CartaoDebito(String numcart, String validade, String CVV) {
        super(numcart, validade, CVV);
    }

    @Override
    public boolean validarcartao() {
        // mantém validação do número do cartão
        if (getNumcart().length() != 16 || !getNumcart().matches("\\d{16}")) {
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
}