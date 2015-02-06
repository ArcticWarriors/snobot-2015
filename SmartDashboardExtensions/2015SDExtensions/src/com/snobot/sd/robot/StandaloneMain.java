package com.snobot.sd.robot;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class StandaloneMain
{
    static double height = 0;
    public static void main(String[] args)
    {
        final RobotDrawer2015 mDrawerPanel = new RobotDrawer2015();
        
        JFrame frame = new JFrame();
        
        frame.setVisible(true);
        frame.setContentPane(mDrawerPanel);
        frame.pack();
        


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent arg0) {
                
                double shooterSpeedIncrement = .05;
                
                if(arg0.getKeyChar() == 'a')
                {
                    mDrawerPanel.setClawOpen(!mDrawerPanel.isClawOpen());
                }
                else if(arg0.getKeyChar() == 's')
                {
                    mDrawerPanel.setClawUp(!mDrawerPanel.isClawUp());
                }
                else if(arg0.getKeyChar() == 'w')
                {
                    mDrawerPanel.setUpperLimitSwtich(!mDrawerPanel.isUpperLimitSwitch());
                }
                else if(arg0.getKeyChar() == 'd')
                {
                    mDrawerPanel.setLowerLimitSwitch(!mDrawerPanel.isLowerLimitSwitch());
                }
                else if (arg0.getKeyCode() == KeyEvent.VK_UP)
                {
                    height += 0.05;
                    mDrawerPanel.setStackerHeight(height);
                }
                else if (arg0.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    height -= 0.05;
                    mDrawerPanel.setStackerHeight(height);
                }
                

                System.out.println("State: " + 
                        "Claw Open: " + mDrawerPanel.isClawOpen() + ", " + 
                        "Claw up: " + mDrawerPanel.isClawUp() + ", " + 
                        "Stacker Height: " + mDrawerPanel.getStackerHeight() + ", " + 
                        "Upper LS: " + mDrawerPanel.isUpperLimitSwitch() + ", " + 
                        "Lower LS: " + mDrawerPanel.isLowerLimitSwitch());
                
                mDrawerPanel.repaint();
            }
        });
        
        
    }
}
