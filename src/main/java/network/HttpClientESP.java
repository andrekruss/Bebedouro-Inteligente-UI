package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import models.TagInfo;

public class HttpClientESP {

    private String baseUrl;

    // Construtor que define o endereço do ESP8266
    public HttpClientESP(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // Método para enviar requisição HTTP GET
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

    public void postTagInfo(String uidTag, String bebida) {

        // construir a requisição POST com uidTag no endereço
        // e a bebida no corpo da requisição

    }
}
