package com.snobot.commands;

import com.snobot.Properties2015;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.IPositioner;
import com.snobot.xlib.InDeadbandHelper;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command
{
    private double mSpeed;
    private double mError;

    private final double mDesiredDistance;
    private final IDriveTrain mDriveTrain;
    private final IPositioner mPosition;
    private double mStartingDistance;
    boolean mFinished;

    private InDeadbandHelper mDbHelper;

    public DriveStraight(double aDistance, IDriveTrain aDriveTrain, IPositioner aPosition)
    {
        mDesiredDistance = aDistance;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        mFinished = false;

        mDbHelper = new InDeadbandHelper(5);
    }

    @Override
    protected void initialize()
    {
        mStartingDistance = mPosition.getTotalDistance();
    }

    @Override
    protected void execute()
    {
        mError = mDesiredDistance - (mPosition.getTotalDistance() - mStartingDistance);
        mSpeed = mError * Properties2015.sDRIVE_FORWARD_KP_VALUE.getValue();
        mDriveTrain.setMotorSpeed(mSpeed, mSpeed);

        boolean is_in_range = Math.abs(mError) < Properties2015.sDRIVE_FORWARD_MIN_ERROR.getValue();

        mFinished = mDbHelper.isFinished(is_in_range);
        System.out.println("DFS Error = " + mError + ", speed = " + mSpeed + " loops good = " + mDbHelper.getLoopsGood());
    }

    @Override
    protected boolean isFinished()
    {
        return mFinished;
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();

    }

    @Override
    protected void interrupted()
    {

    }
}
