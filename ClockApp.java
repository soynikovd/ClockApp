package ClockApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockApp implements ActionListener {
    JFrame frame = new JFrame();
    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String secondsString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);
    String hoursString = String.format("%02d", hours);
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000)% 60;
            seconds = (elapsedTime/1000)%60;
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            hoursString = String.format("%02d", hours);
            timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);
        }
    });
    public ClockApp() {
        frame.getContentPane().setBackground(Color.pink);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(450, 150);
        frame.setSize(370, 400);
        frame.setLayout(null);
        frame.setVisible(true);

        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);
        timeLabel.setBounds(150, 100, 200, 100);
        start.setBounds(50, 250, 100, 50);
        reset.setBounds(200, 250, 100, 50);

        frame.add(start);
        frame.add(reset);
        frame.add(timeLabel);

        start.addActionListener(this);
        reset.addActionListener(this);
        start.setBackground(Color.cyan);
        reset.setBackground(Color.cyan);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (started == false) {
                started = true;
                start.setText("STOP");
                start();
            }
            else{
                started = false;
                start.setText("START");
                stop();
            }
        }
        if(e.getSource() == reset) {
            started = false;
            start.setText("START");
            reset();
        }
    }
    void start() {
        timer.start();
    }
    void stop() {
        timer.stop();
    }
    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        secondsString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        hoursString = String.format("%02d", hours);
        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);
    }

    public static void main(String[] args) {
        new ClockApp();
    }
}

