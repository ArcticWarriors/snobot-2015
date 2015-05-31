package com.snobot.commands.raw;

import com.snobot.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RawDriveFoward extends Command
{
    private final Timer mTimer;
    private final double mSpeed;
    private final double mTimeout;

    private final IDriveTrain mDriveTrain;

    public RawDriveFoward(double aSpeed, double aTimeout, IDriveTrain aDriveTrain)
    {
        mSpeed = aSpeed;
        mDriveTrain = aDriveTrain;
        mTimeout = aTimeout;
        mTimer = new Timer();
    }

//    @Override
    protected void initialize()
    {
        mTimer.start();
    }

//    @Override
    /**
     * Sets motors to desired speed until the distance specified has been traveled
     */
    protected void execute()
    {
        mDriveTrain.setMotorSpeed(mSpeed, mSpeed);
    }

//    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

//    @Override
    protected void interrupted()
    {
        // nothing to do
    }

//    @Override
    protected boolean isFinished()
    {
        return mTimer.get() >= mTimeout;
    }
}
