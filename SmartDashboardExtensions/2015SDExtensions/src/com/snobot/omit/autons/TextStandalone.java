package com.snobot.omit.autons;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TextStandalone
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        TextPanel panel = new TextPanel();
        frame.setSize(new Dimension(200, 200));
        frame.setContentPane(panel.getPanel());
        frame.setVisible(true);

    }
}
