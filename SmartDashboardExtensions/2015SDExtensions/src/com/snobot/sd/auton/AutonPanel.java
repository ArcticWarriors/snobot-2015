package com.snobot.sd.auton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AutonPanel extends JPanel {

    private JButton mSendButton;
    private JButton mSaveButton;
    private JPanel mButtonPanel;
    private JTextArea mTextArea;
    
    public AutonPanel()
    {     
        
        mTextArea = new JTextArea();
        mButtonPanel = new JPanel();
        mSaveButton = new JButton("Send & Save");
        mSendButton = new JButton("Send");

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(mTextArea);
        
        mButtonPanel.setLayout(new GridLayout(1, 2));
        this.setLayout(new BorderLayout());
        
        mButtonPanel.add(mSendButton);
        mButtonPanel.add(mSaveButton);
        
        this.add(pane, BorderLayout.CENTER);
        this.add(mButtonPanel, BorderLayout.SOUTH);
        
        mSendButton.setVisible(true);
        pane.setVisible(true);
    }
    
    public void addSendListener(ActionListener aListener)
    {
        mSendButton.addActionListener(aListener);
    }
    
    public void addSaveListener(ActionListener aListener)
    {
        mSaveButton.addActionListener(aListener);
    }
    
    public JTextArea getTextArea()
    {
        return this.mTextArea;
    }
}
