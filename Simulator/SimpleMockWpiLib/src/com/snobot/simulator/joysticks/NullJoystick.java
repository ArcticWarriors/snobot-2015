package com.snobot.simulator.joysticks;

public class NullJoystick implements IMockJoystick 
{
	public NullJoystick()
	{
	}

	@Override
	public boolean getRawButton(int aIndex) {
		return false;
	}

	@Override
	public double getRawAxis(int aIndex) {
		return 0;
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

	@Override
	public int getAxisCount() {
		return 0;
	}

	@Override
	public int getButtonCount() {
		return 0;
	}

	@Override
	public void setRumble(short s) {
	}

}
