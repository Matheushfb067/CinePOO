public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente( String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public void comprarIngresso(){
        System.out.println("O cliente " + nome + " comprou um ingresso.");
    }

    public void cancelarReserva(){
        System.out.println("Reserva cancelada para o cliente " + nome);
    }

    //Getters
    public String getNome() { return nome; }
    public String getCpf() { return  cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

}
