package com.snobot.commands;

import com.snobot.stacker.IStacker;

import edu.wpi.first.wpilibj.command.Command;

public class SmartStack extends Command
{
    private boolean mFinished;
    private int mStackCommandIndex;
    private final IStacker mSnobotStacker;

    public SmartStack(int aStackCommandIndex, IStacker aSnobotStacker)
    {
        mFinished = false;
        mStackCommandIndex = aStackCommandIndex;
        mSnobotStacker = aSnobotStacker;
    }

//    @Override
    protected void end()
    {
    }

//    @Override
    protected void execute()
    {

        switch (mStackCommandIndex)
        {
        case 0:
        {
            mFinished = mSnobotStacker.moveStackerToGround();
            break;
        }
        case 1:
        {
            mFinished = mSnobotStacker.moveStackerToScoringPlatform();
            break;
        }
        case 2:
        {
            mFinished = mSnobotStacker.moveStackerToOneStack();
            break;
        }
        default:
        {
            mSnobotStacker.stop();
            mFinished = true;
        }

            System.out.println("Stack index=" + mStackCommandIndex + ", finished=" + mFinished);
        }
    }

//    @Override
    protected void initialize()
    {

    }

//    @Override
    protected void interrupted()
    {
        mSnobotStacker.stop();
    }

//    @Override
    protected boolean isFinished()
    {
        return mFinished;

    }

}
