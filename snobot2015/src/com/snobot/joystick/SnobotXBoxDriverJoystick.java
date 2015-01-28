package com.snobot.joystick;

import com.snobot.ConfigurationNames;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * Implements Driver Joy stick Interface
 * Sets up Snobot xBox Joy stick for Driver
 * Ayush/Ammar
 */
public class SnobotXBoxDriverJoystick implements IDriverJoystick {
	
	private Joystick mXBoxStick;
	private DriveMode mDriveMode;
	private Logger mLogger;
	private int mTankModeButton;
	private int mArcadeModeButton;
	private SendableChooser mArcadeModeChooser;
	private SendableChooser mTankModeChooser;
	
	/**
	 * Constructor for xBox Joy stick
	 * @param aXBoxStick Argument for xBox Stick
	 */
	public SnobotXBoxDriverJoystick (Joystick aXBoxStick, SendableChooser aTankModeChooser, SendableChooser aArcadeModeChooser, DriveMode aDriveMode)
	{
		System.out.println("Creating xbox joystick");
		mXBoxStick = aXBoxStick;
		mTankModeChooser = aTankModeChooser;
		mDriveMode = aDriveMode;
		mArcadeModeChooser = aArcadeModeChooser;
	}

	@Override
	public void init() {
		// TODO add Header
		mLogger.addHeader("Xbox Joystick Inputs");
		mTankModeButton = (int) (mTankModeChooser.getSelected());
		mArcadeModeButton = (int) (mArcadeModeChooser.getSelected());
		mDriveMode = DriveMode.Tank;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if ( mXBoxStick.getRawButton(mTankModeButton))
		{
			mDriveMode = DriveMode.Tank;
		}
		else if (mXBoxStick.getRawButton(mArcadeModeButton))
		{
			mDriveMode = DriveMode.Arcade;
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
		// TODO Check if these work
				// Left Y Axis
				mLogger.updateLogger(mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Left_Y_Axis));
				
				// Right Y Axis
				mLogger.updateLogger(mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Right_Y_Axis));
				
				// Speed
				mLogger.updateLogger(mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Left_Y_Axis));
				
				// Angle of the Joy stick (for arcade drive)
				mLogger.updateLogger(mXBoxStick.getRawAxis(ConfigurationNames.sXbox_Right_X_Axis));
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

	@Override
	public DriveMode getDriveMode() {
		// TODO Auto-generated method stub
		return mDriveMode;
	}
}
