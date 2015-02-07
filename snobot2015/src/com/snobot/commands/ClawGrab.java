package com.snobot.commands;

import com.snobot.claw.SnobotClaw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ClawGrab extends Command
{
    private final boolean mOpen;
    private final double mLiftSeconds;
    private final SnobotClaw mClaw;
    private Timer mTimer;
    
    
    public ClawGrab(boolean aOpen, double aOpenSeconds, SnobotClaw aClaw)
    {
        mOpen = aOpen;
        mClaw = aClaw;
        mLiftSeconds = aOpenSeconds;
        mTimer = new Timer();
    }
    
    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute()
    {        
        //Actuates claw open/closed
        if(mOpen)
        {
            if(mTimer.get() < mLiftSeconds)
            {
                mClaw.openClaw();
            }
        }
        else
        {
            if(mTimer.get() < mLiftSeconds)
            {
                mClaw.closeClaw();
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
        return(mTimer.get() > mLiftSeconds);
    }

}
