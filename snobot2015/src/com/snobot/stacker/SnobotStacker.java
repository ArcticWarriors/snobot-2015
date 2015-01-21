package com.snobot.stacker;

import com.snobot.operatorjoystick.IOperatorJoystick;

/**
 * Main class for snobot Stacker subsystem
 * @author Alec/Jeffery
 *
 */
public class SnobotStacker implements IStacker{
	
	private IOperatorJoystick mOperatorJoystick;
	
	/**
	 * Constructs a SnobotStacker object
	 * @param aOperatorJoystick Argument of operator joy stick
	 */
	public SnobotStacker(IOperatorJoystick aOperatorJoystick){
	    mOperatorJoystick=aOperatorJoystick;
	}

	@Override
	public void moveStackerUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveStackerDown() {
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
