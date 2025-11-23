package cinema.modelo;

public class ClienteComum extends Cliente {

    public ClienteComum(String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
        this.tipoCliente = "Comum";
    }

    @Override
    public double calcularDesconto(double valorBase) {
        return valorBase; // Sem desconto
    }
}
