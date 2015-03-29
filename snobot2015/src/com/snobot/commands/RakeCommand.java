package com.snobot.commands;

import com.snobot.rake.IRake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RakeCommand extends Command
{
    IRake mRake;
    Timer mTimer;
    double mTimeAmount;
    boolean mGoDown;

    public RakeCommand(IRake aRake, double aTimeAmount, boolean aGoDown)
    {
        mRake = aRake;
        mTimeAmount = aTimeAmount;
        mGoDown = aGoDown;

        mTimer = new Timer();
    }

    @Override
    protected void end()
    {
        mRake.stop();
    }

    @Override
    protected void execute()
    {
        if (mTimer.get() < mTimeAmount)
        {
            if (mGoDown)
            {
                mRake.moveRakeOut();
            }
            else
            {
                mRake.moveRakeIn();
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

        return (mTimer.get() > mTimeAmount);
    }

}
