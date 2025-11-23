package cinema.modelo;

public class Filme {

    // Atributos
    private String titulo;
    private int duracao;
    private String genero;
    private int classificacao;

    // Construtor
    public Filme(String titulo, int duracao, String genero, int classificacao ) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        this.classificacao = classificacao;
    }

    // Exibição de dados
    public void mostrarInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Duração: " + duracao + " min");
        System.out.println("Gênero: " + genero);
        System.out.println("Classificação: " + classificacao + " anos");
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }
}
