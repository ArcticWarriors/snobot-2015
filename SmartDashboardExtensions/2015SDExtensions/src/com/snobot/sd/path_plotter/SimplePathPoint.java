package com.snobot.sd.path_plotter;

public class SimplePathPoint
{
    public int mSegment;
    public double mTime;
    public double mPosition;
    public double mVelocity;
    public double mAcceleration;

    public SimplePathPoint(int aSegment, double aTime, double aPosition, double aVelocity, double aAccel)
    {
        mSegment = aSegment;
        mTime = aTime;
        mPosition = aPosition;
        mVelocity = aVelocity;
        mAcceleration = aAccel;
    }

    public SimplePathPoint()
    {
    }

    @Override
    public String toString()
    {
        return "SimplePathPoint [segment=" + mSegment + ", time=" + mTime + ", position=" + mPosition + ", velocity=" + mVelocity + ", acceleration="
                + mAcceleration + "]";
    }

}
