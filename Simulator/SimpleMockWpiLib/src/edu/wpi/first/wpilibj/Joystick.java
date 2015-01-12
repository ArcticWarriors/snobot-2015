package edu.wpi.first.wpilibj;

import com.snobot.simulator.IMockJoystick;
import com.snobot.simulator.JoystickFactory;

public class Joystick extends GenericHID {

	private static final JoystickFactory factory = new JoystickFactory();
	private IMockJoystick mJoystickWrapper;
	
	public Joystick(int aJoystickIndex) {
		System.out.println("Starting joystick");
		mJoystickWrapper = factory.create(aJoystickIndex);
	}

	public boolean getRawButton(int aIndex) {
		return mJoystickWrapper.getRawButton(aIndex);
		
	}

	public double getRawAxis(int aIndex) {
		return mJoystickWrapper.getRawAxis(aIndex);
	}

	@Override
	public double getX(Hand hand) {
		return mJoystickWrapper.getX();
	}

	@Override
	public double getY(Hand hand) {
		return mJoystickWrapper.getY();
	}

	@Override
	public double getZ(Hand hand) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTwist() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getThrottle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getTrigger(Hand hand) {
		return getRawButton(1);
	}

	@Override
	public boolean getTop(Hand hand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBumper(Hand hand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPOV(int pov) {
		// TODO Auto-generated method stub
		return 0;
	}

}
