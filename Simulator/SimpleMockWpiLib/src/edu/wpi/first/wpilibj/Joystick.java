package edu.wpi.first.wpilibj;

import com.snobot.simulator.joysticks.IMockJoystick;
import com.snobot.simulator.joysticks.JoystickFactory;
import com.snobot.simulator.joysticks.NullJoystick;

import edu.wpi.first.wpilibj.Joystick.RumbleType;

public class Joystick extends GenericHID {

	public static class RumbleType {

		public static final RumbleType kRightRumble = null;
		public static final RumbleType kLeftRumble = null;

	}

	private static final JoystickFactory factory = new JoystickFactory();
	private IMockJoystick mJoystickWrapper;
	
	public Joystick(int aJoystickIndex) {
		mJoystickWrapper = factory.create(aJoystickIndex);
		
		if(mJoystickWrapper instanceof NullJoystick)
		{
			System.err.println("Warning: You are simulating a null joystick on port " + aJoystickIndex);
		}
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

	public void setRumble(RumbleType rumbleType, float f) {
		// TODO Auto-generated method stub
		
	}

}
