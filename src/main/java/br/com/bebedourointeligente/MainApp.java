package br.com.bebedourointeligente;

import gui.TelaPrincipal;

import javax.swing.*;
import java.awt.*;

public class MainApp {

    public static void main(String[] args) {
        // Definir que o Swing usará a thread de eventos
        SwingUtilities.invokeLater(() -> {

            TelaPrincipal tela = new TelaPrincipal();
        });
    }
}
