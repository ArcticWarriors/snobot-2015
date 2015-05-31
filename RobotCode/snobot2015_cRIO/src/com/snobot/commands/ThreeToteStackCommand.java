package com.snobot.commands;

import com.snobot.stacker.IStacker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ThreeToteStackCommand extends Command
{
    private IStacker mSnobotStacker;
    private Timer mTimer;

    private double mTime1;
    private double mTime2;
    private double mTime3;
    private double mTime4;

    private enum Steps_e
    {
        ScoopFirst, RaiseOne, ScoopSecond, RaiseTwo, ScoopThird, RaiseThree, DropEm,

        Finished
    }

    private Steps_e mStep;

    public ThreeToteStackCommand(IStacker aSnobotStacker, double aTime1, double aTime2, double aTime3, double aTime4)
    {
        mSnobotStacker = aSnobotStacker;
        mTimer = new Timer();
        mTime1 = aTime1;
        mTime2 = aTime2;
        mTime3 = aTime3;
        mTime4 = aTime4;

        mStep = Steps_e.ScoopFirst;
    }

    @Override
    protected void initialize()
    {
        mTimer.start();
    }

    @Override
    protected void execute()
    {
        System.out.println("Running step : " + mStep);

        switch (mStep)
        {
        // Come down on the first step
        case ScoopFirst:
            if (mTimer.get() > mTime1 || mSnobotStacker.moveStackerToGround())
            {
                mStep = Steps_e.RaiseOne;
            }
            break;

        // Pick it up
        case RaiseOne:
            mSnobotStacker.moveStackerToOneStack();
            if (mTimer.get() > mTime1)
            {
                mStep = Steps_e.ScoopSecond;
            }
            break;

        // Come down on the second tote
        case ScoopSecond:
            if (mTimer.get() > mTime2 || mSnobotStacker.moveStackerToGround())
            {
                mStep = Steps_e.RaiseTwo;
            }
            break;

        // Pick it up
        case RaiseTwo:
            mSnobotStacker.moveStackerToOneStack();
            if (mTimer.get() > mTime2)
            {
                mStep = Steps_e.ScoopThird;
            }
            break;

        // Come down on the third tote
        case ScoopThird:
            if (mTimer.get() > mTime3 || mSnobotStacker.moveStackerToGround())
            {
                mStep = Steps_e.RaiseThree;
            }
            break;

        // Pick it up ever so slightly
        case RaiseThree:
            mSnobotStacker.moveStackerToScoringPlatform();
            if (mTimer.get() > mTime3)
            {
                mStep = Steps_e.DropEm;
            }
            break;

        // Drop them and prepare to leave
        case DropEm:
            mSnobotStacker.moveStackerToGround();
            if (mTimer.get() > mTime4)
            {
                mStep = Steps_e.Finished;
            }
            break;
        default:
            System.out.println("WHY ARE YOU IN HERE?!?!?!?!?");
            break;
        }

    }

    @Override
    protected boolean isFinished()
    {
        return mStep == Steps_e.Finished;
    }

    @Override
    protected void end()
    {
        mSnobotStacker.stop();
    }

    @Override
    protected void interrupted()
    {
    }

}
