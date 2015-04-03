package com.snobot.simulator;

public class AnalogWrapper
{

    private double mVoltage;
    private double mAccumulator;

    public void setVoltage(double aVoltage)
    {
        mVoltage = aVoltage;
    }

    public double getVoltage()
    {
        return mVoltage;
    }

    public void setAccumulator(double aValue)
    {
        mAccumulator = aValue;
    }

    public double getAccumulator()
    {
        return mAccumulator;
    }
}
