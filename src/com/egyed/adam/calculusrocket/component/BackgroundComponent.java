package com.egyed.adam.calculusrocket.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Adam on 5/12/16.
 */
public class BackgroundComponent extends JComponent {

    public static int WIDTH = 800;

    public static int HEIGHT = 600;

    public BackgroundComponent() {
        super();



    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D.Double sky = new Rectangle2D.Double(0,0,WIDTH, HEIGHT);

        g2.setColor(new Color(79, 226, 213));
        g2.fill(sky);

        Rectangle2D.Double ground = new Rectangle2D.Double(0,15*HEIGHT/16,WIDTH, HEIGHT);

        g2.setColor(new Color(76, 159, 43));
        g2.fill(ground);


    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH,HEIGHT);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(WIDTH,HEIGHT);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(WIDTH,HEIGHT);
    }
}
