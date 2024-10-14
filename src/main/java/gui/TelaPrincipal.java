package gui;

import network.HttpClientESP;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private HttpClientESP httpClient;
    private PanelLeituraRFIDTag panelLeituraRFID;
    private PanelEscritaRFIDTag panelEscritaRFIDTag;
    private PanelLeituraNivel panelLeituraNivel;
    private PanelLeituraTemperatura panelLeituraTemperatura;

    public TelaPrincipal(HttpClientESP httpClient) {

        this.httpClient = httpClient;

        setTitle("Maquina de Bebida Inteligente GUI");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        panelLeituraRFID = new PanelLeituraRFIDTag(this.httpClient);
        add(panelLeituraRFID);

        panelEscritaRFIDTag = new PanelEscritaRFIDTag(this.httpClient);
        add(panelEscritaRFIDTag);

        panelLeituraNivel = new PanelLeituraNivel(this.httpClient);
        add(panelLeituraNivel);

        panelLeituraTemperatura = new PanelLeituraTemperatura(this.httpClient);
        add(panelLeituraTemperatura);

        setVisible(true);
    }
}
