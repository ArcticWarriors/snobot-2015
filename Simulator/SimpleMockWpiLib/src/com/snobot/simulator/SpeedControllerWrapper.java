package com.snobot.simulator;

import edu.wpi.first.wpilibj.hal.HALUtil;

public class SpeedControllerWrapper
{
    private String mMotorName;

    private double mVoltagePercent;
    private double mMaxSpeed;
    private double mVelocity;
    private double mPosition;

    public SpeedControllerWrapper(int index)
    {
        mVoltagePercent = 0;
        mMaxSpeed = 1;
        mMotorName = "Motor " + index;
    }

    public void setMotorParameters(double aMaxSpeed)
    {
        mMaxSpeed = aMaxSpeed;
    }

    public double get()
    {
        return mVoltagePercent;
    }

    public void set(double speed)
    {
        mVoltagePercent = speed;

        mVelocity = mMaxSpeed * mVoltagePercent;
        mPosition += mVelocity * HALUtil.__sWAIT_TIME;

        if (mMotorName.equals("LeftDrive"))
        {
            System.out.println("Speed: " + speed + ", Position: " + mPosition + ", Velocity: " + mVelocity);
        }
    }

    public double getPosition()
    {
        return mPosition;
    }

    public void resetDistance()
    {
        mPosition = 0;
    }

    public void setName(String aName)
    {
        mMotorName = aName;
    }
}
