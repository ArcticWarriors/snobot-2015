package com.snobot.xlib.simplePath;

import java.util.ArrayList;
import java.util.List;

public class SimplePathGenerator
{
    public List<SimplePathPoint> generate(double aMaxVelocity, double aMaxAccel, double aPosition, double aDt)
    {
        ArrayList<SimplePathPoint> output = new ArrayList<>();

        double t1 = aMaxVelocity / aMaxAccel;
        double t2 = aPosition / aMaxVelocity;
        double t3 = aMaxVelocity / aMaxAccel + aPosition / aMaxVelocity;

        double pos = 0.0;
        double vel = 0.0;
        for (double t = 0; t < t1; t += aDt)
        {
            pos = .5 * aMaxAccel * (t * t);
            vel = aMaxAccel * t;
            SimplePathPoint point = new SimplePathPoint(1, aDt, pos, vel, aMaxAccel);
            output.add(point);

        }

        for (double t = t1; t < t2; t += aDt)
        {
            pos = .5 * ((aMaxVelocity * aMaxVelocity) / aMaxAccel) + aMaxVelocity * (t - (aMaxVelocity / aMaxAccel));
            vel = aMaxVelocity;
            SimplePathPoint point = new SimplePathPoint(2, aDt, pos, vel, 0);
            output.add(point);
        }

        for (double t = t2; t < t3; t += aDt)
        {
            pos = aPosition - .5 * aMaxAccel * Math.pow((t - ((aMaxVelocity / aMaxAccel) + (aPosition / aMaxVelocity))), 2);
            vel = aMaxAccel * ((aMaxVelocity / aMaxAccel) + (aPosition / aMaxVelocity) - t);
            SimplePathPoint point = new SimplePathPoint(3, aDt, pos, vel, -aMaxAccel);
            output.add(point);
        }

        if (output.get(output.size() - 1).mVelocity != 0)
        {
            output.add(new SimplePathPoint(3, aDt, aPosition, 0, 0));
        }


        return output;
    }

}
