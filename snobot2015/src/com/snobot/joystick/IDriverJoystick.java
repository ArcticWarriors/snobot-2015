package com.snobot.joystick;

import com.snobot.ISubsystem;

public interface IDriverJoystick extends ISubsystem {
	
	/**
	 * Gets input from Left Stick in tank mode
	 * @return double for Left Joy stick (-1 to 1)
	 */
	double getLeftY();
	
	/**
	 * Gets Input from Right Stick in tank mode
	 * @return double for Right Joy stick (-1 to 1)
	 */
	double getRightY();
	
	/**
	 * Get the speed for arcade mode
	 * 
	 * @return double for Speed 
	 */
	double getSpeed ();
	
	/**
	 * Gets angle for arcade mode
	 * @return
	 */
	double getRotate ();
}
