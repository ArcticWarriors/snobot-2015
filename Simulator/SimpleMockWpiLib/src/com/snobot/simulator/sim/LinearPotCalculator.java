package com.snobot.simulator.sim;

import com.snobot.simulator.SpeedControllerWrapper;

public class LinearPotCalculator
{

    protected SpeedControllerWrapper mSpeedController;
    protected PotWrapper mPotentiometer;
    protected double mKp;

    protected boolean mSetup;

    public LinearPotCalculator(SpeedControllerWrapper aSpeedController, PotWrapper aPotWrapper)
    {
        mSpeedController = aSpeedController;
        mPotentiometer = aPotWrapper;
        mKp = 1.0;

        mSetup = mSpeedController != null && mPotentiometer != null;
        if (!mSetup)
        {
            System.err.println("Warning, you have given a null paramter, simulation is not possible");
        }
    }

    public void setSimulatorParams(double aKp)
    {
        mKp = aKp;
    }

    public void update()
    {
        if (mSetup)
        {
            double distance_travelled = mSpeedController.get() * mKp;
            mPotentiometer.addDistance(distance_travelled);
        }

    }
}
