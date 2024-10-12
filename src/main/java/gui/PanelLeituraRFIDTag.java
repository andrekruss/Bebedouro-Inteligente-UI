package gui;

import models.TagInfo;
import network.HttpClientESP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class PanelLeituraRFIDTag extends JPanel {

    private HttpClientESP httpClient;
    private List<String> listaUids = List.of("3912fe7a", "a4c895a9", "a48c57ce");
    private JLabel lblSelecionaUid;
    private JComboBox<String> uidDropdown;
    private JButton btnGetTagInfo;
    private JTextArea txtTagInfo;
    private JScrollPane txtTagInfoScrollPane;

    public PanelLeituraRFIDTag(HttpClientESP httpClient) {

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

        // botão para enviar requisição ao ESP8266
        btnGetTagInfo = new JButton("Obter Informações da Tag");
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

        add(lblSelecionaUid);
        add(Box.createVerticalStrut(10));
        add(uidDropdown);
        add(Box.createVerticalStrut(10));
        add(btnGetTagInfo);
        add(Box.createVerticalStrut(10));
        add(txtTagInfoScrollPane);
    }
}
