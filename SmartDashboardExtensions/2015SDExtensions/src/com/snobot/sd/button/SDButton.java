package com.snobot.sd.button;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.snobot.sd.config.WidgetConfiguration;

import edu.wpi.first.smartdashboard.robot.Robot;

public class SDButton {

    private JButton mButton;
    private JPanel mPanel;
    
    class MyButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public SDButton()
    {
        mButton = new JButton(WidgetConfiguration.BUTTON_TEXT);
        mButton.setPreferredSize(new Dimension(WidgetConfiguration.BUTTON_SIZE_X, 
                WidgetConfiguration.BUTTON_SIZE_Y));
        
        mPanel = new JPanel();
        mPanel.add(mButton);
        
        ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Robot.getTable().putBoolean("Button Performed", true);
                Robot.getTable().putBoolean("Button Performed", false);
            }
        };
        
        mButton.addActionListener(listener);
    }
    
    public JPanel getButtonPanel()
    {
        return this.mPanel;
    }
}
