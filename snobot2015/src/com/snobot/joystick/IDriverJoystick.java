package com.snobot.joystick;

import com.snobot.ISubsystem;

public interface IDriverJoystick extends ISubsystem {
	
	/**
	 * Gets input from Left Stick
	 * @return double for Left Joy stick (-1 to 1)
	 */
	double getLeftY();
	
	/**
	 * Gets Input from Right Stick
	 * @return double for Right Joy stick (-1 to 1)
	 */
	double getRightY();
	
	/**
	 * 
	 * @return double for Speed 
	 */
	double getSpeed ();
	
	/**
	 * Gets angle of joy stick
	 * @return
	 */
	double getRotate ();
}
