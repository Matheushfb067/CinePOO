import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op;

        Filme [] filmes = {
                new Filme("Wicked: Parte 2", 120, "Fantasia", 12),
                new Filme("Truque de mestre - O 3º ato", 112, "Ação", 14),
                new Filme("Zootopia 2", 107, "Animação", 10),
                new Filme("Five Nights at Freddy's 2", 104, "Terror", 16),
                new Filme("O Hobbit: A Desolação de Smaug", 186, "Fantasia", 12),
                new Filme("Invocação do Mal 4: O Ultimo Ritual", 136, "Terror", 16),
        };

        Sala [] salas = {
                new Sala(1, 120, "3D", true),
                new Sala(2, 100, "2D", true),
                new Sala(3, 140, "IMAX", true),
                new Sala(4, 80, "XD", true),
        };

        Sessao [] sessao = {
                new Sessao(1, "22/11/2025", "15h30"),
                new Sessao(2, "22/11/2025", "17:30"),
                new Sessao(3, "22/11/2025", "19:30"),
                new Sessao(4, "22/11/2025", "21:30")
        };

        Cliente cliente = new Cliente("Chris", "133.245.987-65", "chrislima@gmail.com", "7070-7070");

        //Variaveis para guardar os estados:
        Filme filmeEscolhido = null;
        Sala salaEscolhida = null;
        Sessao sessaoEscolhida = null;
        boolean ingressoComprado = false;

        do {
            System.out.println("\n===== CINE POO: Escolha uma Opção =====");
            System.out.println("1 - Filmes em Cartaz ");
            System.out.println("2 - Escolher Filme ");
            System.out.println("3 - Escolher Sala");
            System.out.println("4 - Escolher Sessão");
            System.out.println("5 - Comprar Ingressos");
            System.out.println("6 - Cancelar Reserva");
            System.out.println("0 - Sair");
            System.out.println("==========================================");

            op = entrada.nextInt();

            switch (op) {
                case 1:
                    //Filmes em Cartaz
                    System.out.println("===== Filmes em Cartaz =====");
                    for (int i = 0; i < filmes.length; i++){
                        System.out.println((i + 1) + " " + filmes[i].getTitulo());
                    }
                    System.out.println("==========================================");
                    break;

                case 2:
                    //Escolher filme
                    int escolhaFilme = -1;

                    do{
                        System.out.println("\n===== Escolha um Filme =====");

                        for (int i = 0; i < filmes.length; i++){
                            System.out.println((i + 1) + " " + filmes[i].getTitulo());
                        }

                        System.out.println("==========================================");

                        System.out.println("Digite o numero do filme: ");
                        escolhaFilme = entrada.nextInt();

                        if (escolhaFilme < 1 || escolhaFilme > filmes.length) {
                            System.out.println("Opção inválida! Tente novamente.");
                        }
                    }while(escolhaFilme < 1 || escolhaFilme > filmes.length);


                    filmeEscolhido = filmes[escolhaFilme - 1];
                    System.out.println("Filme Escolhido: " + filmeEscolhido.getTitulo());
                    break;

                case 3:
                    //Escolher sala

                    int escolhaSala;

                    System.out.println("============= Escolha um Sala ===========");

                    for (int i = 0; i < salas.length; i++) {
                        System.out.println((i+1) + " - Sala " + salas[i].getIdSala() + " (" + salas[i].getCapacidade() + " lugares)");
                    }

                    System.out.println("==========================================");

                    escolhaSala = entrada.nextInt();
                    salaEscolhida = salas[escolhaSala - 1];

                    int numAssento;

                    //Loop de Escolha do Assento
                    boolean sucesso;
                    do {

                        salaEscolhida.imprimirMapa();
                        System.out.println("Digite o numero do assento desejado: ");
                        numAssento = entrada.nextInt();

                        sucesso = salaEscolhida.ocuparAssentoPorNumero(numAssento);

                        if (!sucesso){
                            System.out.println("Assento já ocupado! Tente novamente.");
                        }
                    }while(!sucesso);
                    System.out.println("Assento reservado com sucesso!");
                    break;

                case 4:
                    //Escolher sessão


                    break;
                case 5:
                    //Cancelar ingresso

                    break;
                case 6:
                    //Cancelar Reserva
                    break;
                case 0:
                    System.out.println("Encerrando...");
                default:
                    System.out.println("Opção inválida!");
            }
        }while(op != 0);


    /*
        // ======= Cartões =======
        Cartao cartaoCredito = new CartaoCredito("1234567812345678", "12/25", "124"); // último dígito do CVV par
        Cartao cartaoDebito = new CartaoDebito("8765432187654321", "11/26", "135");   // último dígito do CVV ímpar

        Pagamento pagamentoCredito = new PagamentoCartao(cartaoCredito);
        Pagamento pagamentoDebito = new PagamentoCartao(cartaoDebito);

        // ======= PIX =======
        Pagamento pagamentoPix = new PagamentoPix("meuemail@teste.com");

        // ======= Dinheiro =======
        Pagamento pagamentoDinheiro = new PagamentoDinheiro();

        // ======= Testando pagamentos =======
        System.out.println("=== Teste Cartão de Crédito ===");
        pagamentoCredito.pagar(150.0);

        System.out.println("\n=== Teste Cartão de Débito ===");
        pagamentoDebito.pagar(80.0);

        System.out.println("\n=== Teste PIX ===");
        pagamentoPix.pagar(50.0);

        System.out.println("\n=== Teste Dinheiro ===");
        pagamentoDinheiro.pagar(30.0);
        */
    }
}
