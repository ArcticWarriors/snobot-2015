package com.snobot.commands;

import com.snobot.claw.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClaw extends Command{
    
    private final boolean mClawUp;
    private final double mLiftSeconds;
    private final SnobotClaw mClaw;
    
    private Timer mTimer;
    
    public MoveClaw(boolean aClawUp, double aLiftSeconds, SnobotClaw aClaw)
    {
        mClawUp = aClawUp;
        mLiftSeconds = aLiftSeconds;
        mClaw = aClaw;
        mTimer = new Timer();
    }
    @Override
    protected void end() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void execute() {
      //Actuates claw up/down
        if(mClawUp)
        {
            if(mTimer.get() < mLiftSeconds)
            {
                mClaw.moveClawUp();
            }
        }
        else if(!mClawUp)
        {
            mClaw.moveClawDown();
        }
        
    }

    @Override
    protected void initialize() {
        mTimer.start();
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return(mTimer.get() < mLiftSeconds);
    }
    
}
