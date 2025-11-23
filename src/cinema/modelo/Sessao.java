package cinema.modelo;

public class Sessao {

    // Atributos da sessão
    private int numSessao;
    private String data;
    private String horario;

    // construtor
    public Sessao(int numSessao, String data, String horario) {
        this.numSessao = numSessao;
        this.data = data;
        this.horario = horario;
    }

    // Getters e Setters
    public int getNumSessao() {
        return numSessao;
    }

    public String getHorario() {
        return horario;
    }

}