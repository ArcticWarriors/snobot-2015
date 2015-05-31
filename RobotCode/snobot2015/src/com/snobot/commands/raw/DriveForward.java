package com.snobot.commands.raw;

import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;

import edu.wpi.first.wpilibj.command.Command;

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
    private final double mTolerance;

    private final IDriveTrain mDriveTrain;
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
    public DriveForward(double aDistance, double aSpeed, double aTolerance, IDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        mDesiredDistance = aDistance;
        mSpeed = aSpeed;
        mTolerance = aTolerance;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        mFinished = false;
    }

    @Override
    protected void initialize()
    {
        mStartingDistance = mPosition.getTotalDistance();
    }

    @Override
    /**
     * Sets motors to desired speed until the distance specified has been traveled
     */
    protected void execute()
    {
        double distanceTravelled = mPosition.getTotalDistance() - mStartingDistance;

        if (distanceTravelled < (mDesiredDistance - mTolerance))
        {
            mDriveTrain.setMotorSpeed(mSpeed, mSpeed);
        }
        else if (distanceTravelled > (mDesiredDistance + mTolerance))
        {
            mDriveTrain.setMotorSpeed(-mSpeed, -mSpeed);
        }
        else
        {
            mFinished = true;
        }

        System.out.println(mPosition.getTotalDistance());
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        // nothing to do
    }

    @Override
    protected boolean isFinished()
    {
        return mFinished;
    }

}
