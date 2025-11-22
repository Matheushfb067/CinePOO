public class Sala {
    private int idSala;
    private int capacidade;
    private String tipoSala; // 2D, 3D etc.
    private boolean status; // False = indisponível, True = disponível

    public Sala(int idSala, int capacidade, String tipoSala, boolean status) {
        this.idSala = idSala;
        this.capacidade = capacidade;
        this.tipoSala = tipoSala;
        this.status = status;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Método que retorna a porcentagem ocupada em uma sala
    public double taxaOcupacao(int ingressosVendidos) {
        double taxa;

        if(ingressosVendidos <= 0) {
            taxa = 0.0;
        }
        else{
            taxa = ((double)ingressosVendidos / getCapacidade()) * 100.0;
        }
        return taxa;
    }

    // Método que retorna quantos assentos vazios há na sala
    public int assentosVagos(int ingressosVendidos) {
        int assentosVagos = getCapacidade() - ingressosVendidos;

        return assentosVagos;
    }

    // Método que verifica a disponibilidade (status) da sala
    public void disponibilidade(boolean status) {
        if(getStatus() == false) {
            System.out.println("Sala indisponível");
        }
        else{
            System.out.println("Sala disponível");
        }
    }

    // Método que coloca a sala em manutenção (status == false / indisponível)
    public void emManutencao() {
        setStatus(false);
    }

    // Método que finaliza a manutenção da sala (status == true / disponível)
    public void fimManutencao() {
        setStatus(true);
    }
}