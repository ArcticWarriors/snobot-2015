package com.snobot.joystick;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Implements Driver Joy stick Interface
 * Sets up Snobot xBox Joy stick for Driver
 * Ayush/Ammar
 */
public class SnobotXBoxDriverJoystick implements IDriverJoystick {
	
	private Joystick mXBoxStick;
	private boolean mDriveMode;
	
	/**
	 * Constructor for xBox Joy stick
	 * @param aXBoxStick Argument for xBox Stick
	 */
	public SnobotXBoxDriverJoystick (Joystick aXBoxStick)
	{
		System.out.println("Creating xbox joystick");
		mXBoxStick = aXBoxStick;
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
		return mXBoxStick.getRawAxis(1);
	}

	@Override
	public double getRightY() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(5);
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(1);
	}

	@Override
	public double getRotate() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(4);
	}
	
	public boolean getDriveMode()
	{
		// TODO Auto-generated method stub
		if ( mXBoxStick.getRawButton(1))
		{
			mDriveMode = true;
		}
		else if (mXBoxStick.getRawButton(2))
		{
			mDriveMode = false;
		}
		
		return mDriveMode;
		
	}
}
