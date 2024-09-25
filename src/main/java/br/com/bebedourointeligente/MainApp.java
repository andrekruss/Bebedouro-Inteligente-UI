package br.com.bebedourointeligente;

import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        // Definir que o Swing usará a thread de eventos
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Minha Aplicação Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Criar um botão
            JButton button = new JButton("Clique aqui!");

            // Adicionar o botão à janela
            frame.getContentPane().add(button);

            // Tornar a janela visível
            frame.setVisible(true);
        });
    }
}
