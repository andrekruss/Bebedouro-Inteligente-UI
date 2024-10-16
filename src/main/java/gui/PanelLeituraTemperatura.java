package gui;

import models.StatusTemperatura;
import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class PanelLeituraTemperatura extends JPanel {

    private HttpClientESP httpClient;
    private JLabel lbltemperaturaStatus;
    private Timer timer;
    private JLabel lblTituloTemp;
    private int raioBorda = 30;

    public PanelLeituraTemperatura (HttpClientESP httpClient) {

        setOpaque(false);
        //setLayout(new GridLayout(1, 1, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 200));
        setBackground(Color.lightGray);

        // Label para titulo
        lblTituloTemp = new JLabel("VERIFICAR TEMPERATURA:");
        lblTituloTemp.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloTemp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label para acompanhar os niveis dos tanques.
        lbltemperaturaStatus = new JLabel("TEMPERATURA: Carregando...");
        lbltemperaturaStatus.setFont(new Font("Arial", Font.BOLD, 13));
        lbltemperaturaStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(lblTituloTemp);
        add(Box.createVerticalGlue());
        add(lbltemperaturaStatus);
        add(Box.createVerticalGlue());

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
            lbltemperaturaStatus.setText("TEMPERATURA: " + temperatura + " ºC");

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Ativa a suavização de bordas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define a cor de fundo e desenha o painel com bordas arredondadas
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), raioBorda, raioBorda));

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Ativa a suavização de bordas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha a borda arredondada
        g2.setColor(Color.GRAY);
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, raioBorda, raioBorda));

        g2.dispose();
    }
}
