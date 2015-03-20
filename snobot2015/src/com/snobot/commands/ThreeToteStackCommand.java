package com.snobot.commands;

import com.snobot.stacker.IStacker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ThreeToteStackCommand extends Command
{
    IStacker mSnobotStacker;
    Timer mTimer;
    double mTime1;
    double mTime2;
    double mTime3;
    double mTime4;

    boolean mFinishedMovingDown;
    boolean mFinishedMovingDown2;

    public ThreeToteStackCommand(IStacker aSnobotStacker, double aTime1, double aTime2, double aTime3, double aTime4)
    {
        mSnobotStacker = aSnobotStacker;
        mTimer = new Timer();
        mTime1 = aTime1;
        mTime2 = aTime2;
        mTime3 = aTime3;
        mTime4 = aTime4;

        mFinishedMovingDown = false;
    }

    @Override
    protected void initialize()
    {
        // TODO Auto-generated method stub
        mTimer.start();
    }

    @Override
    protected void execute()
    {
        // TODO Auto-generated method stub
        if (mTimer.get() < mTime1)
        {
            mSnobotStacker.moveStackerToOneStack();
            System.out.println("Phase 1");
        }
        else if (mTimer.get() < mTime2)
        {
            if (!mFinishedMovingDown)
            {

                System.out.println("Phase 2 Down");
                mFinishedMovingDown = !mSnobotStacker.moveStackerDown();
            }

            if (mFinishedMovingDown)
            {
                System.out.println("Phase 2 Up");
                mSnobotStacker.moveStackerToOneStack();
            }

        }
        else if (mTimer.get() < mTime3)
        {

            if (!mFinishedMovingDown2)
            {

                System.out.println("Phase 3 Down");
                mFinishedMovingDown2 = !mSnobotStacker.moveStackerDown();
            }

            if (mFinishedMovingDown2)
            {
                System.out.println("Phase 3 Up");
                mSnobotStacker.moveStackerToScoringPlatform();
            }

        }
        else if (mTimer.get() < mTime4)
        {
            System.out.println("Phase 4");
            mSnobotStacker.moveStackerToGround();

        }

    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return (mTimer.get() > mTime4);
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

}
