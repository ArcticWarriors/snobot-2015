package com.snobot.sd.auton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.snobot.sd.button.SDButton;
import com.snobot.sd.config.WidgetConfiguration;
import com.snobot.sd.text_area.SDTextArea;

public class AutonPanel extends JPanel {

    private JButton mSendButton;
    private JTextArea mTextArea;
    
    public AutonPanel()
    {     
        
        mTextArea = new JTextArea();
        mSendButton = new JButton("Send");

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(mTextArea);
        
        this.setLayout(new BorderLayout());
        
        this.add(pane, BorderLayout.CENTER);
        this.add(mSendButton, BorderLayout.SOUTH);
        
        mSendButton.setVisible(true);
        pane.setVisible(true);
    }
    
    public void addSendListener(ActionListener aListener)
    {
        mSendButton.addActionListener(aListener);
    }
    
    public JTextArea getTextArea()
    {
        return this.mTextArea;
    }
}
