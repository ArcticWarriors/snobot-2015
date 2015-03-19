package com.snobot.simulator.sim;

import com.snobot.simulator.AnalogWrapper;

public class PotWrapper
{
    private AnalogWrapper mWrapper;
    private double mDistance;
    private double mMinVoltage;
    private double mVoltsPerValue;

    public PotWrapper(AnalogWrapper aWrapper, double aMinVoltage, double aVoltsPerValue)
    {
        mWrapper = aWrapper;
        mDistance = 0;
        mMinVoltage = aMinVoltage;
        mVoltsPerValue = aVoltsPerValue;
    }

    public void addDistance(double distance_travelled)
    {
        mDistance += distance_travelled;

        double voltage = (mDistance * mVoltsPerValue) + mMinVoltage;
        mWrapper.setVoltage(voltage);
    }

    public double getDistance()
    {
        return mDistance;
    }

}
