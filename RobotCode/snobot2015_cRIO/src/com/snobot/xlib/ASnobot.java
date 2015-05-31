package com.snobot.xlib;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import java.util.Vector;

public abstract class ASnobot extends IterativeRobot implements ISubsystem
{

    protected Vector mSubsystems;

    public ASnobot()
    {
        super();
    }

    /**
     * This function is called periodically during autonomous
     */
//    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();

        update();
        updateSmartDashboard();
        updateLog();

    }

    /**
     * This function is called periodically during operator control
     */
//    @Override
    public void teleopPeriodic()
    {
        update();
        control();
        updateSmartDashboard();
        updateLog();
    }

//    @Override
    public void disabledInit()
    {
        PropertyManager.saveIfUpdated();
    }

//    @Override
    public void update()
    {
        for (int i = 0; i < mSubsystems.size(); ++ i)
        {
            ISubsystem iSubsystem = (ISubsystem) mSubsystems.elementAt(i);
            iSubsystem.update();

        }
    }

//    @Override
    public void control()
    {
        for (int i = 0; i < mSubsystems.size(); ++ i)
        {
            ISubsystem iSubsystem = (ISubsystem) mSubsystems.elementAt(i);
            iSubsystem.control();
        }
    }

//    @Override
    public void updateSmartDashboard()
    {
        for (int i = 0; i < mSubsystems.size(); ++ i)
        {
            ISubsystem iSubsystem = (ISubsystem) mSubsystems.elementAt(i);
            iSubsystem.updateSmartDashboard();
        }
    }

//    @Override
    public void init()
    {
        for (int i = 0; i < mSubsystems.size(); ++ i)
        {
            ISubsystem iSubsystem = (ISubsystem) mSubsystems.elementAt(i);
            iSubsystem.init();
        }
    }

//    @Override
    public void rereadPreferences()
    {
        for (int i = 0; i < mSubsystems.size(); ++ i)
        {
            ISubsystem iSubsystem = (ISubsystem) mSubsystems.elementAt(i);
            iSubsystem.rereadPreferences();
        }
    }

//    @Override
    public void stop()
    {
        for (int i = 0; i < mSubsystems.size(); ++ i)
        {
            ISubsystem iSubsystem = (ISubsystem) mSubsystems.elementAt(i);
            iSubsystem.stop();
        }
    }

    /**
     * This function is called periodically during test mode
     */
//    @Override
    public void testPeriodic()
    {

    }

}