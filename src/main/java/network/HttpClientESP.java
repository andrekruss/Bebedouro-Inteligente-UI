package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import models.StatusTanques;
import models.StatusTemperatura;
import models.TagInfo;
import java.io.OutputStream;
import java.rmi.server.ExportException;

public class HttpClientESP {

    private String baseUrl;

    // Construtor que define o endereço do ESP8266
    public HttpClientESP(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // Metodo para enviar requisição HTTP GET
    public TagInfo getTagInfo(String tagUid) throws Exception {
        URL url = new URL(baseUrl + "get-tag-info?uid=" + tagUid);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Usar Gson para fazer o parse da resposta JSON
            Gson gson = new Gson();
            TagInfo tagInfo = gson.fromJson(response.toString(), TagInfo.class);

            // Retorna o objeto TagInfo
            return tagInfo;
        } else {
            throw new Exception("GET request falhou, código: " + responseCode);
        }
    }

    // Metodo para realizar a requisicao POST passando UID e a bebida
    public void postTagInfo (String tagUid, String bebida) throws Exception {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(baseUrl + "post-tag-info?uid=" + tagUid);
            connection = (HttpURLConnection) url.openConnection();

            // Configura a conexão para realizar um POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Criação do JSON com a UID e a bebida no formato correto "tagUid: bebida"
            String jsonInputString = "{ \"" + tagUid + "\": \"" + bebida + "\"}";

            // Envia o JSON via OutputStream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // Verificar o código de resposta
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("POST request falhou, código: " + responseCode);
            }

            System.out.println("POST request bem-sucessido.");

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Metodo para buscar os niveis dos tanques via requisição GET
    public StatusTanques getNiveisTanques() throws Exception{
        URL url = new URL(baseUrl + "/get-niveis");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Conversão do JSON recebido para objeto StatusTanques
            Gson gson = new Gson();
            return gson.fromJson(response.toString(), StatusTanques.class);

        } else {
            throw new Exception("Erro na requisição GET: " + responseCode);
        }
    }

    // Metodo para buscar os niveis dos tanques via requisição GET
    public double getTemperatura() throws Exception{
        URL url = new URL(baseUrl + "/get-temperatura");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Conversão do JSON recebido para objeto Temperatura
            return Double.parseDouble((response.toString()));

        } else {
            throw new Exception("Erro na requisição GET: " + responseCode);
        }
    }
}
