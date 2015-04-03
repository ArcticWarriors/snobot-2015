package com.snobot.simulator;

public class SolenoidWrapper
{

    private boolean mState;

    public SolenoidWrapper()
    {
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
