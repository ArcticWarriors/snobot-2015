package com.snobot.sd.text_area;

import javax.swing.JFrame;

public class StandAloneTextArea {

    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        
        SDTextArea tester = new SDTextArea();
        
        frame.setContentPane(tester.getScrollPane());;
        frame.pack();
        frame.setVisible(true);
    }
}
