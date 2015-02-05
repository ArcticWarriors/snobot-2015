package com.snobot.commands;

import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RawStack extends Command
{
    private final Timer mTimer;
    private final double mSpeed;
    private final boolean moveUp;
    private final SnobotStacker mSnobotStacker;

    public RawStack(double aSpeed, boolean aMoveUp, SnobotStacker aSnobotStacker)
    {
        mSpeed = aSpeed;
        moveUp = aMoveUp;
        mTimer = new Timer();
        mSnobotStacker = aSnobotStacker;
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute()
    {
        // TODO Auto-generated method stub
        if (mTimer.get() < 5)
        {
            if (moveUp)
            {
                mSnobotStacker.moveStackerUp();
            }
            else
            {
                mSnobotStacker.moveStackerDown();
            }
        }

    }

    @Override
    protected void initialize()
    {
    	mTimer.start();
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
        if (mTimer.get() >= 5)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}
