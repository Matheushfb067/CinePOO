public class Sessao {

    // Atributos da sessão
    protected int numSessao;
    protected String data;
    protected String horario;

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

    public void setNumSessao(int numSessao) {
        this.numSessao = numSessao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    // facilitando exibicao e testes
    @Override
    public String toString() {
        return "Sessao{" +
                "numSessao=" + numSessao +
                ", data='" + data + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}