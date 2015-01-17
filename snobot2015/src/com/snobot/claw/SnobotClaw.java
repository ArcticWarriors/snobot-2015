package com.snobot.claw;

import com.snobot.operatorjoystick.IOperatorJoystick;

public class SnobotClaw implements IClaw{

	IOperatorJoystick joystick;
	/**
	 * Constructs a SnobotClaw  object
	 * @param aOJ
	 */
	public SnobotClaw(IOperatorJoystick aOJ){
		joystick=aOJ;
	}
	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
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
