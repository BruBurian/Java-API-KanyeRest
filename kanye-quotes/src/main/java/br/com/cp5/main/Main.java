package br.com.cp5.main;

import java.util.Scanner;

import br.com.cp5.api.KanyeRestAPI;
import br.com.cp5.beans.Quote;
import br.com.cp5.DAO.QuoteDAO;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KanyeRestAPI api = new KanyeRestAPI();
        QuoteDAO dao = new QuoteDAO("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl","rm552863", "050305");

        while (true) {
            System.out.println("Deseja ver uma frase do Kanye West? (sim/nao)");
            String resposta = scanner.nextLine().toLowerCase();

            if (resposta.equals("sim")) {
                try {
                    Quote quote = api.getQuote();
                    System.out.println(">>> " + quote.getQuote());

                    System.out.println("1. Avaliar frase");
                    System.out.println("2. Ver outra frase");
                    System.out.println("3. Sair");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao) {
                    	case 1:
                    		System.out.println("Dê uma nota de 1 a 10 para a frase:");
                    		int nota = scanner.nextInt();
                    		scanner.nextLine();
                    		// Salvar a citação e a nota no banco de dados
                    		dao.inserirCitacao(quote, nota); 
                    		break;
                        case 2:
                            break;
                        case 3:
                            System.out.println("Volte quando quiser ver mais frases do Kanye ;)");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao obter citação: " + e.getMessage());
                }
            } else if (resposta.equals("nao")) {
                System.out.println("Volte quando quiser ver mais frases do Kanye ;)");
                scanner.close();
                return;
            } else {
                System.out.println("Resposta inválida. Digite 'sim' ou 'nao'.");
            }
        }
    }
}
