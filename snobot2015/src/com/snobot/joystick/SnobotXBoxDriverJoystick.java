package com.snobot.joystick;

import com.snobot.ConfigurationNames;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Implements Driver Joy stick Interface
 * Sets up Snobot xBox Joy stick for Driver
 * Ayush/Ammar
 */
public class SnobotXBoxDriverJoystick implements IDriverJoystick {
	
	private Joystick mXBoxStick;
	private boolean mDriveMode;
	private Logger mLogger;
	
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
		mDriveMode = true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if ( mXBoxStick.getRawButton(ConfigurationNames.sXbox_Button_A))
		{
			mDriveMode = true;
		}
		else if (mXBoxStick.getRawButton(ConfigurationNames.sXbox_Button_B))
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
				mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Left_Y_Axis);
				
				// Right Y Axis
				mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Right_Y_Axis);
				
				// Speed
				mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Left_Y_Axis);
				
				// Angle of the Joy stick (for arcade drive)
				mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Right_X_Axis);
//		)
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLeftY() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Left_Y_Axis);
	}

	@Override
	public double getRightY() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Right_Y_Axis);
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Left_Y_Axis);
	}

	@Override
	public double getRotate() {
		// TODO Auto-generated method stub
		return mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Right_X_Axis);
	}
	
	public boolean getDriveMode()
	{
		// TODO Auto-generated method stub
		return mDriveMode;
	}
}
