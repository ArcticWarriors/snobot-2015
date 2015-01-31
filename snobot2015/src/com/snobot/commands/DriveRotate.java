package com.snobot.commands;

import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.position.SnobotPosition;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command
{
    /**
     * Auton command for rotate/turn; Turns to a specified degree and speed
     */
    double mDegree;
    double mSpeed;
    SnobotDriveTrain mDriveTrain;
    SnobotPosition mPosition;

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
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    /**
     * Turns right or left based on proximity to mDegree
     */
    @Override
    protected void execute()
    {
        if (mPosition.getSnobotDegrees() < (mDegree - 2))
        {
            mDriveTrain.setMotorSpeed(mSpeed, (-mSpeed));
        }
        else if (mPosition.getSnobotDegrees() > (mDegree + 2))
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
