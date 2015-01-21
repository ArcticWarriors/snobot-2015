package com.snobot.drivetrain;

import com.snobot.joystick.SnobotXBoxDriverJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets up specific snobot drive train 
 * @author Ayush/Ammar
 *
 */
public class SnobotDriveTrain implements IDriveTrain{

	private SpeedController mSpeedControllerLeft;
	private SpeedController mSpeedControllerRight;		 
	private SnobotXBoxDriverJoystick mDriverJoystick;
	private RobotDrive mRobotDrive;
	
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
		mSpeedControllerRight =	aSpeedControllerRight;	 
		mDriverJoystick = aDriverJoystick;  
		mRobotDrive = new RobotDrive(mSpeedControllerLeft, mSpeedControllerRight);
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
		// TODO Add Tank drive
		mRobotDrive.arcadeDrive(mDriverJoystick.getSpeed(), mDriverJoystick.getRotate());
		
	}

	@Override
	public void rereadPreferences() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("Left Drive Speed", mSpeedControllerLeft.get());
		SmartDashboard.putNumber("Right Drive Speed",mSpeedControllerRight.get());
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
