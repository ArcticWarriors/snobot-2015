package com.snobot.operatorjoystick;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotOperatorJoystick implements IOperatorJoystick{
	
	private Joystick mJoystick;
	
	/**
	 * Constructs a SnobotOperatorJoystick object
	 * @param aJoystick
	 */
	public SnobotOperatorJoystick(Joystick aJoystick){
	    mJoystick = aJoystick;
	}

	@Override
	public boolean getStackerUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getStackerDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawClosed() {
		// TODO Auto-generated method stub
		return false;
	}

}
