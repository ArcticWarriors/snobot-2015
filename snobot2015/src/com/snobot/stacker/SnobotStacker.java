package com.snobot.stacker;

import com.snobot.operatorjoystick.IOperatorJoystick;

public class SnobotStacker implements IStacker{
	
	IOperatorJoystick joystick;
	
	/**
	 * Constructs a SnobotStacker object
	 * @param aOJ
	 */
	public SnobotStacker(IOperatorJoystick aOJ){
	    joystick=aOJ;
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}

}
