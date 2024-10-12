package gui;

import models.TagInfo;
import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
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

    public PanelEscritaRFIDTag(HttpClientESP httpClient) {

        this.httpClient = httpClient;

        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(300, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Organiza os componentes em 3 linhas

        // label para dropdown das UIDs
        lblSelecionaUid = new JLabel("Selecione a UID Tag: ");
        lblSelecionaUid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // caixa dropdown das uids
        uidDropdown = new JComboBox<>(listaUids.toArray(new String[0]));
        uidDropdown.setMaximumSize(new Dimension(200, 30));
        uidDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // label para dropdown das Bebidas
        lblSelecionaBebida = new JLabel("Selecione a Bebida: ");
        lblSelecionaBebida.setAlignmentX(Component.CENTER_ALIGNMENT);

        // caixa dropdown das bebidas
        bebidaDropdown = new JComboBox<>(listaBebidas.toArray(new String[0]));
        bebidaDropdown.setMaximumSize(new Dimension(200, 30));
        bebidaDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // botão para enviar requisição ao ESP8266
        btnPostTagInfo = new JButton("Enviar Informações");
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

        add(lblSelecionaUid);
        add(Box.createVerticalStrut(10));
        add(uidDropdown);
        add(lblSelecionaBebida);
        add(Box.createVerticalStrut(10));
        add(bebidaDropdown);
        add(Box.createVerticalStrut(10));
        add(btnPostTagInfo);
        add(Box.createVerticalStrut(20));
        add(lblStatus);
    }
}
