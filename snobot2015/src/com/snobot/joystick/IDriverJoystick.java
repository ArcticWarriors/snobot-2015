package com.snobot.joystick;

import com.snobot.ISubsystem;

/**
 * Main interface for Driver Joystick 
 * @author Ayush/Ammar
 *
 */

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
	 * @return double for joy stick angle
	 */
	double getRotate ();
	
	/**
	 * 
	 * @return if true, tank drive mode else arcade drive mode
	 */
	boolean getDriveMode();
}
