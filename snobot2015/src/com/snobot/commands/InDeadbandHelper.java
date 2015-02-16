package com.snobot.commands;

public class InDeadbandHelper
{
    private int mLoopsRequired;
    private int mLoopsFinished;

    public InDeadbandHelper(int aLoopsRequired)
    {
        mLoopsRequired = aLoopsRequired;
    }

    public boolean isFinished(boolean aInRange)
    {
        if (aInRange)
        {
            mLoopsFinished++;
        }
        else
        {
            mLoopsFinished = 0;
        }

        return mLoopsFinished >= mLoopsRequired;
    }

    public int getLoopsGood()
    {
        return mLoopsFinished;
    }
}
