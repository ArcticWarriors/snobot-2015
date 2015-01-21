package com.snobot.claw;

import com.snobot.operatorjoystick.IOperatorJoystick;

/**
 * Class for the snobot claw and implements the main interface for claw
 * 
 * @author Alec/Jeffery
 *
 */
public class SnobotClaw implements IClaw{

	private IOperatorJoystick mJoystick;
	
	/**
	 * Constructs a SnobotClaw  object
	 * @param aJoystick Argument for operator Joystick
	 *
	 */
	public SnobotClaw(IOperatorJoystick aJoystick){
		mJoystick=aJoystick;
	}
	@Override
	public void openClaw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeClaw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveClawUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveClawDown() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void control() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rereadPreferences() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateLog() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
