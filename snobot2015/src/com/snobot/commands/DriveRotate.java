package com.snobot.commands;

import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.position.SnobotPosition;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command
{
    /**
     * Auton command for rotate/turn; Turns to a specified degree and speed
     */
    private final double mDegree;
    private final double mSpeed;
    private boolean mFinished;
    private final SnobotDriveTrain mDriveTrain;
    private final SnobotPosition mPosition;

    /**
     * Creates DriveRotate command object
     * @param aDegree -Degree specified to turn to
     * @param aSpeed -Speed specified to turn at 
     * @param aDriveTrain -SnobotDriveTrain class
     * @param aPosition -SnobotPosition class
     */
    public DriveRotate(double aDegree, double aSpeed, SnobotDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        mDegree = aDegree;
        mSpeed = aSpeed;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
        
        mFinished = false;
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    /**
     * Turns right or left based on proximity to mDegree
     */
    @Override
    protected void execute()
    {
        System.out.println("Current = " + mPosition.getSnobotDegrees() + ", desired = " + mDegree + ", speed = " + mSpeed);
        if (mPosition.getSnobotDegrees() < (mDegree - 2))
        {
            mDriveTrain.setMotorSpeed(mSpeed, -mSpeed);
        }
        else if (mPosition.getSnobotDegrees() > (mDegree + 2))
        {
            mDriveTrain.setMotorSpeed((-(mSpeed)), mSpeed);
        }
        else
        {
            mFinished = true;
        }
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
        if(mFinished)
        {
            mFinished = false;
            return true;
        }
        return false;
    }

}
