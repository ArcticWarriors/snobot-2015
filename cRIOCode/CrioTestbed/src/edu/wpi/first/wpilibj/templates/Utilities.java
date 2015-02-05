package edu.wpi.first.wpilibj.templates;

/**
 * A utility class with functions to limit values, check for deadbands, etc
 *
 * @author PJ
 */
public class Utilities
{

    /**
     * Will return 0 if the input is within the given deadband
     *
     * @param input The input
     * @param deadband The deadband
     * @return 0 if it is within the deadband, otherwise the original value
     */
    public static double StopInDeadband(double input, double deadband)
    {
        if (input < deadband && input > -deadband)
        {
            return 0;
        }
        return input;
    }

    /**
     * Clips the output to be in the form -1.0&lt;= input &lt;=1.0
     *
     * @param in The input value
     * @return The clipped output
     */
    public static double LimitMotor(double in)
    {
        return LimitVal(in, -1.0, 1.0);
    }

    /**
     * Limits the given value to be in the format -limit&lt;= input &lt;=limit
     *
     * @param in The input value to constrict
     * @param limit The limit to use for upper and lower bound
     * @return The clipped value
     */
    public static double LimitVal(double in, double limit)
    {
        return LimitVal(in, -limit, limit);
    }

    /**
     * Limits the given value to be in the format min &lt;= input &lt;= max
     *
     * @param in The input value to constrict
     * @param min The minimum value
     * @param max The maximum value
     * @return The clipped value
     */
    public static double LimitVal(double in, double min, double max)
    {
        double output = in;

        if (in > max)
        {
            output = max;
        }
        if (in < min)
        {
            output = min;
        }

        return output;
    }

    /**
     * Converts degrees to radians
     *
     * @param degrees The degrees to convert
     * @return radians
     *
     * @see Utilities#RadianToDegree(double)
     */
    public static double DegreeToRadian(double degrees)
    {
        return degrees * Math.PI / 180.0;
    }

    /**
     * Converts radians to degrees
     *
     * @param rads The radians to convert
     * @return Degrees
     *
     * @see Utilities#DegreeToRadian(double)
     */
    public static double RadianToDegree(double rads)
    {
        return rads * 180.0 / Math.PI;
    }

    /**
     * Returns if the current value is within the deadband of the desired value
     * i.e. -deadband &lt; cur &lt; deadband
     *
     * @param cur The current value
     * @param des The desired value
     * @param deadband The deadband
     * @return True if it is in the deadband, false otherwise
     */
    public static boolean inInclusiveRange(double cur, double des, double deadband)
    {
        if (((cur + deadband) > des) && ((cur - deadband) < des))
        {
            return true;
        }
        return false;
    }

    /**
     * Will transpose the angle (degrees) so that it is between -180 and +180
     *
     * @param aDegrees The angle (in degrees) to transpose
     * @return Angle in form of -180&lt;x&lt;=180
     */
    public static double makeBetween180s(double aDegrees)
    {
        while (aDegrees > 180)
        {
            aDegrees -= 360;
        }
        while (aDegrees < -180)
        {
            aDegrees += 360;
        }
        return aDegrees;
    }

    /**
     * Computes the rotation of an angle by 180 degrees.
     *
     * @param aRadians Angle in the range [-pi, pi].
     * @return Angle in the range [-pi, pi].
     */
    public static double angleRot180(double aRadians)
    {
        aRadians += Math.PI;
        if (aRadians > Math.PI)
        {
            aRadians -= 2 * Math.PI;
        }
        return aRadians;
    }

    /**
     * Computes angle difference a - b, accounting for wrap-around, in Radians
     *
     * @param a Angle in the range [-pi, pi].
     * @param b Angle in the range [-pi, pi].
     * @return Angle in the range [-pi, pi].
     */
    public static double angleDiff(double a, double b)
    {
        double c = a - b;
        if (c < -Math.PI)
        {
            c += 2 * Math.PI;
        }
        else if (c > Math.PI)
        {
            c -= 2 * Math.PI;
        }
        return c;
    }

    /**
     * Computes angle difference a - b, accounting for wrap-around, in Radians
     *
     * @param a Angle in the range [-180, 180].
     * @param b Angle in the range [-180, 180].
     * @return Angle in the range [-180, 180].
     */
    public static double angleDiffD(double aDegree1, double aDegree2)
    {
        double c = aDegree1 - aDegree2;
        if (c < -180)
        {
            c += 360;
        }
        else if (c > 180)
        {
            c -= 360;
        }
        return c;
    }
}
