package com.snobot.xlib;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public abstract class ASnobot extends IterativeRobot implements ISubsystem
{

    protected ArrayList<ISubsystem> mSubsystems;

    public ASnobot()
    {
        super();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
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
    @Override
    public void teleopPeriodic()
    {
        update();
        control();
        updateSmartDashboard();
        updateLog();
    }

    @Override
    public void disabledInit()
    {
        PropertyManager.saveIfUpdated();
    }

    @Override
    public void update()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.update();

        }
    }

    @Override
    public void control()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.control();
        }
    }

    @Override
    public void updateSmartDashboard()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.updateSmartDashboard();
        }
    }

    @Override
    public void init()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.init();
        }
    }

    @Override
    public void rereadPreferences()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.rereadPreferences();
        }
    }

    @Override
    public void stop()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.stop();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic()
    {

    }

}