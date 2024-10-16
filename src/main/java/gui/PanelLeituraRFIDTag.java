package gui;

import models.TagInfo;
import network.HttpClientESP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;


public class PanelLeituraRFIDTag extends JPanel {

    private HttpClientESP httpClient;
    private List<String> listaUids = List.of("3912fe7a", "a4c895a9", "a48c57ce");
    private JLabel lblSelecionaUid;
    private JComboBox<String> uidDropdown;
    private JButton btnGetTagInfo;
    private JTextArea txtTagInfo;
    private JScrollPane txtTagInfoScrollPane;
    private JLabel lblTitulo;
    private int raioBorda = 30;

    public PanelLeituraRFIDTag(HttpClientESP httpClient) {

        this.httpClient = httpClient;

        setOpaque(false);
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(300, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Organiza os componentes em 3 linhas

        // Label para titulo
        lblTitulo = new JLabel("CONSULTAR TAG:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // label para dropdown das UIDs
        lblSelecionaUid = new JLabel("Selecione a UID Tag: ");
        lblSelecionaUid.setFont(new Font("Arial", Font.BOLD, 12));
        lblSelecionaUid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // caixa dropdown das uids
        uidDropdown = new JComboBox<>(listaUids.toArray(new String[0]));
        uidDropdown.setMaximumSize(new Dimension(200, 30));
        uidDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // botão para enviar requisição ao ESP8266
        btnGetTagInfo = new JButton("Confirmar Tag");
        btnGetTagInfo.setSize(new Dimension(100, 30));
        btnGetTagInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnGetTagInfo.addActionListener(e -> {
                try {

                    String uidSelecionado = uidDropdown.getSelectedItem().toString();
                    TagInfo tagInfo = httpClient.getTagInfo(uidSelecionado);

                    // Escrevendo na TextArea
                    txtTagInfo.setText(""); // Limpa a TextArea
                    txtTagInfo.append("UID: " + tagInfo.getUid() + "\n");
                    txtTagInfo.append("Bebida: " + tagInfo.getBebida() + "\n");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        });

        txtTagInfo = new JTextArea(5, 20);
        txtTagInfo.setEditable(false);
        txtTagInfo.setMaximumSize(new Dimension(200, 100));
        txtTagInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtTagInfoScrollPane = new JScrollPane(txtTagInfo);
        txtTagInfoScrollPane.setMaximumSize(new Dimension(200, 100));
        txtTagInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(lblTitulo);
        add(Box.createVerticalGlue());
        add(lblSelecionaUid);
        add(Box.createVerticalStrut(10));
        add(uidDropdown);
        add(Box.createVerticalStrut(10));
        add(btnGetTagInfo);
        add(Box.createVerticalStrut(10));
        add(txtTagInfoScrollPane);
        add(Box.createVerticalGlue());
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
