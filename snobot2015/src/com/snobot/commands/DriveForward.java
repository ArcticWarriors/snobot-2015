package com.snobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.snobot.drivetrain.*;
import com.snobot.position.*;
public class DriveForward extends Command
{
	//super ("DriveForwardCommand");
	double mDistance;
	double mSpeed;
	SnobotDriveTrain mDriveTrain;
	SnobotPosition mPosition;
	public DriveForward (double aDistance, double aSpeed, SnobotDriveTrain aDriveTrain, SnobotPosition aPosition)
	{
		mDistance = aDistance;
		mSpeed = aSpeed;
		mDriveTrain = aDriveTrain;
		mPosition = aPosition;
	}
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() 
	{
		
		double totalDistance = 0;
		mDriveTrain.setMotorSpeed(mSpeed, mSpeed);
		
		while(totalDistance <= mDistance)
		{
			mPosition.updateAll();
			totalDistance = (totalDistance + mPosition.getSnobotDistance());
		}
		mDriveTrain.stop();
	}

	@Override
	protected void initialize() {
		
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
