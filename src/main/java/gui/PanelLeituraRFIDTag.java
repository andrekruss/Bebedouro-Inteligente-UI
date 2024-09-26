package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelLeituraRFIDTag extends JPanel {

    private List<String> listaUids = List.of("uid1", "uid2", "uid3");
    private JLabel lblSelecionaUid;
    private JComboBox<String> uidDropdown;
    private JButton btnGetTagInfo;
    private JTextArea txtTagInfo;
    private JScrollPane txtTagInfoScrollPane;

    public PanelLeituraRFIDTag() {

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
