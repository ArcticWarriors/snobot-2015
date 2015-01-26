package com.snobot.joystick;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.SnobotDriveTrain.DriveMode;
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
	private SendableChooser mTankModeChooser;
	
	/**
	 * Constructor for xBox Joy stick
	 * @param aXBoxStick Argument for xBox Stick
	 */
	public SnobotXBoxDriverJoystick (Joystick aXBoxStick, SendableChooser aTankModeChooser, DriveMode aDriveMode)
	{
		System.out.println("Creating xbox joystick");
		mXBoxStick = aXBoxStick;
		mTankModeChooser = aTankModeChooser;
		mDriveMode = aDriveMode;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		mTankModeButton = (int) (mTankModeChooser.getSelected());
		mDriveMode = DriveMode.Tank;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if ( mXBoxStick.getRawButton(mTankModeButton))
		{
			mDriveMode = DriveMode.Tank;
		}
		else if (mXBoxStick.getRawButton(ConfigurationNames.sXbox_Button_B))
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
}
