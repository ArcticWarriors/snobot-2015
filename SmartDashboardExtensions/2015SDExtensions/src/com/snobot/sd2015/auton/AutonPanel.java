package com.snobot.sd2015.auton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.snobot.sd2015.config.SmartDashboardNames;

import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class AutonPanel extends JPanel {

    private JButton mSendButton;
    private JButton mSaveButton;
    private JPanel mButtonPanel;
    private JPanel mBoolPanel;
    private JPanel mButtonsAndBool;
    private JTextArea mTextArea;
    
    // Graphics mColorMe;

    public AutonPanel()
    {     
        
        mTextArea = new JTextArea();
        mButtonPanel = new JPanel();
        mBoolPanel = new JPanel();
        mButtonsAndBool = new JPanel();
        mSaveButton = new JButton("Send & Save");
        mSendButton = new JButton("Send");

        // mColorMe = mBoolPanel.getGraphics();

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(mTextArea);
        
        mButtonPanel.setLayout(new GridLayout(1, 2));
        mButtonsAndBool.setLayout(new GridLayout(2, 1));
        this.setLayout(new BorderLayout());
        
        mButtonPanel.add(mSendButton);
        mButtonPanel.add(mSaveButton);
        
        mButtonsAndBool.add(mButtonPanel);
        mButtonsAndBool.add(mBoolPanel);

        this.add(pane, BorderLayout.CENTER);
        this.add(mButtonsAndBool, BorderLayout.SOUTH);
        
        this.setVisible(true);

        ITableListener parseListener = new ITableListener() {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
                if (Robot.getTable().getBoolean(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, true)) {
                    System.out.println("GREEN!");

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            mBoolPanel.setBackground(Color.GREEN);
                        }
                    });

                }
                else {
                    System.out.println("RED!");

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            mBoolPanel.setBackground(Color.RED);
                        }
                    });
                }
            }

        };

        Robot.getTable().addTableListener(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, parseListener, true);
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
