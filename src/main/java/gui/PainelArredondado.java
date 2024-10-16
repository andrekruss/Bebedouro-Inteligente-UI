package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PainelArredondado extends JPanel {
    private int raioBorda;  // Raio das bordas arredondadas

    public PainelArredondado(int raioBorda) {
        this.raioBorda = raioBorda;  // Definindo o raio para as bordas
        setOpaque(false);  // Deixa o fundo transparente para mostrar as bordas arredondadas
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Ativando suavização de bordas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Definindo a cor de fundo do painel
        g2.setColor(getBackground());

        // Desenhando um retângulo com bordas arredondadas
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), raioBorda, raioBorda));

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Ativando suavização de bordas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenhando a borda do retângulo com bordas arredondadas
        g2.setColor(Color.GRAY);  // Cor da borda
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, raioBorda, raioBorda));

        g2.dispose();
    }
}
