package cinema.modelo;

public class ClienteIdoso extends Cliente {
    private String dataNascimento;

    public ClienteIdoso(String nome, String cpf, String email, String telefone,
                        String dataNascimento) {
        super(nome, cpf, email, telefone);
        this.tipoCliente = "Idoso";
        this.dataNascimento = dataNascimento;
    }

    @Override
    public double calcularDesconto(double valorBase) {
        return valorBase * 0.5; // 50% de desconto
    }

    // Getter
    public String getDataNascimento() {
        return dataNascimento;
    }
}