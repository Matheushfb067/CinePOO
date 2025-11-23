package cinema.main;

import java.util.Scanner;
import java.util.InputMismatchException;

import cinema.modelo.Filme;
import cinema.modelo.Sala;
import cinema.modelo.Sessao;
import cinema.modelo.Cliente;
import cinema.concorrencia.TentativaDeCompra;
import cinema.pagamento.*;
import cinema.modelo.ClienteComum;
import cinema.modelo.ClienteEstudante;
import cinema.modelo.ClienteIdoso;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op = -1;

        Filme[] filmes = {
                new Filme("Wicked: Parte 2", 120, "Fantasia", 12),
                new Filme("Truque de mestre - O 3º ato", 112, "Ação", 14),
                new Filme("Zootopia 2", 107, "Animação", 10),
                new Filme("Five Nights at Freddy's 2", 104, "Terror", 16),
                new Filme("O Hobbit: A Desolação de Smaug", 186, "Fantasia", 12),
                new Filme("Invocação do Mal 4: O Ultimo Ritual", 136, "Terror", 16),
        };

        Sala[] salas = {
                new Sala(1, 120, "3D", true),
                new Sala(2, 100, "2D", true),
                new Sala(3, 140, "IMAX", true),
                new Sala(4, 80, "XD", true),
        };

        Sessao[] sessao = {
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
            System.out.println("6 - Selecione o Tipo de Cliente ");
            System.out.println("7 - Comprar Ingressos");
            System.out.println("8 - Cancelar Reserva");
            System.out.println("9 - Comcorrencia de Compras (THREADS)");
            System.out.println("0 - Sair");
            System.out.println("==========================================");

            //Se na entrada 'op' o usuário digitar uma letra, símbolo ou string, seu programa cai em InputMismatchException e encerra.
            //InputMismatchException é uma exceção que acontece quando o usuário digita um valor que não combina com o tipo esperado na entrada
            try {
                op = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                entrada.nextLine();
                continue;
            }

            switch (op) {
                case 1:
                    //Filmes em Cartaz
                    System.out.println("===== Filmes em Cartaz =====");
                    for (int i = 0; i < filmes.length; i++){
                        System.out.println((i + 1) + " " + filmes[i].getTitulo());
                    }
                    System.out.println("==========================================");
                    System.out.println();
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
                    System.out.println();
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
                    System.out.println();
                    break;
                case 4:
                    //Escolher sala

                    int escolhaSala;

                    System.out.println("============= Escolha uma Sala ===========");

                    for (int i = 0; i < salas.length; i++) {
                        System.out.println((i+1) + " Sala " + salas[i].getIdSala() + " (" + salas[i].getCapacidade() + " lugares)");
                    }

                    System.out.println("==========================================");

                    escolhaSala = entrada.nextInt();
                    entrada.nextLine(); // evitar pular entrada
                    salaEscolhida = salas[escolhaSala - 1];

                    int numAssento = -1;

                    //Loop de Escolha do Assento
                    boolean sucesso;
                    do {
                        salaEscolhida.imprimirMapa();
                        System.out.println("Digite o numero do assento desejado: ");
                        //Mesma validação anterior, previnindo de não digitar uma entrada de tipo diferente da necessaria
                        try{
                            numAssento = entrada.nextInt();
                            sucesso = salaEscolhida.ocuparAssentoPorNumero(numAssento);
                        }catch (InputMismatchException e) {
                            System.out.println("Erro: digite apenas números!");
                            entrada.nextLine();
                            sucesso = false;
                            continue; //volta o loop para o inicio
                        }

                        if (!sucesso){
                            System.out.println("Assento já ocupado! Tente novamente.");
                        }
                    }while(!sucesso);

                    //salvar automaticamente o assento escolhido
                    assentoReservado = numAssento;

                    System.out.println("Assento reservado com sucesso!");
                    System.out.println();
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
                    System.out.println();
                    break;
                case 6:
                    //Selecionar tipo de Cliente
                    System.out.println("===== SELECIONAR TIPO DE CLIENTE =====");
                    System.out.println("1 - Cliente Comum (sem desconto)");
                    System.out.println("2 - Estudante (50% de desconto)");
                    System.out.println("3 - Idoso (50% de desconto)");
                    System.out.print("Escolha o tipo de cliente: ");
                    System.out.println("==========================================");
                    System.out.println();

                    int tipoCliente = entrada.nextInt();
                    entrada.nextLine();

                    // Guardar informações do cliente atual
                    String nome = cliente.getNome();
                    String cpf = cliente.getCpf();
                    String email = cliente.getEmail();
                    String telefone = cliente.getTelefone();

                    switch (tipoCliente) {
                        case 1:
                            cliente = new ClienteComum(nome, cpf, email, telefone);
                            System.out.println("Tipo de cliente alterado para: Cliente Comum");
                            break;
                        case 2:
                            System.out.print("Instituição de ensino: ");
                            String instituicao = entrada.nextLine();
                            System.out.print("Número de matrícula: ");
                            String matricula = entrada.nextLine();
                            cliente = new ClienteEstudante(nome, cpf, email, telefone, instituicao, matricula);
                            System.out.println("Tipo de cliente alterado para: Estudante");
                            break;
                        case 3:
                            System.out.print("Data de nascimento (DD/MM/AAAA): ");
                            String dataNasc = entrada.nextLine();
                            cliente = new ClienteIdoso(nome, cpf, email, telefone, dataNasc);
                            System.out.println("Tipo de cliente alterado para: Idoso");
                            break;
                        default:
                            System.out.println("Opção inválida! Mantendo tipo atual.");
                            break;
                    }
                    System.out.println("==========================================");
                    System.out.println();
                    break;
                case 7:
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

                    //Calcula desconto se o cliente for estudante
                    double valorBase = 45.0;
                    double valorFinal = cliente.calcularDesconto(valorBase);
                    System.out.println("Valor do ingresso: R$" + valorFinal);
                    boolean sucessoPagamento = pagamento.pagar(valorFinal); // ex: valor fixo ou variável

                    if (sucessoPagamento) {
                        ingressoComprado = true;
                        System.out.println("Pagamento realizado com sucesso!");
                        cliente.comprarIngresso();
                    } else {
                        System.out.println("Pagamento recusado!");
                    }
                    break;
                case 8:
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
                    System.out.println();
                    break;
                case 9:
                    /*Uma Thread consiste em criar linhas de execução em paralelo dentro do mesmo código

                    */

                    System.out.println("===== SIMULANDO CONCORRÊNCIA DE COMPRAS =====");

                    // Definição da sala e assento de conflito entre os clientes alice, bob e carol.
                    Sala salaTeste = salas[0]; //Sala 1
                    int assentoConflito = 5;

                    System.out.println("Clientes tentando reservar o Assento " + assentoConflito + " na Sala " + salaTeste.getIdSala());

                    //Criação das tarefas - objetos que irão executar em conflito
                    Runnable tarefa1 = new TentativaDeCompra("Cliente Alice", salaTeste, assentoConflito);
                    Runnable tarefa2 = new TentativaDeCompra("Cliente Bob", salaTeste, assentoConflito);
                    Runnable tarefa3 = new TentativaDeCompra("Cliente Carol", salaTeste, assentoConflito);

                    // Cria as Threads e associa as tarefas a elas
                    Thread t1 = new Thread(tarefa1);//objetos que implementam a interface Runnable são as instâncias da classe Cinema.Concorrencia.TentativaDeCompra
                    Thread t2 = new Thread(tarefa2);
                    Thread t3 = new Thread(tarefa3);

                    //start() é o que realmente cria a thread no sistema.
                    t1.start();
                    t2.start();
                    t3.start();

                    /*Ele força a thread principal a esperar que a thread t1 (Alice) termine sua execução antes de
                    continuar com o código seguinte*/
                    try {
                        t1.join(); // Espera t1 finalizar
                        t2.join(); // Espera t2 finalizar
                        t3.join(); // Espera t3 finalizar
                    } catch (InterruptedException e) {
                        System.out.println("Simulação de thread interrompida.");
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("--- Concorrência Finalizada ---");
                    salaTeste.imprimirMapa(); // Mostra o mapa final da sala
                    System.out.println("=============================================");
                    break;
                case 0:
                    System.out.println("Encerrando...");
            }
        }while(op != 0);
    }
}
