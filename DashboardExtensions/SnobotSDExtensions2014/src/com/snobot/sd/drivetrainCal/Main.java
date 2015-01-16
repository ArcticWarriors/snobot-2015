package com.snobot.sd.drivetrainCal;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		final DrivetrainCalDrawer mDrawerPanel = new DrivetrainCalDrawer();
		
		JFrame frame = new JFrame();
		
		frame.setVisible(true);
		frame.setContentPane(mDrawerPanel);
		frame.pack();


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mDrawerPanel.updateWheelBias(1, true, 51, 2, 3, 4);
		
		
	}
}
