package com.snobot.simulator;

public class SolenoidWrapper extends ASensorWrapper
{
    private final CompressorWrapper mCompressor;
    private boolean mState;
    private double mOnTransitionPressureDrop;
    private double mOffTransitionPressureDrop;

    public SolenoidWrapper(int aIndex)
    {
        super("Solenoid " + aIndex);
        mCompressor = SensorActuatorRegistry.get().getCompressor();
        mState = false;
        mOnTransitionPressureDrop = 10;
        mOffTransitionPressureDrop = 10;
    }

    public boolean get()
    {
        return mState;
    }

    public void set(boolean aState)
    {
        if (aState != mState)
        {
            if (aState)
            {
                mCompressor.solenoidFired(mOnTransitionPressureDrop);
            }
            else
            {
                mCompressor.solenoidFired(mOffTransitionPressureDrop);
            }
        }

        mState = aState;
    }

    public void setTransitionPressure(double aPressure)
    {
        mOnTransitionPressureDrop = mOffTransitionPressureDrop = aPressure;
    }
}
