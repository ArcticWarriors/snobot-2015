package com.snobot2012.intake;

import com.snobot.xlib.ISubsystem;
import com.snobot2012.SmartDashboardNames;
import com.snobot2012.ui.OperatorJoystick;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotIntake implements ISubsystem
{

    private SpeedController mIntakeMotor;
    private OperatorJoystick mIntakeJoystick;

    public SnobotIntake(SpeedController aController, OperatorJoystick aJoystick)
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
    }

    @Override
    public void control()
    {
        if (mIntakeJoystick.useIntake())
        {
            mIntakeMotor.set(1);
        }
        else
        {
            stop();
        }
    }

    @Override
    public void rereadPreferences()
    {
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.INTAKE_SPEED, mIntakeMotor.get());

    }

    @Override
    public void updateLog()
    {
    }

    @Override
    public void stop()
    {
    }

}
