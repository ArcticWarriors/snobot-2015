package com.snobot.commands.raw;

import com.snobot.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives forward at specified speed for the specified distance
 * 
 * @author Andrew/Alec
 *
 */
public class RawDriveForward extends Command
{

    private final double mTime;
    private final double mSpeed;
    private final Timer mTimer;

    private final IDriveTrain mDriveTrain;

    /**
     * Creates DriveForward Command object
     * 
     * @param aTime
     *            Time to drive
     * @param aSpeed
     *            Speed to set motors to
     * @param aDriveTrain
     *            Drivetrain class
     */
    public RawDriveForward(double aTime, double aSpeed, IDriveTrain aDriveTrain)
    {
        mSpeed = aSpeed;
        mTime = aTime;
        mDriveTrain = aDriveTrain;
        mTimer = new Timer();
    }

    @Override
    protected void initialize()
    {
        mTimer.start();
    }

    @Override
    /**
     * Sets motors to desired speed until the distance specified has been traveled
     */
    protected void execute()
    {
        mDriveTrain.setMotorSpeed(mSpeed, mSpeed);
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
        return mTimer.get() > mTime;
    }

}
