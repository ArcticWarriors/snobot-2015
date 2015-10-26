package com.snobot2012.shooter;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.SinglePressButton;
import com.snobot2012.SmartDashboardNames;
import com.snobot2012.ui.OperatorJoystick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotShooter implements ISubsystem

{
	private SpeedController mShooterMotor;
	private Solenoid mShooterSolenoid;
	
	private SinglePressButton mIncreaseSpeedButton;
	private SinglePressButton mDecreaseSpeedButton;
	
    private OperatorJoystick mShooterJoystick;
	
    public SnobotShooter(SpeedController aShooterMotor, Solenoid aShooterSolenoid, OperatorJoystick aShooterJoystick)
	{
		mShooterJoystick = aShooterJoystick;
		mShooterSolenoid = aShooterSolenoid;
		mShooterMotor = aShooterMotor;
		mIncreaseSpeedButton = new SinglePressButton();
		mDecreaseSpeedButton = new SinglePressButton();
	}
	
	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void control() {

		//Shooter
        double motorSpeed = mShooterMotor.get();
        if (mIncreaseSpeedButton.update(mShooterJoystick.incrementShooterSpeed()))
		{
            motorSpeed += 0.1;
		}
        else if (mDecreaseSpeedButton.update(mShooterJoystick.decrementShooterSpeed()))
		{
            motorSpeed -= 0.1;
		}
		
        if (motorSpeed > 1)
		{
            motorSpeed = 1;
		}
        else if (motorSpeed < -1)
		{
            motorSpeed = -1;
		}
		
        mShooterMotor.set(motorSpeed);
		
		//Piston
        if (mShooterJoystick.shootButton())
		{
			mShooterSolenoid.set(true);
		}
		else
		{
			mShooterSolenoid.set(false);
		}
		
	}
	@Override
    public void rereadPreferences()
    {
	}

	@Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.SHOOTER_SPEED, mShooterMotor.get());
	}

	@Override
    public void updateLog()
    {
	}

	@Override
    public void stop()
    {
        mShooterMotor.set(0);
	}
	
		
	
}
