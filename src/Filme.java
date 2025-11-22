public class Filme {

    // Atributos
    private String titulo;
    private int duracao;
    private String genero;
    private int classificacao;
    private String diretor;

    // Construtores
    public Filme(String titulo, int duracao, String genero, int classificacao, String diretor) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        this.classificacao = classificacao;
        this.diretor = diretor;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    // Exibição de dados
    public void mostrarInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Duração: " + duracao + " min");
        System.out.println("Gênero: " + genero);
        System.out.println("Classificação: " + classificacao + " anos");
        System.out.println("Diretor: " + diretor);
    }
}
