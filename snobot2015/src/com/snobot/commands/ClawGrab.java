package com.snobot.commands;

import com.snobot.claw.SnobotClaw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawGrab extends Command
{
    boolean mOpen;
    boolean mClawUp;
    SnobotClaw mClaw;
    
    public ClawGrab(boolean aOpen, boolean aClawUp, SnobotClaw aClaw)
    {
        mOpen = aOpen;
        mClawUp = aClawUp;
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
        if(mClawUp == true)
        {
            mClaw.moveClawUp();
        }
        else if(mClawUp == false)
        {
            mClaw.moveClawDown();
        }
        
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
