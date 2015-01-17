package com.snobot.drivetrain;

import com.snobot.ISubsystem;

public interface IDriveTrain extends ISubsystem {

	/**
	 * Will set speed of motors. 
	 * @param aR Right Motor Speed
	 * @param aL Left Motor Speed
	 */
	void setMotorSpeed ( double aR, double aL);
}
