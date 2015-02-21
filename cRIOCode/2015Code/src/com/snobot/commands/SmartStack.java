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

    
    protected void end()
    {
    }

    
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
        
        System.out.println(mStackCommandIndex);
        }
    }

    
    protected void initialize()
    {

    }

    
    protected void interrupted()
    {
        mSnobotStacker.stop();
    }

    
    protected boolean isFinished()
    {
        return mFinished;
      
    }

}
