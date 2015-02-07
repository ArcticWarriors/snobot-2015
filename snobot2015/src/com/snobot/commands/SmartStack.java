package com.snobot.commands;

import com.snobot.ConfigurationNames;
import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SmartStack extends Command
{
    private boolean mFinished;
    private int mStackCommandIndex;
    private final SnobotStacker mSnobotStacker;
   

    public SmartStack( int aStackCommandIndex, SnobotStacker aSnobotStacker)
    {
        mFinished = false;
        mStackCommandIndex = aStackCommandIndex;
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
        
        switch (mStackCommandIndex)
        {
        case 0:
        {
            mSnobotStacker.moveStackerToGround();
            mFinished = true;
            break;
        }
        case 1:
        {
            mSnobotStacker.moveStackerToScoringPlatform();
            mFinished = true;
            break;
        }
        case 2:
        {
            mSnobotStacker.moveStackerToOneStack();
            mFinished = true;
            break;
        }
        case 3:
        {
            mSnobotStacker.moveStackerToTwoStack();
            mFinished = true;
            break;
        }
        case 4:
        {
            mSnobotStacker.moveStackerToThreeStack();
            mFinished = true;
            break;
        }
        default:
        {
            mSnobotStacker.stop();
            mFinished = true;
        }
        }
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void interrupted()
    {
        mSnobotStacker.stop();
    }

    @Override
    protected boolean isFinished()
    {
        return mFinished;
      
    }

}
