package com.snobot.sd.robot.positioner;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class StandaloneMain
{


    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final RobotWidget2015Positioner panel = new RobotWidget2015Positioner();

        panel.setRobotPosition(90, 24, 45);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                panel.updateScale();
            }
        });
    }
}
