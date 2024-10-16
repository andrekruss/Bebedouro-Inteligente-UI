package gui;

import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class PanelEscritaRFIDTag extends JPanel {

    private HttpClientESP httpClient;
    private List<String> listaUids = List.of("3912fe7a", "a4c895a9", "a48c57ce");
    private List<String> listaBebidas = List.of("coca", "suco", "agua");
    private JLabel lblSelecionaUid;
    private JLabel lblSelecionaBebida;
    private JComboBox<String> uidDropdown;
    private JComboBox<String> bebidaDropdown;
    private JButton btnPostTagInfo;
    private JLabel lblStatus;
    private JLabel lblTituloEsc;
    private int raioBorda = 30;

    public PanelEscritaRFIDTag(HttpClientESP httpClient) {

        this.httpClient = httpClient;

        setOpaque(false);
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(300, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Label para titulo
        lblTituloEsc = new JLabel("SELECIONAR BEBIDA:");
        lblTituloEsc.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloEsc.setAlignmentX(Component.CENTER_ALIGNMENT);

        // label para dropdown das UIDs
        lblSelecionaUid = new JLabel("Selecione a UID Tag: ");
        lblSelecionaUid.setFont(new Font("Arial", Font.BOLD, 12));
        lblSelecionaUid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // caixa dropdown das uids
        uidDropdown = new JComboBox<>(listaUids.toArray(new String[0]));
        uidDropdown.setMaximumSize(new Dimension(200, 30));
        uidDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // label para dropdown das Bebidas
        lblSelecionaBebida = new JLabel("Selecione a Bebida: ");
        lblSelecionaBebida.setFont(new Font("Arial", Font.BOLD, 12));
        lblSelecionaBebida.setAlignmentX(Component.CENTER_ALIGNMENT);

        // caixa dropdown das bebidas
        bebidaDropdown = new JComboBox<>(listaBebidas.toArray(new String[0]));
        bebidaDropdown.setMaximumSize(new Dimension(200, 30));
        bebidaDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // botão para enviar requisição ao ESP8266
        btnPostTagInfo = new JButton("Confirmar");
        btnPostTagInfo.setSize(new Dimension(100, 30));
        btnPostTagInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label para status de envio
        lblStatus = new JLabel(" ");
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Acao para o botao enviar as informacao
        btnPostTagInfo.addActionListener(e -> {
            try {
                String uidSelecionado = uidDropdown.getSelectedItem().toString();
                String bebidaSelecionada = bebidaDropdown.getSelectedItem().toString();

                // Envia a requisicao via HTTP POST
                httpClient.postTagInfo(uidSelecionado, bebidaSelecionada);

                // Atualiza o status para o usuário
                lblStatus.setText("Informações enviadas com sucesso!");

            } catch (Exception ex) {
                ex.printStackTrace();
                lblStatus.setText("Erro ao enviar informações!");
            }
        });

        add(Box.createVerticalStrut(10));
        add(lblTituloEsc);
        add(Box.createVerticalGlue());
        add(lblSelecionaUid);
        add(Box.createVerticalStrut(10));
        add(uidDropdown);
        add(Box.createVerticalStrut(10));
        add(lblSelecionaBebida);
        add(Box.createVerticalStrut(10));
        add(bebidaDropdown);
        add(Box.createVerticalStrut(30));
        add(btnPostTagInfo);
        add(Box.createVerticalStrut(20));
        add(lblStatus);
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
