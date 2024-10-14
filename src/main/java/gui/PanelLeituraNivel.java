package gui;

import models.StatusTanques;
import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelLeituraNivel extends JPanel {

    private HttpClientESP httpClient;
    private JLabel lbltanque1Status;
    private JLabel lbltanque2Status;
    private JLabel lbltanque3Status;
    private Timer timer;

    public PanelLeituraNivel(HttpClientESP httpClient){
        setLayout(new GridLayout(3, 1, 10, 10));  // Configura layout de 3 linhas e 1 colunas com espaçamento
        setPreferredSize(new Dimension(300, 200));
        setBackground(Color.lightGray);

        lbltanque1Status = new JLabel("Tanque 1: Carregando...");
        lbltanque1Status.setHorizontalAlignment(SwingConstants.CENTER);

        lbltanque2Status = new JLabel("Tanque 2: Carregando...");
        lbltanque2Status.setHorizontalAlignment(SwingConstants.CENTER);

        lbltanque3Status = new JLabel("Tanque 3: Carregando...");
        lbltanque3Status.setHorizontalAlignment(SwingConstants.CENTER);

        // Adiciona os componentes ao painel

        add(lbltanque1Status);
        add(lbltanque2Status);
        add(lbltanque3Status);

        // Inicializar e configurar o Timer para atulizar a cada 5seg (5000 ms)
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarStatusTanques();// Atualizar o painel com informações do ESP32
            }
        });
        timer.start();
    }

    // Metodo que atualiza o Label StatusTanques
    public void atualizarStatusTanques() {
        try {
            StatusTanques status = httpClient.getNiveisTanques();

            lbltanque1Status.setText("Tanque 1: " + status.getTanque1());
            lbltanque2Status.setText("Tanque 2: " + status.getTanque2());
            lbltanque3Status.setText("Tanque 3: " + status.getTanque3());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar status dos tanques.");
        }
    }
}
