package com.snobot.simulator.joysticks;

public interface IMockJoystick {

	boolean getRawButton(int aIndex);

	double getRawAxis(int aIndex);

	double getX();
	double getY();

}
