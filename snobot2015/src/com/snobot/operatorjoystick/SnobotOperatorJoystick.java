package com.snobot.operatorjoystick;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Monitors state of operator joystick for other
 * classes/objects to use
 * @author Alec/Jeffrey
 *
 */
public class SnobotOperatorJoystick implements IOperatorJoystick{
	
	private Joystick mJoystick;
	
	/**
	 * Constructs a SnobotOperatorJoystick object
	 * @param aOperatorJoystick Argument for operator Joystick
	 */
	public SnobotOperatorJoystick(Joystick aOperatorJoystick){
	    mJoystick = aOperatorJoystick;
	}

	@Override
	public boolean getStackerUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawOpen() {
		// TODO Auto-generated method stub
		return false;
	}

}
