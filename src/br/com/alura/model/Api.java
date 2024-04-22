package br.com.alura.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {

    public ExchangeRate getCode(){

        final String KEY = "350d479c9bb1d3bc9c45ae6a";

        try {
            HttpClient client = HttpClient.newHttpClient();
            URI address = URI.create("https://v6.exchangerate-api.com/v6/" + KEY + "/latest/USD");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(address)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), ExchangeRate.class);

        } catch (IOException | InterruptedException | UncheckedIOException e) {
            throw new RuntimeException(e);
        }

    }



}
