package gui;

import models.StatusTanques;
import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;


public class PanelLeituraNivel extends JPanel {

    private HttpClientESP httpClient;
    private JLabel lbltanque1Status;
    private JLabel lbltanque2Status;
    private JLabel lbltanque3Status;
    private Timer timer;
    private JLabel lblTituloNivel;
    private int raioBorda = 30;

    public PanelLeituraNivel(HttpClientESP httpClient){

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 200));
        setBackground(Color.lightGray);

        // Label para titulo
        lblTituloNivel = new JLabel("VERIFICAR NIVEL:");
        lblTituloNivel.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloNivel.setAlignmentX(Component.CENTER_ALIGNMENT);

        lbltanque1Status = new JLabel("TANQUE 1: Carregando...");
        lbltanque1Status.setAlignmentX(Component.CENTER_ALIGNMENT);

        lbltanque2Status = new JLabel("TANQUE 2: Carregando...");
        lbltanque2Status.setAlignmentX(Component.CENTER_ALIGNMENT);

        lbltanque3Status = new JLabel("TANQUE 3: Carregando...");
        lbltanque3Status.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona os componentes ao painel

        add(Box.createVerticalStrut(10));
        add(lblTituloNivel);
        add(Box.createVerticalGlue());
        add(lbltanque1Status);
        add(Box.createVerticalStrut(20));
        add(lbltanque2Status);
        add(Box.createVerticalStrut(20));
        add(lbltanque3Status);
        add(Box.createVerticalGlue());

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

            lbltanque1Status.setText("TANQUE 1: " + status.getTanque1());
            lbltanque2Status.setText("TANQUE 2: " + status.getTanque2());
            lbltanque3Status.setText("TANQUE 3: " + status.getTanque3());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar status dos tanques.");
        }
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
