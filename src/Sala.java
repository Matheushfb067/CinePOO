import java.util.Random;

public class Sala {
    private int idSala;
    private int capacidade;
    private String tipoSala; // 2D, 3D etc.
    private boolean status; // False = indisponível, True = disponível

    private int[][] assentos;

    public Sala(int idSala, int capacidade, String tipoSala, boolean status) {
        this.idSala = idSala;
        this.capacidade = capacidade;
        this.tipoSala = tipoSala;
        this.status = status;

        int linhas = (int)Math.sqrt(capacidade);
        int colunas = capacidade / linhas;

        assentos = new int[linhas][colunas];

        int numero = 1;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                assentos[i][j] = numero++;
            }
        }

        Random rand = new Random();
        int ocupados = capacidade / 5;

        for (int i = 0; i < ocupados; i++) {
            int r = rand.nextInt(linhas);
            int c = rand.nextInt(colunas);
            assentos[r][c] = -1;  // assento ocupado
        }
    }

    // Escolher assento pelo número real
    public boolean ocuparAssentoPorNumero(int numeroAssento) {
        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[0].length; j++) {
                if (assentos[i][j] == numeroAssento) {
                    assentos[i][j] = -1;
                    return true;
                }
            }
        }
        return false; // número não encontrado ou já ocupado
    }

    public void imprimirMapa() {
        System.out.println("========================= MAPA DA SALA " + idSala + " =========================");
        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[0].length; j++) {
                if (assentos[i][j] == -1) {
                    System.out.print("[ XX ] "); // ocupado
                } else {
                    System.out.printf("[%3d ] ", assentos[i][j]); // disponível
                }
            }
            System.out.println();
        }
        System.out.println("===================================================================");
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

    public int getIdSala(){
        return idSala;
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