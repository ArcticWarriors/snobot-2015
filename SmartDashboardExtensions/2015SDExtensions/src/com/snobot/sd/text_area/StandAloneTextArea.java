package com.snobot.sd.text_area;

import javax.swing.JFrame;

public class StandAloneTextArea {

    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        
        TextArea tester = new TextArea();
        
        frame.setContentPane(tester.getScrollPane());;
        frame.pack();
        frame.setVisible(true);
    }
}
