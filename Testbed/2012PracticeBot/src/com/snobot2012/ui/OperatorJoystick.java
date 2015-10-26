package com.snobot2012.ui;

import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorJoystick
{
    private Joystick mJoystick;

    public OperatorJoystick(Joystick aJoystick)
    {
        mJoystick = aJoystick;
    }

    public boolean useIntake()
    {
        return mJoystick.getRawAxis(XboxButtonMap.LEFT_TRIGGER) > 0;
    }

    public boolean incrementShooterSpeed()
    {
        return mJoystick.getRawButton(XboxButtonMap.B_BUTTON);
    }

    public boolean decrementShooterSpeed()
    {
        return mJoystick.getRawButton(XboxButtonMap.X_BUTTON);
    }

    public boolean shootButton()
    {
        return mJoystick.getRawAxis(XboxButtonMap.RIGHT_TRIGGER) > 0;
    }

}
