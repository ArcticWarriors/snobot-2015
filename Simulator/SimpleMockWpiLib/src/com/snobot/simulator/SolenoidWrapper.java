package com.snobot.simulator;

public class SolenoidWrapper extends ASensorWrapper
{

    private boolean mState;

    public SolenoidWrapper(int aIndex)
    {
        super("Solenoid " + aIndex);
        mState = false;
    }

    public boolean get()
    {
        return mState;
    }

    public void set(boolean aState)
    {
        mState = aState;
    }
}
