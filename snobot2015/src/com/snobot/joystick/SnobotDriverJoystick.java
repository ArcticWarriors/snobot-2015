package com.snobot.joystick;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotDriverJoystick implements IDriverJoystick {
	
	private Joystick mJoystick1;
	
	public SnobotDriverJoystick (Joystick aJoystick1)
	{
		mJoystick1 = aJoystick1;
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

	@Override
	public double getLeftY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRightY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRotate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
