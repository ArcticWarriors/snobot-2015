package com.snobot.joystick;

import edu.wpi.first.wpilibj.Joystick;


/**
 * Implements Driver Joystick Interface
 * Sets up Snobot Flight Stick for Driver
 * Ayush/Ammar
 *
 */
public class SnobotFlightstickJoystick implements IDriverJoystick{

	private Joystick mLeftFlightStick;
	private Joystick mRightFlightStick;
	
	/**
	 * Constructor for Flight Stick 
	 * @param aLeftFlightStick Argument for Left Flight Stick
	 * @param aRightFlightStick Argument for Right Flight Stick
	 */
	public SnobotFlightstickJoystick (Joystick aLeftFlightStick, Joystick aRightFlightStick)
	{
		mLeftFlightStick = aLeftFlightStick;
		mRightFlightStick = aRightFlightStick;
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
