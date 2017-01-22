package com.egyed.adam.calculusrocket.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adam on 5/12/16.
 */
public class Rocket extends JComponent {

    private int frameX;
    private int frameY;

    private int xSize = 20;
    private int ySize = 50;

    private double y;

    public Rocket(int frameX, int frameY) {
        super();

        this.frameX = frameX;
        this.frameY = frameY;
        y = 0;



    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        double xRect = (frameX / 2) - (xSize / 2);
        double yRect = frameY - (y + (ySize / 2) +40);
        Rectangle2D.Double rect = new Rectangle2D.Double(xRect,yRect,xSize,ySize);

        Rectangle2D.Double wing1 = new Rectangle2D.Double(xRect-5,yRect+40,10,20);
        Rectangle2D.Double wing2 = new Rectangle2D.Double(xRect+15,yRect+40,10,20);

        Rectangle2D.Double head = new Rectangle2D.Double(xRect-3,yRect-15,27,25);



        //g2.drawImage(rocketImage,xRect,yR)

        g2.setColor(Color.red);
        g2.fill(rect);

        g2.setColor(Color.orange);
        g2.fill(wing1);
        g2.fill(wing2);
        g2.fill(head);
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getYpos() {
        return y;
    }
}
