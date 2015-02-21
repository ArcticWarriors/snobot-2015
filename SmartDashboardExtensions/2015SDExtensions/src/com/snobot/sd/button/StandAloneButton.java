package com.snobot.sd.button;

import javax.swing.JFrame;

public class StandAloneButton {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Stand-alone button");
        
        SDButton button = new SDButton();
        
        frame.setContentPane(button.getButtonPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
