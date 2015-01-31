package com.snobot.commands;

import com.snobot.claw.*;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClaw extends Command{
    
    boolean mClawUp;
    SnobotClaw mClaw;
    
    public MoveClaw(boolean aClawUp, SnobotClaw aClaw)
    {
        mClawUp = aClawUp;
        mClaw = aClaw;
    }
    @Override
    protected void end() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void execute() {
      //Actuates claw up/down
        if(mClawUp == true)
        {
            mClaw.moveClawUp();
        }
        else if(mClawUp == false)
        {
            mClaw.moveClawDown();
        }
        
    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
