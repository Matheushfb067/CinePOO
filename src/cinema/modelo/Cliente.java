package cinema.modelo;

public class Cliente {
    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone;
    protected String tipoCliente;

    public Cliente( String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.tipoCliente = "Comum";
    }

    public double calcularDesconto(double valorBase) {
        return valorBase; // sem desconto por padrão
    }

    public void comprarIngresso(){
        System.out.println("O cliente " + nome + " comprou um ingresso.");
    }

    public void cancelarReserva(){
        System.out.println("Reserva cancelada com sucesso para o cliente: " + nome + "!");
    }

    //Getters
    public String getNome() { return nome; }
    public String getCpf() { return  cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getTipoCliente(){ return tipoCliente; }

}
