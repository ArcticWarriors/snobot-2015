package com.snobot.commands;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotateSmartur extends Command
{
    private double mError;
    private double mSpeed;

    private final double mDesiredDegree;
    private boolean mFinished;
    private final IDriveTrain mDriveTrain;
    private final SnobotPosition mPosition;

    /**
     * Creates DriveRotate command object
     * @param aDegree -Degree specified to turn to
     * @param aDriveTrain -SnobotDriveTrain class
     * @param aPosition -SnobotPosition class
     */
    public DriveRotateSmartur(double aDegree, IDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        mDesiredDegree = aDegree;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        
        mFinished = false;
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    @Override
    protected void execute()
    {
        // TODO Determine which parameter should be set to negative (rotate left
        // or rotate right)
        mError = (mDesiredDegree - mPosition.getSnobotDegrees());
        mSpeed = mError * ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_ROTATE_KP_VALUE, 0.01);
        mDriveTrain.setMotorSpeed(mSpeed, -mSpeed);

        mFinished = (Math.abs(mError) < ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_ROTATE_MIN_ERROR, 2));
    }

    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void interrupted()
    {
        
    }

    @Override
    protected boolean isFinished()
    {
        return mFinished;
    }

}
