package com.snobot.commands;

import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.position.SnobotPosition;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command
{
    /**
     * TODO add javadoc
     */
    double mDegree;
    double mSpeed;
    SnobotDriveTrain mDriveTrain;
    SnobotPosition mPosition;

    public DriveRotate(double aDegree, double aSpeed, SnobotDriveTrain aDriveTrain, SnobotPosition aPosition)
    {
        mDegree = aDegree;
        mSpeed = aSpeed;
        mDriveTrain = aDriveTrain;
        mPosition = aPosition;
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute()
    {
        while (mPosition.getSnobotDegrees() < (mDegree - 2))
        {
            mDriveTrain.setMotorSpeed(mSpeed, (-mSpeed));
        }
        while (mPosition.getSnobotDegrees() > (mDegree + 2))
        {
            mDriveTrain.setMotorSpeed((mSpeed), mSpeed);
        }
        mDriveTrain.stop();
    }

    @Override
    protected void initialize()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
