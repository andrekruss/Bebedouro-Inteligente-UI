package gui;

import models.StatusTemperatura;
import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLeituraTemperatura extends JPanel {

    private HttpClientESP httpClient;
    private JLabel lbltemperaturaStatus;
    private Timer timer;

    public PanelLeituraTemperatura (HttpClientESP httpClient) {
        setLayout(new GridLayout(1, 1, 10, 10));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 200));
        setBackground(Color.lightGray);

        lbltemperaturaStatus = new JLabel("Temperatura: Carregando...");
        lbltemperaturaStatus.setHorizontalAlignment(SwingConstants.CENTER);

        add(lbltemperaturaStatus);

        // Inicializar e configurar o Timer para atulizar a cada 5seg (5000 ms)
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTemperatura();// Atualizar o painel com informações do ESP32
            }
        });
        timer.start();
    }

    //Metodo para atualizar a temperatura
    public void atualizarTemperatura () {
        try {
           double temperatura = httpClient.getTemperatura();
            lbltemperaturaStatus.setText("Temperatura: " + temperatura + " ºC");

            if (temperatura > 7.0){
                exibirAlerta();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar status da temperatura.");
        }
    }

    // Metodo que exibe o alerta se a temperatura estiver acima de 7 graus
    private void exibirAlerta() {
        JOptionPane.showMessageDialog(this,
                "Alerta: Temperatura acima do recomendado!",
                "Alerta de Temperatura",
                JOptionPane.WARNING_MESSAGE);
    }
}
