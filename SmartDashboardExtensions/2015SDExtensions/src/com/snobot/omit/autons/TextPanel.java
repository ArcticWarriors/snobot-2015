package com.snobot.omit.autons;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.wpi.first.smartdashboard.robot.Robot;

public class TextPanel
{
    JTextArea mTextArea;
    JScrollPane mScrollPane;
    JButton mButton;
    JPanel mPanel;
    private static final String buttoner = "ButtonIsPressed";

    public TextPanel()
    {
        mTextArea = new JTextArea();
        mScrollPane = new JScrollPane();
        mButton = new JButton("Send");
        mPanel = new JPanel();

        mPanel.setLayout(new BorderLayout());
        mScrollPane.setViewportView(mTextArea);

        mPanel.add(mScrollPane, BorderLayout.CENTER);
        mPanel.add(mButton, BorderLayout.SOUTH);

        ActionListener listener = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                Robot.getTable().putBoolean(buttoner, true);
            }

        };


    }

    public JPanel getPanel()
    {
        return this.mPanel;
    }

    public JTextArea getTextArea()
    {
        return this.mTextArea;
    }
}
