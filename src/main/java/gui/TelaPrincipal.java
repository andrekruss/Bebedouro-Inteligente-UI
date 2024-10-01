package gui;

import network.HttpClientESP;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private HttpClientESP httpClient;
    private PanelLeituraRFIDTag panelLeituraRFID;

    public TelaPrincipal(HttpClientESP httpClient) {

        this.httpClient = httpClient;

        setTitle("Bebedouro Inteligente GUI");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        panelLeituraRFID = new PanelLeituraRFIDTag(this.httpClient);
        add(panelLeituraRFID);

        setVisible(true);
    }
}
