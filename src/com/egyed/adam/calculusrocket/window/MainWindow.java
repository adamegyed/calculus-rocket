package com.egyed.adam.calculusrocket.window;

import com.egyed.adam.calculusrocket.component.BackgroundComponent;
import com.egyed.adam.calculusrocket.component.Rocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam on 5/12/16.
 *
 */
public class MainWindow extends JFrame implements ActionListener {

    private static final double maxT = 10.0;

    BackgroundComponent bg;

    Rocket rocket;

    Timer refreshTimer;

    JPanel rightPanel;
    JPanel buttonPanel;
    JPanel showTimePanel;
    JPanel positionPanel;


    JButton playButton;
    JButton pauseButton;
    JButton resetButton;

    JLabel showTime;
    JLabel accelerationFunction;
    JLabel velocityFunction;


    JLabel position1;
    JLabel position2;
    JLabel position3;
    JLabel position4;

    JTextField fieldA;
    JTextField fieldB;
    JTextField fieldC;
    JTextField fieldD;

    JButton updateButton;

    JPanel timePanel;
    JLabel setTime;
    JTextField timeSet;
    JButton updateTime;



    private double a = 0.0;
    private double b = 0.0;
    private double c = 0.0;
    private double d = 0.0;

    private double t;

    public MainWindow() {
        super("Calculus Rocket");

        t = 0.0;

        refreshTimer = new Timer(25, this);
        rocket = new Rocket(BackgroundComponent.WIDTH, BackgroundComponent.HEIGHT);
        this.add(rocket);
        rocket.setBounds(0,0,BackgroundComponent.WIDTH, BackgroundComponent.HEIGHT);

        init();

        this.pack();
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

    }

    private void init() {

        rightPanel = new JPanel();

        rightPanel.setLayout(new GridLayout(7,1));
        buttonPanel = new JPanel();


        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        resetButton = new JButton("Reset");

        showTime = new JLabel("    t = 0.00    y = 0.00");
        showTimePanel = new JPanel(new BorderLayout());
        showTimePanel.add(showTime, BorderLayout.CENTER);

        playButton.addActionListener(this);
        pauseButton.addActionListener(this);
        resetButton.addActionListener(this);

        buttonPanel.setLayout(new GridLayout(1,3));

        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);

        // Init functions

        positionPanel = new JPanel();

        accelerationFunction = new JLabel("    a(t) = 0");
        velocityFunction = new JLabel("    v(t) = 0");

        position1 = new JLabel("    x(t) = ");
        fieldA = new JTextField("0",3);
        position2 = new JLabel("t^3 + ");
        fieldB = new JTextField("0",3);
        position3 = new JLabel("t^2 + ");
        fieldC = new JTextField("0",3);
        position4 = new JLabel("t + ");
        fieldD = new JTextField("0",3);

        positionPanel.add(position1);
        positionPanel.add(fieldA);
        positionPanel.add(position2);
        positionPanel.add(fieldB);
        positionPanel.add(position3);
        positionPanel.add(fieldC);
        positionPanel.add(position4);
        positionPanel.add(fieldD);

        //

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);


        timePanel = new JPanel();
        timeSet = new JTextField("0",3);
        setTime = new JLabel("    t = ");
        updateTime = new JButton("Set Time");
        updateTime.addActionListener(this);

        timePanel.add(setTime);
        timePanel.add(timeSet);
        timePanel.add(updateTime);


        rightPanel.add(buttonPanel);
        rightPanel.add(showTimePanel);
        rightPanel.add(accelerationFunction);
        rightPanel.add(velocityFunction);
        rightPanel.add(positionPanel);
        rightPanel.add(updateButton);
        rightPanel.add(timePanel);

        this.setLayout(new BorderLayout());
        bg = new BackgroundComponent();

        this.add(bg, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==refreshTimer) {


            t+=0.025;
            //if ((t % 1) == 0) System.out.println("t: "+t +" y: "+String.valueOf( rocket.getY()));

            if (t>maxT) {
                t = maxT;
                refreshTimer.stop();
            }
            updateRocket();
            updateShowTime();

        }
        else if (e.getSource()==playButton) {
            playTimer();
        }
        else if (e.getSource()==pauseButton) {
            pauseTimer();
        }
        else if (e.getSource()==resetButton) {
            resetTimer();
        }
        else if (e.getSource() == updateButton) {
            updateFunctions();
        }
        else if (e.getSource()==updateTime) {
            try {
                t = Double.valueOf(timeSet.getText());
            }
            catch (Exception excp) {
                JOptionPane.showMessageDialog(null,"Invalid time","Error",JOptionPane.ERROR_MESSAGE);
            }

            if (t <= 10 && t >= 0) {
                updateRocket();
                updateShowTime();
            }
            else {
                JOptionPane.showMessageDialog(null,"Time not in range","Error",JOptionPane.ERROR_MESSAGE);
            }
        }


    }


    private void resetTimer() {

        t = 0.0;

        refreshTimer.stop();

        updateRocket();
        updateShowTime();

    }

    private void pauseTimer() {

        refreshTimer.stop();
    }

    private void playTimer() {

        refreshTimer.restart();

    }

    private void updateShowTime() {
        StringBuilder time = new StringBuilder(String.valueOf(t));
        StringBuilder showY = new StringBuilder(String.valueOf(rocket.getYpos()));

        while (time.length()<4) {
            time.append('0');

        }
        while (showY.length()<4) {
            showY.append('0');

        }
        showTime.setText("    t = "+time.toString().substring(0,4)+"    y = "+showY.toString().substring(0,4));
        showTime.repaint();

    }

    private void updateFunctions() {

        try {

            a = Double.valueOf(fieldA.getText());
            b = Double.valueOf(fieldB.getText());
            c = Double.valueOf(fieldC.getText());
            d = Double.valueOf(fieldD.getText());


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Invalid constants","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }



        StringBuilder acel = new StringBuilder();
        StringBuilder vel = new StringBuilder();

        if (a!=0) {
            double ac;
            double ve;
            ve = a * 3;
            ac = a * 6;

            String veString = String.valueOf(ve);
            String acString = String.valueOf(ac);

            if (veString.length()>fieldA.getText().length()+2) {
                veString = veString.substring(0,fieldA.getText().length()+2);
            }




            if (acString.length()>fieldA.getText().length()+2) {
                acString = acString.substring(0,fieldA.getText().length()+2);
            }

            vel.append(veString).append("t^2");
            acel.append(acString).append("t");
        }
        if (b!=0) {
            double ac;
            double ve;
            ve = b * 2;
            ac = b * 2;

            String veString = String.valueOf(ve);
            String acString = String.valueOf(ac);

            if (veString.length()>fieldB.getText().length()+2) {
                veString = veString.substring(0,fieldB.getText().length()+2);
            }




            if (acString.length()>fieldB.getText().length()+2) {
                acString = acString.substring(0,fieldB.getText().length()+2);
            }

            if (a!=0)  {
                vel.append(" + ");
                acel.append(" + ");
            }
            vel.append(veString).append('t');
            acel.append(acString);
        }
        if (c!=0) {


            if (b!=0 || a!=0) vel.append(" + ");
            vel.append(c);

        }
        if (a==0 && b==0) {
            acel.append('0');
            if (c==0) {
                vel.append('0');
            }
        }

        accelerationFunction.setText("    a(t) = "+acel.toString());

        velocityFunction.setText("    v(t) = "+vel.toString());

        accelerationFunction.repaint();

        velocityFunction.repaint();


        resetTimer();



    }

    private void updateRocket() {

        double ypos;

        ypos = (a * t * t * t) + (b * t * t) + (c * t) + d;

        rocket.setY(ypos);
        rocket.repaint();

    }
}
