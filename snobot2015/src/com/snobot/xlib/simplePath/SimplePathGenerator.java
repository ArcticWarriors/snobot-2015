package com.snobot.xlib.simplePath;

import java.util.ArrayList;
import java.util.List;

public class SimplePathGenerator
{
    public List<SimplePathPoint> generate(double aMaxVelocity, double aMaxAccel, double aPosition, double aDt)
    {
        ArrayList<SimplePathPoint> output = new ArrayList<>();

        double pos = 0.0;
        double vel = 0.0;
        for (double t = 0; t < (aMaxVelocity / aMaxAccel); t += aDt)
        {
            pos = .5 * aMaxAccel * (t * t);
            vel = aMaxAccel * t;
            SimplePathPoint point = new SimplePathPoint(1, t, pos, vel, aMaxAccel);
            output.add(point);

        }

        for (double t = (aMaxVelocity / aMaxAccel); t < (aPosition / aMaxVelocity); t += aDt)
        {
            pos = .5 * ((aMaxVelocity * aMaxVelocity) / aMaxAccel) + aMaxVelocity * (t - (aMaxVelocity / aMaxAccel));
            vel = aMaxVelocity;
            SimplePathPoint point = new SimplePathPoint(2, t, pos, vel, 0);
            output.add(point);
        }

        for (double t = (aPosition / aMaxVelocity); t < aMaxVelocity / aMaxAccel + aPosition / aMaxVelocity; t += aDt)
        {
            pos = aPosition - .5 * aMaxAccel * Math.pow((t - ((aMaxVelocity / aMaxAccel) + (aPosition / aMaxVelocity))), 2);
            vel = aMaxAccel * ((aMaxVelocity / aMaxAccel) + (aPosition / aMaxVelocity) - t);
            SimplePathPoint point = new SimplePathPoint(3, t, pos, vel, -aMaxAccel);
            output.add(point);
        }


        return output;
    }

}
