package cinema.modelo;

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
    /*Serve para garantir a segurança das threads, o synchronized serve para informar que a thread deve ser executada dentro deste bloco
    Se Alice está dentro do bloco, Bob e Carol devem esperar na porta até que Alice saia e libere a chave.*/
    public synchronized boolean ocuparAssentoPorNumero(int numeroAssento) {
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

    public boolean liberarAssentoPorNumero(int numeroAssento) {
        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[0].length; j++) {
                if (assentos[i][j] == -1) {

                    // calcular qual número deveria estar nessa posição
                    int numeroCorreto = i * assentos[0].length + j + 1;

                    if (numeroCorreto == numeroAssento) {
                        assentos[i][j] = numeroAssento; // restaurar
                        return true;
                    }
                }
            }
        }
        return false;
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

    public int getIdSala(){
        return idSala;
    }
}