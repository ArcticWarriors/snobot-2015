package com.snobot.xlib.path.simple;

import java.text.DecimalFormat;
import java.util.List;

import com.snobot.xlib.path.SimplePathPoint;

public class SimplePathFollower
{
    private double mKp; // Proportional to error term
    private double mKd; // Derivitave of error term
    private double mKv; // Feed forward velocity term
    private double mKa; // Feed forward acceleration term

    private double mLastError;
    private int mPathIndex = 0;
    private List<SimplePathPoint> mListPoints;

    public SimplePathFollower(List<SimplePathPoint> aListPoints,
            double aKp,
            double aKd,
            double aKv,
            double aKa)
    {
        mKp = aKp;
        mKd = aKd;
        mKv = aKv;
        mKa = aKa;

        mLastError = 0;
        mPathIndex = 0;
        mListPoints = aListPoints;
    }

    public double calculate(double aCurrPosition)
    {
        if (mPathIndex >= mListPoints.size())
        {
            return 0;
        }

        SimplePathPoint point = mListPoints.get(mPathIndex);

        double error = point.mPosition - aCurrPosition;

        double p_term = error * mKp;
        double d_term = mKd * ((error - mLastError) / point.mTime - point.mVelocity);
        double v_term = mKv * point.mVelocity;
        double a_term = mKa * point.mAcceleration;
        double output = p_term + d_term + v_term + a_term;

        DecimalFormat df = new DecimalFormat("#.000");
        System.out.println("" +
                "Current: " + df.format(aCurrPosition) + ", " +
                "Desired: " + df.format(point.mPosition) + ", " +
                "p: " + df.format(p_term) + ", " +
                "d: " + df.format(d_term) + ", " +
                "v: " + df.format(v_term) + ", " +
                "a: " + df.format(a_term) + ", " +
                "output: " + output);

        // System.out.println(
        // "kp = " + mKp + ", " +
        // "kd = " + mKd + ", " +
        // "kv = " + mKv + ", " +
        // "ka = " + mKa);

        mLastError = error;
        mPathIndex++;

        return p_term + v_term;
    }

    public boolean isFinished()
    {
        return (mPathIndex >= mListPoints.size());

    }
}
