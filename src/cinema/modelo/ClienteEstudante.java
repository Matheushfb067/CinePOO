package cinema.modelo;

public class ClienteEstudante extends Cliente {
    private String instituicao;
    private String matricula;

    public ClienteEstudante(String nome, String cpf, String email, String telefone, String instituicao, String matricula) {
        super(nome, cpf, email, telefone);
        this.tipoCliente = "Estudante";
        this.instituicao = instituicao;
        this.matricula = matricula;
    }

    @Override
    public double calcularDesconto(double valorBase) {
        return valorBase * 0.5; // 50% de desconto
    }

    public String getInstituicao() {
        return instituicao;
    }

    public String getMatricula() {
        return matricula;
    }
}
