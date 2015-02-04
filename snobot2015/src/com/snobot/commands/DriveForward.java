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

    double mDistance;
    double mSpeed;
    SnobotDriveTrain mDriveTrain;
    SnobotPosition mPosition;
    boolean mFinished;
    double mTotalDistance;

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
        mDistance = aDistance;
        mSpeed = aSpeed;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        mFinished=false;
        mTotalDistance=0;
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub
    }

    @Override
    /**
     * Sets motors to desired speed until the distance specified has been traveled
     */
    protected void execute()
    {
        mDriveTrain.setMotorSpeed(mSpeed, mSpeed);

        if (mTotalDistance < mDistance)
        {
            mTotalDistance = (mTotalDistance + mPosition.getSnobotDistance());
        }
        else if (mTotalDistance >= mDistance)
        {
            mFinished=true;
            mDriveTrain.stop();
        }
    }

    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished()
    {
        if (mFinished)
        {
            mFinished = false;
            return true;
        }
        else
        {
            return false;
        }
    }

}
