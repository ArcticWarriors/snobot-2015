package com.snobot.simulator;

import edu.wpi.first.wpilibj.hal.HALUtil;

public class SpeedControllerWrapper extends ASensorWrapper
{

    private double mVoltagePercent;
    private double mMaxSpeed;
    private double mVelocity;
    private double mPosition;

    public SpeedControllerWrapper(int index)
    {
        super("Speed Controller " + index);
        mVoltagePercent = 0;
        mMaxSpeed = 1;
    }

    public void setMotorParameters(MotorParameters aParams, double rpmToDistance, double aMass)
    {
        setMotorParameters(1);
    }

    public void setMotorParameters(double aMaxSpeed)
    {
        mMaxSpeed = aMaxSpeed;
        System.out.println(mName + ": Max Speed=" + mMaxSpeed);
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

        // if (mName.equals("LeftDrive"))
        // {
        // System.out.println("Speed: " + speed + ", Velocity: " + mVelocity + ", Position: " + mPosition);
        // }
    }

    public double getPosition()
    {
        return mPosition;
    }

    public void resetDistance()
    {
        mPosition = 0;
    }
}
