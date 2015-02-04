package com.snobot.sd.text_area;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;

public class TextAreaWidget extends StaticWidget{
    JTextArea mTextArea;
    JPanel mFrame;
    
    public TextAreaWidget()
    {
        mTextArea = new JTextArea(5, 20);
        mFrame = new JPanel();
    }

    @Override
    public void propertyChanged(Property arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init() {
        mFrame.add(this);
        mFrame.setVisible(true);
    }

}
