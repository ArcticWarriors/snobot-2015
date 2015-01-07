package edu.wpi.first.wpilibj;

import com.snobot.simulator.IMockJoystick;
import com.snobot.simulator.JoystickFactory;

public class Joystick {

	private static final JoystickFactory factory = new JoystickFactory();
	private IMockJoystick mJoystickWrapper;
	
	public Joystick(int aJoystickIndex) {
		mJoystickWrapper = factory.create(aJoystickIndex);
	}

	public boolean getRawButton(int aIndex) {
		return mJoystickWrapper.getRawButton(aIndex);
		
	}

	public double getRawAxis(int aIndex) {
		return mJoystickWrapper.getRawAxis(aIndex);
	}

	public double getX() {
		return mJoystickWrapper.getX();
	}

	public double getY() {
		return mJoystickWrapper.getY();
	}

	public boolean getTrigger() {
		return getRawButton(1);
	}

}
