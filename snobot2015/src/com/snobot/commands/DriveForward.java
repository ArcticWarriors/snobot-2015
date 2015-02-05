        package com.snobot.commands;

import edu.wpi.first.wpilibj.command.Command;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.*;
import com.snobot.position.*;

/**
 * Drives forward at specified speed for the specified distance
 * 
 * @author Andrew/Alec
 *
 */
public class DriveForward extends Command
{

    private final double mDesiredDistance;
    private final double mSpeed;
    private final SnobotDriveTrain mDriveTrain;
    private final SnobotPosition mPosition;
    private double mStartingDistance;
    boolean mFinished;

    /**
     * Creates DriveForward Command object
     * 
     * @param aDistance
     *            -Distance desired to drive
     * @param aSpeed
     *            -Speed to set motors to
     * @param aDriveTrain
     *            -SnobotDriveTrain class
     * @param aPosition
     *            -SnobotPosition class
     */
    public DriveForward(double aDistance, double aSpeed, SnobotDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        super(ConfigurationNames.sDRIVE_FORWARD_COMMAND);
        mDesiredDistance = aDistance;
        mSpeed = aSpeed;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        mFinished=false;
    }

    @Override
    protected void initialize()
    {
    	mStartingDistance  = mPosition.getTotalDistance();
    }
    
    @Override
    /**
     * Sets motors to desired speed until the distance specified has been traveled
     */
    protected void execute()
    {
        double distance_travelled = mPosition.getTotalDistance() - mStartingDistance;

        if (distance_travelled < mDesiredDistance)
        {
            mDriveTrain.setMotorSpeed(mSpeed, mSpeed);
        }
        else
        {
            mFinished=true;
        }
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
    	//nothing to do
    }

    @Override
    protected boolean isFinished()
    {
    	return mFinished;
    }

}
