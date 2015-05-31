package com.snobot.simulator;

public class CompressorWrapper extends ASensorWrapper
{
    private double mAirPressure;
    private double mChargeRate;

    public CompressorWrapper()
    {
        super("Compressor");
        mAirPressure = 120;
        mChargeRate = .25; // psi charge per 20ms loop
    }

    public void solenoidFired(double aPressure)
    {
        mAirPressure -= aPressure;
        if (mAirPressure < 0)
        {
            mAirPressure = 0;
        }
    }

    public void update()
    {
        mAirPressure += mChargeRate;
        if (mAirPressure > 120)
        {
            mAirPressure = 120;
        }
    }

    public double getAirPressure()
    {
        return mAirPressure;
    }
}
