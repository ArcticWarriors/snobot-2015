package com.snobot.shooter;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.SinglePressButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class SnobotShooter implements ISubsystem

{
	private SpeedController mShooterMotor;
	private Solenoid mShooterSolenoid;
	
	private SinglePressButton mIncreaseSpeedButton;
	private SinglePressButton mDecreaseSpeedButton;
	
	private Joystick mShooterJoystick;
	
	public SnobotShooter(SpeedController aShooterMotor, Solenoid aShooterSolenoid, Joystick aShooterJoystick) 
	{
		mShooterJoystick = aShooterJoystick;
		mShooterSolenoid = aShooterSolenoid;
		mShooterMotor = aShooterMotor;
		mIncreaseSpeedButton = new SinglePressButton();
		mDecreaseSpeedButton = new SinglePressButton();
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

		//Shooter
		double MotorSpeed = mShooterMotor.get();
		if (mIncreaseSpeedButton.update(mShooterJoystick.getRawButton(1))) // TODO Congfigure Button
		{
			MotorSpeed += 0.1; 
		}
		else if (mDecreaseSpeedButton.update(mShooterJoystick.getRawButton(2))) // TODO Configure Button
		{
			MotorSpeed  -= 0.1;
		}
		
		if (MotorSpeed > 1)
		{
			MotorSpeed = 1; 
		}
		else if (MotorSpeed < -1)
		{
			MotorSpeed = -1; 
		}
		
		mShooterMotor.set(MotorSpeed);
		
		//Piston
		if (mShooterJoystick.getRawButton(3)) // TODO Configure button
		{
			mShooterSolenoid.set(true);
		}
		else
		{
			mShooterSolenoid.set(false);
		}
		
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
