package br.com.cp5.api;

import com.google.gson.Gson;
import br.com.cp5.beans.Quote;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class KanyeRestAPI {
    private final Gson gson = new Gson();
    
 // Obtém uma citação da API KanyeREST
    public Quote getQuote() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
        	// Cria uma requisição GET para a URL da API
            HttpGet request = new HttpGet("https://api.kanye.rest/");
            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Erro ao obter citação: " + response.getStatusLine());
                }
                String json = EntityUtils.toString(response.getEntity());
                return gson.fromJson(json, Quote.class);
            }
        }
    }
}
