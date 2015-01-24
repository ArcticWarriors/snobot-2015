package com.snobot.joystick;

import com.snobot.ConfigurationNames;
import com.snobot.logger.Logger;

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
	private boolean mDriveMode;
	private Logger mLogger;
	
	/**
	 * Constructor for Flight Stick 
	 * @param aLeftFlightStick Argument for Left Flight Stick
	 * @param aRightFlightStick Argument for Right Flight Stick
	 */
	public SnobotFlightstickJoystick (Joystick aLeftFlightStick, Joystick aRightFlightStick)
	{
		System.out.println("Creating flightstick joystick");
		mLeftFlightStick = aLeftFlightStick;
		mRightFlightStick = aRightFlightStick;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		mDriveMode = true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if ( mRightFlightStick.getRawButton(ConfigurationNames.sFlightsticks_Button_4))
		{
			mDriveMode = true;
		}
		else if (mRightFlightStick.getRawButton(ConfigurationNames.sFlightsticks_Button_5))
		{
			mDriveMode = false;
		}
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
//		mLogger.updateLogger(
				
			// Left Y Axis
			mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
				
			// Right Y Axis
			mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
			
			// Speed
			mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
			
			// Angle of the Joy stick (for arcade drive)
			mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_X_Axis);
//		)
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLeftY() {
		// TODO Auto-generated method stub
		return mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
	}
	
	@Override
	public double getRightY() {
		// TODO Auto-generated method stub
		return mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
	}

	@Override
	public double getRotate() {
		// TODO Auto-generated method stub
		return mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_X_Axis);
	}
	
	
	public boolean getDriveMode()
	{
		// TODO Auto-generated method stub
		return mDriveMode;
	}
}
