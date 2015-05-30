package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.ISimulatorUpdater;

public class StackerPotSim implements ISimulatorUpdater
{
    private AnalogWrapper mWrapper;
    private SpeedControllerWrapper mSpeedController;

    private double mThrow;
    private double mMaxVoltage;
    private double mMinVoltage;

    public StackerPotSim(AnalogWrapper aWrapper, SpeedControllerWrapper aSpeedController)
    {
        mWrapper = aWrapper;
        mSpeedController = aSpeedController;

        mThrow = mMaxVoltage = mMinVoltage = 1;
        // setParameters(24.0, 3.596190, 4.9731405);
    }

    public void setParameters(double aThrow, double aMaxVoltage, double aMinVoltage)
    {
        mThrow = aThrow;
        mMaxVoltage = aMaxVoltage;
        mMinVoltage = aMinVoltage;
    }

    public void update()
    {
        double voltage_diff = mMinVoltage - mMaxVoltage;
        double ipv = mThrow / voltage_diff;
        double voltage = mMaxVoltage - (mSpeedController.getPosition() - mThrow) / ipv;

        mWrapper.setVoltage(voltage);
    }

}
