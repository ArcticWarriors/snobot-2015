package com.snobot.commands;

import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RawStack extends Command
{
    private final SnobotStacker mSnobotStacker;
    private final Timer mTimer;
    private final boolean moveUp;
    private final double mTimeout;

    public RawStack(double aTimeout, boolean aMoveUp, SnobotStacker aSnobotStacker)
    {
        mSnobotStacker = aSnobotStacker;
        mTimer = new Timer();
        mTimeout = aTimeout;
        moveUp = aMoveUp;
    }

    @Override
    protected void end()
    {
        mSnobotStacker.stop();
    }

    @Override
    protected void execute()
    {
        if (mTimer.get() < mTimeout)
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
    }

    @Override
    protected boolean isFinished()
    {
        if (mTimer.get() >= mTimeout)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}
