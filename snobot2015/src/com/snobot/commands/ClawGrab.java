package com.snobot.commands;

import com.snobot.claw.IClaw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ClawGrab extends Command
{
    private final boolean mOpen;
    private final double mLiftSeconds;
    private final IClaw mClaw;
    private Timer mTimer;

    public ClawGrab(boolean aOpen, double aOpenSeconds, IClaw aClaw)
    {
        mOpen = aOpen;
        mClaw = aClaw;
        mLiftSeconds = aOpenSeconds;
        mTimer = new Timer();
    }

    @Override
    protected void end()
    {
    }

    @Override
    protected void execute()
    {
        // Actuates claw open/closed
        if (mOpen)
        {
            mClaw.openClaw();
        }
        else
        {
            mClaw.closeClaw();
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
    }

    @Override
    protected boolean isFinished()
    {
        return (mTimer.get() > mLiftSeconds);
    }

}
