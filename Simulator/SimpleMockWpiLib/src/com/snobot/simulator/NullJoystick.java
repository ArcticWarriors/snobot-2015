package com.snobot.simulator;

public class NullJoystick implements IMockJoystick 
{
	public NullJoystick()
	{
		System.out.println("Creainting null joystik");
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

}
