public class Cliente {
    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone;

    public Cliente( String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public void comprarIngresso(){

    }

    public void cancelarReserva(){

    }

    //Getters e setters
    public String getNome() { return nome; }
    public String getCpf() { return  cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

}
