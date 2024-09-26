package gui;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private PanelLeituraRFIDTag panelLeituraRFID;

    public TelaPrincipal() {

        setTitle("Bebedouro Inteligente GUI");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelLeituraRFID = new PanelLeituraRFIDTag();

        add(panelLeituraRFID);

        setVisible(true);
    }
}
