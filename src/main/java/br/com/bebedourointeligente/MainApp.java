package br.com.bebedourointeligente;

import gui.TelaPrincipal;
import network.HttpClientESP;

import javax.swing.*;
import java.awt.*;

public class MainApp {

    public static void main(String[] args) {
        // Definir que o Swing usará a thread de eventos
        SwingUtilities.invokeLater(() -> {

            HttpClientESP httpClient = new HttpClientESP("http://192.168.18.160/");

            TelaPrincipal tela = new TelaPrincipal(httpClient);
        });
    }
}
