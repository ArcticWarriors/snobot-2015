package com.snobot.commands;

import com.snobot.stacker.IStacker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RawStack extends Command
{
    private final IStacker mSnobotStacker;
    private final Timer mTimer;
    private final boolean moveUp;
    private final double mTimeout;

    public RawStack(double aTimeout, boolean aMoveUp, IStacker aSnobotStacker)
    {
        mSnobotStacker = aSnobotStacker;
        mTimer = new Timer();
        mTimeout = aTimeout;
        moveUp = aMoveUp;
    }

    
    protected void end()
    {
        mSnobotStacker.stop();
    }

    
    protected void execute()
    {

        if (moveUp)
        {
            mSnobotStacker.moveStackerUp();
        }
        else
        {
            mSnobotStacker.moveStackerDown();
        }
    }

    
    protected void initialize()
    {
    	mTimer.start();
    }

    
    protected void interrupted()
    {
    }

    
    protected boolean isFinished()
    {
        return (mTimer.get() >= mTimeout);
    }

}
