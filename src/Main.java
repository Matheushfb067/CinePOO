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
        int assentoReservado = -1;


        do {
            System.out.println("===== CINE POO: Escolha uma Opção =====");
            System.out.println("1 - Filmes em Cartaz ");
            System.out.println("2 - Escolher Filme ");
            System.out.println("3 - Mostrar Informações do Filme");
            System.out.println("4 - Escolher Sala");
            System.out.println("5 - Escolher Sessão");
            System.out.println("6 - Comprar Ingressos");
            System.out.println("7 - Cancelar Reserva");
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
                        System.out.println("===== Escolha um Filme =====");

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
                    //Mostrar informações do filme
                    if (filmeEscolhido == null) {
                        System.out.println("Nenhum filme selecionado!");
                    } else {
                        System.out.println("===== INFORMAÇÕES DO FILME =====");
                        filmeEscolhido.mostrarInfo();
                        System.out.println("================================");
                    }
                    break;
                case 4:
                    //Escolher sala

                    int escolhaSala;

                    System.out.println("============= Escolha um Sala ===========");

                    for (int i = 0; i < salas.length; i++) {
                        System.out.println((i+1) + " Sala " + salas[i].getIdSala() + " (" + salas[i].getCapacidade() + " lugares)");
                    }

                    System.out.println("==========================================");

                    escolhaSala = entrada.nextInt();
                    entrada.nextLine(); // evitar pular entrada
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

                    //salvar automaticamente o assento escolhido
                    assentoReservado = numAssento;

                    System.out.println("Assento reservado com sucesso!");
                    break;

                case 5:
                    //Escolher sessão
                    int escolhaSessao = -1;

                    do{
                        System.out.println("============= Escolha um Sessão ===========");
                        for (int i = 0; i < sessao.length; i++){
                            System.out.println((i + 1) + " Sessão " + sessao[i].getNumSessao() + " | " + sessao[i].getHorario());
                        }

                        System.out.println("==========================================");

                        System.out.println("Digite o numero da sessão: ");
                        escolhaSessao = entrada.nextInt();

                        if (escolhaSessao < 1 || escolhaSessao > sessao.length){
                            System.out.println("Sessão invalida! Tente novamente.");
                        }
                    }while(escolhaSessao < 1 || escolhaSessao > sessao.length);
                    sessaoEscolhida = sessao[escolhaSessao - 1];
                    System.out.println("Sessão Escolhida: " + sessaoEscolhida.getHorario());
                    break;

                case 6:
                    //Comprar ingresso
                    while(filmeEscolhido == null || salaEscolhida == null || sessaoEscolhida == null){
                        System.out.println("Você ainda não selecionou todas as opções!");

                        if (filmeEscolhido == null)
                            System.out.println("- Escolha um FILME (opção 1 no menu)");
                        if (salaEscolhida == null)
                            System.out.println("- Escolha uma SALA (opção 2 no menu)");
                        if (sessaoEscolhida == null)
                            System.out.println("- Escolha uma SESSÃO (opção 3 no menu)");

                        System.out.println("Retornando ao menu principal...");
                        break;
                    }

                    if(filmeEscolhido == null || salaEscolhida == null || sessaoEscolhida == null){
                        break;
                    }

                    System.out.println("===== RESUMO DA COMPRA =====");
                    System.out.println("Filme: " + filmeEscolhido.getTitulo());
                    System.out.println("Sala: " + salaEscolhida.getIdSala());
                    System.out.println("Sessão: " + sessaoEscolhida.getHorario());
                    System.out.println("Assento reservado!");
                    System.out.println("==========================================");

                    System.out.println("====== Forma de Pagamento ======");
                    System.out.println("1 - Cartão de Crédito");
                    System.out.println("2 - Cartão de Débito");
                    System.out.println("3 - PIX");
                    System.out.println("4 - Dinheiro");
                    System.out.print("Escolha uma das opções: ");

                    int tipoPagamento = entrada.nextInt();
                    entrada.nextLine(); // limpar buffer
                    Pagamento pagamento = null;

                    /*Os cases devem ficar dentro de chaves já que os atributos criados dentro dos cases
                    serão utilizados em mais de um case por isso é necessaria a diferença de escopo delimitada
                    por {}*/
                    switch (tipoPagamento){
                        case 1: {
                            System.out.println("Número do Cartão de Crédito: ");
                            String num = entrada.next();

                            System.out.println("Validade (MM/AA): ");
                            String val = entrada.next();

                            System.out.println("CVV: ");
                            String cvv = entrada.next();

                            Cartao credito = new CartaoCredito(num, val, cvv);
                            pagamento = new PagamentoCartao(credito);
                            break;
                        }
                        case 2: {
                            System.out.println("Número do Cartão de Débito: ");
                            String num = entrada.next();

                            System.out.println("Validade (MM/AA): ");
                            String val = entrada.next();

                            System.out.println("CVV: ");
                            String cvv = entrada.next();

                            Cartao debito = new CartaoDebito(num, val, cvv);
                            pagamento = new PagamentoCartao(debito);
                            break;
                        }
                        case 3: {
                            System.out.println("Digite a chave PIX: ");
                            String chave = entrada.nextLine();
                            pagamento = new PagamentoPix(chave);
                            break;
                        }
                        case 4:
                            pagamento = new PagamentoDinheiro();
                            break;
                        default:
                            System.out.println("Forma de Pagamento Invalida!");
                            break;
                    }

                    boolean sucessoPagamento = pagamento.pagar(45.0); // ex: valor fixo ou variável
                    if (sucessoPagamento) {
                        ingressoComprado = true;
                        System.out.println("Pagamento realizado com sucesso!");
                        cliente.comprarIngresso();
                    } else {
                        System.out.println("Pagamento recusado!");
                    }

                    break;
                case 7:
                    //Cancelar Reserva
                    if (filmeEscolhido == null || salaEscolhida == null || sessaoEscolhida == null) {
                        System.out.println("Nenhuma reserva encontrada para cancelar!");
                        break;
                    }

                    System.out.println("===== CONFIRMAR CANCELAMENTO =====");
                    System.out.println("Filme: " + filmeEscolhido.getTitulo());
                    System.out.println("Sala: " + salaEscolhida.getIdSala());
                    System.out.println("Sessão: " + sessaoEscolhida.getHorario());

                    entrada.nextLine();

                    String confirm;

                    do{
                        System.out.print("Tem certeza que deseja cancelar a reserva? (S/N): ");
                        confirm = entrada.nextLine().trim();

                        if (!confirm.equalsIgnoreCase("S") && !confirm.equalsIgnoreCase("N")) {
                            System.out.println("Entrada inválida! Digite apenas S ou N.");
                        }

                    }while(!confirm.equalsIgnoreCase("S") && !confirm.equalsIgnoreCase("N"));

                    if (confirm.equalsIgnoreCase("N")) {
                        System.out.println("Cancelamento abortado.");
                        break;
                    }

                    //Liberação automatica do assento
                    if (assentoReservado != -1) {
                        salaEscolhida.liberarAssentoPorNumero(assentoReservado);
                        System.out.println("Assento " + assentoReservado + " liberado!");
                    }

                    //Reseta todas as informações guardadas anteriormente!
                    filmeEscolhido = null;
                    salaEscolhida = null;
                    sessaoEscolhida = null;
                    ingressoComprado = false;
                    assentoReservado = -1;

                    cliente.cancelarReserva();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                default:
                    System.out.println("Opção inválida!");
            }
        }while(op != 0);
    }
}
