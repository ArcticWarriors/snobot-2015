package com.snobot.commands;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardSmartur extends Command
{
    private double mSpeed;
    private double mError;

    private final double mDesiredDistance;
    private final IDriveTrain mDriveTrain;
    private final SnobotPosition mPosition;
    private double mStartingDistance;
    boolean mFinished;

    public DriveForwardSmartur(double aDistance, IDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        mDesiredDistance = aDistance;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        mFinished = false;
    }


    
    protected void initialize()
    {
        mStartingDistance = mPosition.getTotalDistance();
    }

    
    protected void execute()
    {
        mError = mDesiredDistance - (mPosition.getTotalDistance() - mStartingDistance);
        mSpeed = mError * ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_FORWARD_KP_VALUE, 0.01);
        mDriveTrain.setMotorSpeed(mSpeed, mSpeed);
        
        // System.out.println("Error = " + mError + ", speed = " + mSpeed);
        
        mFinished = (Math.abs(mError) < ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_FORWARD_MIN_ERROR, 2));
    }

    
    protected boolean isFinished()
    {
        return mFinished;
    }

    
    protected void end()
    {
        mDriveTrain.stop();

    }

    
    protected void interrupted()
    {

    }
}
