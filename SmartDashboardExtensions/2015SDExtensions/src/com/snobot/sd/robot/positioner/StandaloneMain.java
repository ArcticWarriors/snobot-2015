package com.snobot.sd.robot.positioner;

import javax.swing.JFrame;

public class StandaloneMain
{


    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        RobotWidget2015Positioner panel = new RobotWidget2015Positioner();

        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
