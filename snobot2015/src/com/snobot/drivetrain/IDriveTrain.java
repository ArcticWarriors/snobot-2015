package com.snobot.drivetrain;

import com.snobot.ISubsystem;

/**
 * Main Drive Train interface.
 * 
 * @author Ayush/Ammar
 *
 */
public interface IDriveTrain extends ISubsystem {

	/**
	 * Will set speed of motors. 
	 * @param aRight Right Motor Speed
	 * @param aLeft Left Motor Speed 
	 */
	void setMotorSpeed ( double aLeft, double aRight);
}
