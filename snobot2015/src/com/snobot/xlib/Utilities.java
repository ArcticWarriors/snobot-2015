package com.snobot.xlib;

public class Utilities
{
    public static double getDifferenceInAngleRadians(double aFrom, double aTo)
    {
        return boundAngleNegPiToPiRadians(aTo - aFrom);
    }

    public static double boundAngle0to2PiRadians(double aAngle)
    {
        // Naive algorithm
        while (aAngle >= 2.0 * Math.PI)
        {
            aAngle -= 2.0 * Math.PI;
        }
        while (aAngle < 0.0)
        {
            aAngle += 2.0 * Math.PI;
        }
        return aAngle;
    }

    public static double boundAngleNegPiToPiRadians(double aAngle)
    {
        while (aAngle >= Math.PI)
        {
            aAngle -= 2.0 * Math.PI;
        }

        while (aAngle < -Math.PI)
        {
            aAngle += 2.0 * Math.PI;
        }
        return aAngle;
    }
}
