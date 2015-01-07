package com.snobot.simulator;

public interface IMockJoystick {

	boolean getRawButton(int aIndex);

	double getRawAxis(int aIndex);

	double getX();
	double getY();

}
