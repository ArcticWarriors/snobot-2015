package com.snobot.xlib.path.simple;

import java.text.DecimalFormat;
import java.util.List;

import com.snobot.SmartDashboardNames;
import com.snobot.xlib.path.IdealPlotSerializer;
import com.snobot.xlib.path.SimplePathPoint;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimplePathFollower
{
    private double mKp; // Proportional to error term
    private double mKd; // Derivitave of error term
    private double mKv; // Feed forward velocity term
    private double mKa; // Feed forward acceleration term

    private double mLastError;
    private int mPathIndex = 0;
    private List<SimplePathPoint> mListPoints;

    private double mLastPosition;

    public SimplePathFollower(List<SimplePathPoint> aPath,
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
        mLastPosition = 0;
        mListPoints = aPath;

        if (SmartDashboard.getString(SmartDashboardNames.sSIMPLE_IDEAL_PATH, "").isEmpty())
        {
            sendIdealPath();
        }
    }

    public void init()
    {
        sendIdealPath();
    }

    private void sendIdealPath()
    {
        SmartDashboard.putString(SmartDashboardNames.sSIMPLE_IDEAL_PATH, IdealPlotSerializer.serializePath(mListPoints));
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

        double velocity = (aCurrPosition - mLastPosition) / point.mTime;

        SimplePathPoint actual_point = new SimplePathPoint(point.mSegment, point.mTime, aCurrPosition, velocity, 0);

        // System.out.println("i = " + mPathIndex + ", cur = " + aCurrPosition +
        // ", " + mLastPosition + ", " + velocity);

        DecimalFormat df = new DecimalFormat("#.000");
        System.out.println("" +
                "Current: " + df.format(aCurrPosition) + ", " +
                "Desired: " + df.format(point.mPosition) + ", " +
                "p: " + df.format(p_term) + ", " +
                "d: " + df.format(d_term) + ", " +
                "v: " + df.format(v_term) + ", " +
                "a: " + df.format(a_term) + ", " +
                "output: " + output);

        String point_info = mPathIndex + "," + IdealPlotSerializer.serializePathPoint(actual_point);
        SmartDashboard.putString(SmartDashboardNames.sSIMPLE_PATH_POINT_INFO, point_info);

        mLastError = error;
        mLastPosition = aCurrPosition;
        mPathIndex++;

        return output;
    }

    public boolean isFinished()
    {
        return (mPathIndex >= mListPoints.size());

    }
}
