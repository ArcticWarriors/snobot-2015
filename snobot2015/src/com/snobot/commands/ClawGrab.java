package com.snobot.commands;

import com.snobot.claw.SnobotClaw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawGrab extends Command
{
    boolean mOpen;
    SnobotClaw mClaw;
    
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
        if(mOpen == true)
            {
            mClaw.openClaw();
            }
        else if(mOpen == false)
        {
            mClaw.closeClaw();
        }
        
        

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
