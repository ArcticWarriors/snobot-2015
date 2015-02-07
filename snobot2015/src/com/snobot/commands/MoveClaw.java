package com.snobot.commands;

import com.snobot.claw.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClaw extends Command{
    
    private final boolean mClawUp;
    private final double mLiftSeconds;
    private final SnobotClaw mClaw;
    
    private final Timer mTimer;
    
    public MoveClaw(boolean aClawUp, double aLiftSeconds, SnobotClaw aClaw)
    {
        mClawUp = aClawUp;
        mLiftSeconds = aLiftSeconds;
        mClaw = aClaw;
        mTimer = new Timer();
    }
    @Override
    protected void end() {
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
    }

    @Override
    protected boolean isFinished() {
        return mTimer.get() < mLiftSeconds;
    }
    
}
