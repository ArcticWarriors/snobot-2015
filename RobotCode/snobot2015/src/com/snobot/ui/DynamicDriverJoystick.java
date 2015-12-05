package com.snobot.ui;

import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DynamicDriverJoystick implements IDriverJoystick
{

    private final IDriverJoystick mFlightStickWrapper;
    private final IDriverJoystick mXboxWrapper;
    private final SendableChooser mStickTypeChooser;
    private final SendableChooser mModeTypeChooser;

    private IDriverJoystick mActiveStick;

    public DynamicDriverJoystick(Joystick stick1, Joystick stick2, Logger aLogger)
    {
        mStickTypeChooser = new SendableChooser();
        mModeTypeChooser = new SendableChooser();
        mFlightStickWrapper = new SnobotFlightstickJoystick(stick1, stick2, aLogger);
        mXboxWrapper = new SnobotXBoxDriverJoystick(stick1, aLogger);

        mStickTypeChooser.addDefault("X-box", mXboxWrapper);
        mStickTypeChooser.addObject("Flight Stick", mFlightStickWrapper);
        SmartDashboard.putData("Stick Type", mStickTypeChooser);

        mModeTypeChooser.addDefault("Tank", DriveMode.Tank);
        mModeTypeChooser.addObject("Arcade", DriveMode.Arcade);
        SmartDashboard.putData("Drive Mode", mModeTypeChooser);

        mActiveStick = mXboxWrapper;
    }

    @Override
    public void init()
    {
        mFlightStickWrapper.init();
        mXboxWrapper.init();
    }

    @Override
    public void update()
    {
        IDriverJoystick selectedStick = (IDriverJoystick) mStickTypeChooser.getSelected();
        if (selectedStick != null)
        {
            mActiveStick = selectedStick;
        }

        DriveMode driveMode = (DriveMode) mModeTypeChooser.getSelected();
        if (driveMode != null)
        {
            setDriveMode(driveMode);
        }

        mActiveStick.update();
    }

    @Override
    public void setDriveMode(DriveMode aDriveMode)
    {
        mFlightStickWrapper.setDriveMode(aDriveMode);
        mXboxWrapper.setDriveMode(aDriveMode);
    }

    @Override
    public void control()
    {
        mActiveStick.control();
    }

    @Override
    public void rereadPreferences()
    {
        mActiveStick.rereadPreferences();
    }

    @Override
    public void updateSmartDashboard()
    {
        mActiveStick.updateSmartDashboard();
    }

    @Override
    public void updateLog()
    {
        mActiveStick.updateLog();
    }

    @Override
    public void stop()
    {
        mActiveStick.stop();
    }

    @Override
    public double getLeftY()
    {
        return mActiveStick.getLeftY();
    }

    @Override
    public double getRightY()
    {
        return mActiveStick.getRightY();
    }

    @Override
    public double getSpeed()
    {
        return mActiveStick.getSpeed();
    }

    @Override
    public double getRotate()
    {
        return mActiveStick.getRotate();
    }

    @Override
    public DriveMode getDriveMode()
    {
        return mActiveStick.getDriveMode();
    }

    @Override
    public boolean getDriveForward()
    {
        return mActiveStick.getDriveForward();
    }

    @Override
    public boolean getDriveBackward()
    {
        return mActiveStick.getDriveBackward();
    }

    @Override
    public boolean isReducedSpeedMode()
    {
        return mActiveStick.isReducedSpeedMode();
    }

}
