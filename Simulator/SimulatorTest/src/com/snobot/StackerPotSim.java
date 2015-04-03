package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.SpeedControllerWrapper;

public class StackerPotSim
{
    private AnalogWrapper mWrapper;
    private SpeedControllerWrapper mSpeedController;

    public StackerPotSim(AnalogWrapper aWrapper, SpeedControllerWrapper aSpeedController)
    {
        mWrapper = aWrapper;
        mSpeedController = aSpeedController;
    }

    public void update()
    {
        double ipv = 24 / 1.3769505;
        double voltage = 3.596190 - (mSpeedController.getPosition() - 24) / ipv;

        mWrapper.setVoltage(voltage);
    }

}
