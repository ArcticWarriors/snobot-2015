package com.snobot.intake;

import com.snobot.xlib.ISubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeMotor implements ISubsystem
{

    private SpeedController mIntakeMotor;
    private Joystick mIntakeJoystick;

    public IntakeMotor(SpeedController aController, Joystick aJoystick)
    {
        mIntakeMotor = aController;
        mIntakeJoystick = aJoystick;
    }

    public void init()
    {

    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void control()
    {
        if (mIntakeJoystick.getRawButton(1))// TODO get the button for intake
        {
            mIntakeMotor.set(1);
        }
        else
        {
            mIntakeMotor.set(0);
        }
    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard()
    {
        // TODO Auto-generated method stub
        SmartDashboard.putNumber("Intake Motor Status", mIntakeMotor.get());

    }

    @Override
    public void updateLog()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

    }

}
