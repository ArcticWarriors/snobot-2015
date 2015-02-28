package com.snobot.xlib.simplePath;

public class SimplePathPoint
{
    int mSegment;
    double mTime;
    double mPosition;
    double mVelocity;
    double mAcceleration;

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
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString()
    {
        return "SimplePathPoint [segment=" + mSegment + ", time=" + mTime + ", position=" + mPosition + ", velocity=" + mVelocity + ", acceleration="
                + mAcceleration + "]";
    }

}
