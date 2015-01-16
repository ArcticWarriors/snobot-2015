package com.snobot.sd.auton;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		final AutonDrawer mDrawerPanel = new AutonDrawer();
		
		JFrame frame = new JFrame();
		
		frame.setVisible(true);
		frame.setContentPane(mDrawerPanel);
		frame.pack();


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int modeNumber = 1;
		String modeName = "Test Name";
		String autonCommands = 
						"Command 1\n" + 
						"Command 2\n" + 
						"Command 3\n" + 
						"Command 4\n" + 
						"Command 5\n";
		
		String thisCommand = "Command 2";
		boolean successfulRead = true;
		
		
		mDrawerPanel.setAutonInfo(successfulRead, modeNumber, modeName, thisCommand, autonCommands);
		
		
	}
}
