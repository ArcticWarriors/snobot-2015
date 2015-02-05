package com.snobot.commands;

import com.snobot.claw.SnobotClaw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawGrab extends Command
{
    private final boolean mOpen;
    private final SnobotClaw mClaw;
    
    public ClawGrab(boolean aOpen, SnobotClaw aClaw)
    {
        mOpen = aOpen;
        mClaw = aClaw;
    }
    
    
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    
    protected void execute()
    {
        
        
        //Actuates claw open/closed
        if(mOpen)
        {
        mClaw.openClaw();
        }
        else
        {
            mClaw.closeClaw();
        }
        
        

    }

    
    protected void initialize()
    {
        // TODO Auto-generated method stub

    }

    
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

    
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

}