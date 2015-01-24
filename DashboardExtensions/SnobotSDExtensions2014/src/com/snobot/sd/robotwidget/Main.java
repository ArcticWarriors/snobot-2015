package com.snobot.sd.robotwidget;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		final RobotDrawer2014 mDrawerPanel = new RobotDrawer2014();
		
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
					mDrawerPanel.setArmUp(!mDrawerPanel.isArmUp());
				}
				else if(arg0.getKeyChar() == 's')
				{
					mDrawerPanel.setShooterUp(!mDrawerPanel.isShooterUp());
				}
				else if(arg0.getKeyChar() == 'w')
				{
					double speed = mDrawerPanel.getRollerSpeed() + shooterSpeedIncrement;
					if(speed > 1)
					{
						speed = 1;
					}
					mDrawerPanel.setRollerSpeed(speed);
				}
				else if(arg0.getKeyChar() == 'd')
				{
					double speed = mDrawerPanel.getRollerSpeed() - shooterSpeedIncrement;
					if(speed < -1)
					{
						speed = -1;
					}
					mDrawerPanel.setRollerSpeed(speed);
				}
				
				mDrawerPanel.repaint();
			}
		});
		
		
	}
}
