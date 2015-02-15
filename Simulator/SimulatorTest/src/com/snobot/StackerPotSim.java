package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.sim.DistanceCalculator;

public class StackerPotSim implements DistanceCalculator
{
    private AnalogWrapper mWrapper;
    private double mDistance;

    public StackerPotSim(AnalogWrapper aWrapper)
    {
        mWrapper = aWrapper;
        mDistance = 0;
    }

    // TODO replace numbers with constants
    @Override
    public void addDistance(double distance_travelled)
    {
        mDistance += distance_travelled;

        double ipv = 24 / 1.3769505;
        double voltage = 3.596190 - (mDistance - 24) / ipv;

        mWrapper.setVoltage(voltage);
    }

    @Override
    public double getDistance()
    {
        return mDistance;
    }

}
