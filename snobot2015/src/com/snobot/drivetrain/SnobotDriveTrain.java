package com.snobot.drivetrain;

import com.snobot.joystick.SnobotXBoxDriverJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Sets up specific snobot drive train 
 * @author Ayush/Ammar
 *
 */
public class SnobotDriveTrain implements IDriveTrain{

	private SpeedController mSpeedControllerLeft;
	private SpeedController mSpeedControlleRight;		 
	private SnobotXBoxDriverJoystick mDriverJoystick;
	
	/**
	 * Takes 2 speed controllers and joy stick arguments
	 * @param aSpeedControllerLeft Argument for left Speed Controller
	 * @param aSpeedControllerRight Argument for right Speed Controller
	 * @param aDriverJoystick Argument Driver Joy stick
	 */
	public SnobotDriveTrain (
			SpeedController aSpeedControllerLeft, 
			SpeedController aSpeedControllerRight,				 
			SnobotXBoxDriverJoystick aDriverJoystick)
	{
		mSpeedControllerLeft = aSpeedControllerLeft;
		mSpeedControlleRight =	aSpeedControllerRight;	 
		mDriverJoystick = aDriverJoystick;  
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
	public void setMotorSpeed(double aLeft, double aRight) {
		// TODO Auto-generated method stub
		
	}
}
