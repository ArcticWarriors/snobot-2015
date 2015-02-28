package com.snobot.xlib.simplePath;

import java.util.List;

public class SimplePathFollower
{
    double mKp;
    double mKff;
    int mPathIndex = 0;
    List<SimplePathPoint> mListPoints;

    public SimplePathFollower(List<SimplePathPoint> aListPoints, double aKp, double aKff)
    {
        mKp = aKp;
        mKff = aKff;
        mListPoints = aListPoints;
    }

    public double calculate(double aCurrPosition)
    {

        double error = mListPoints.get(mPathIndex).mPosition - aCurrPosition;
        double currVel = mListPoints.get(mPathIndex).mVelocity;

        mPathIndex++;

        return (error * mKp + mKff * currVel);
    }

    public boolean isFinished()
    {
        return (mPathIndex >= mListPoints.size());

    }
}
