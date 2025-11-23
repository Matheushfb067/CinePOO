package cinema.main;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerenciadorDeArquivos {
    private static final String ARQUIVO = "historico_ingressos.txt";

    // Salvar uma compra no arquivo
    public static void salvarCompra(String nomeCliente, String tipoCliente, String filme, String sala, String sessao, int assento, double valor) {
        try {
            // FileWriter com 'true' = adiciona ao final do arquivo (não sobrescreve)
            FileWriter escritor = new FileWriter(ARQUIVO, true);
            BufferedWriter buffer = new BufferedWriter(escritor);

            // Pegar data e hora atual
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHora = agora.format(formato);

            // Escrever as informações no arquivo
            buffer.write("========================================");
            buffer.newLine();
            buffer.write("Data/Hora: " + dataHora);
            buffer.newLine();
            buffer.write("Cliente: " + nomeCliente + " (" + tipoCliente + ")");
            buffer.newLine();
            buffer.write("Filme: " + filme);
            buffer.newLine();
            buffer.write("Sala: " + sala + " | Assento: " + assento);
            buffer.newLine();
            buffer.write("Sessão: " + sessao);
            buffer.newLine();
            buffer.write("Valor pago: R$ " + String.format("%.2f", valor));
            buffer.newLine();
            buffer.write("========================================");
            buffer.newLine();
            buffer.newLine();

            // SEMPRE fechar o arquivo!
            buffer.close();
            escritor.close();

            System.out.println("✓ Compra salva no histórico!");

        } catch (IOException e) {
            System.out.println("✗ Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    // Ler todo o histórico do arquivo
    public static void lerHistorico() {
        try {
            File arquivo = new File(ARQUIVO);

            // Verificar se o arquivo existe
            if (!arquivo.exists()) {
                System.out.println("Nenhum histórico encontrado ainda.");
                return;
            }

            FileReader leitor = new FileReader(ARQUIVO);
            BufferedReader buffer = new BufferedReader(leitor);

            System.out.println("\n===== HISTÓRICO DE COMPRAS =====");
            String linha;

            // Ler linha por linha até o final do arquivo
            while ((linha = buffer.readLine()) != null) {
                System.out.println(linha);
            }

            // SEMPRE fechar o arquivo!
            buffer.close();
            leitor.close();

        } catch (IOException e) {
            System.out.println("✗ Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
