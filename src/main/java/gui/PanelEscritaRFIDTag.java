package gui;

import models.TagInfo;
import network.HttpClientESP;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEscritaRFIDTag extends JPanel {

    private HttpClientESP httpClient;
    private List<String> listaUids = List.of("3912fe7a", "a4c895a9", "a48c57ce");
    // criar lista bebidas
    private JLabel lblSelecionaUid;
    private JComboBox<String> uidDropdown;
    private JButton btnPostTagInfo;

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

        // caixa dropdown das bebidas

        // botão para enviar requisição ao ESP8266
        btnPostTagInfo = new JButton("Enviar Informações");
        btnPostTagInfo.setSize(new Dimension(100, 30));
        btnPostTagInfo.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnPostTagInfo.addActionListener(e -> {
            try {

                String uidSelecionado = uidDropdown.getSelectedItem().toString();

                // IMPLEMENTAR NO HTTPCLIENT O MÉTODO POST
                //TagInfo tagInfo = httpClient.postTagInfo(uidSelecionado, bebidaSelcionada);


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(lblSelecionaUid);
        add(Box.createVerticalStrut(10));
        add(uidDropdown);
        add(Box.createVerticalStrut(10));
        add(btnPostTagInfo);
    }
}
