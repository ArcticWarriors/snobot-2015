package com.snobot.commands;

import com.snobot.claw.SnobotClaw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ClawGrab extends Command
{
    private final boolean mOpen;
    private final SnobotClaw mClaw;
    private Timer mTimer;
    
    public ClawGrab(boolean aOpen, SnobotClaw aClaw)
    {
        mOpen = aOpen;
        mClaw = aClaw;
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
            if(mTimer.get() < 2)
            {
                mClaw.openClaw();
            }
        }
        else
        {
            if(mTimer.get() < 2)
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
        return false;
    }

}
