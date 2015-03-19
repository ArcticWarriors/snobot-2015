package com.snobot.sd2015.auton;

import javax.swing.JFrame;

import com.snobot.sd.button.SDButton;

public class AutonStandalone {


    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Test Auton");
        
        AutonPanel panel = new AutonPanel();
        
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
