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
        if (mPathIndex >= mListPoints.size())
        {
            return 0;
        }
        double desiredPosition = mListPoints.get(mPathIndex).mPosition;

        double error = desiredPosition - aCurrPosition;
        double currVel = mListPoints.get(mPathIndex).mVelocity;

        double p_term = error * mKp;
        double v_term = mKff * currVel;
        double output = p_term + v_term;

        System.out.println("Current : " + aCurrPosition + ", desired: " + desiredPosition + ", p: " + p_term + ", v: " + v_term + ", " + mKff
                + ", output = " + output);

        mPathIndex++;

        return p_term + v_term;
    }

    public boolean isFinished()
    {
        return (mPathIndex >= mListPoints.size());

    }
}
