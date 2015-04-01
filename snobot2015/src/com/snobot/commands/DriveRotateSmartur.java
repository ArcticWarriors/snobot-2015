package com.snobot.commands;

import com.snobot.Properties2015;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.InDeadbandHelper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveRotateSmartur extends Command
{
    private double mError;
    private double mSpeed;

    private final double mDesiredDegree;
    private boolean mFinished;
    private final IDriveTrain mDriveTrain;
    private final SnobotPosition mPosition;

    private InDeadbandHelper mDbHelper;
    private Timer timeout = new Timer();

    /**
     * Creates DriveRotate command object
     * 
     * @param aDegree -Degree specified to turn to
     * @param aDriveTrain -SnobotDriveTrain class
     * @param aPosition -SnobotPosition class
     */
    public DriveRotateSmartur(double aDegree, IDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        super(5);
        mDesiredDegree = aDegree;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        mFinished = false;

        mDbHelper = new InDeadbandHelper(5);
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
        mSpeed = mError * Properties2015.sDRIVE_ROTATE_KP_VALUE.getValue();
        mDriveTrain.setMotorSpeed(mSpeed, -mSpeed);

        boolean is_in_range = (Math.abs(mError) < Properties2015.sDRIVE_ROTATE_MIN_ERROR.getValue());
        mFinished = mDbHelper.isFinished(is_in_range);

        System.out.println("DRS: error: " + mError + ", speed=" + mSpeed);
    }

    @Override
    protected void initialize()
    {
        timeout.start();
    }

    @Override
    protected void interrupted()
    {

    }

    @Override
    protected boolean isFinished()
    {
        return mFinished || timeout.get() > 5;
    }

}
