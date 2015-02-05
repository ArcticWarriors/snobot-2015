package com.snobot.commands;

import com.snobot.claw.*;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClaw extends Command{
    
    private final boolean mClawUp;
    private final SnobotClaw mClaw;
    
    public MoveClaw(boolean aClawUp, SnobotClaw aClaw)
    {
        mClawUp = aClawUp;
        mClaw = aClaw;
    }
    
    protected void end() {
        // TODO Auto-generated method stub
        
    }

    
    protected void execute() {
      //Actuates claw up/down
        if(mClawUp)
        {
            mClaw.moveClawUp();
        }
        else if(!mClawUp)
        {
            mClaw.moveClawDown();
        }
        
    }

    
    protected void initialize() {
        // TODO Auto-generated method stub
        
    }

    
    protected void interrupted() {
        // TODO Auto-generated method stub
        
    }

    
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
