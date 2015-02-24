package com.snobot.trajectory;

import java.util.ArrayList;
import java.util.List;

import com.snobot.trajectory.config.SimpleConfig;

public class SimplePathGenerator
{

    public String serialize(SimpleConfig aConfig, double dt)
    {
        double max_vel = aConfig.getMax_vel();
        double max_accel = aConfig.getMax_accel();
        double destination = aConfig.getPosition();

        double t1 = max_vel / max_accel;
        double t2 = destination / max_vel;
        double t3 = t1 + t2;

        List<SimplePathPoint> turnInfo = new ArrayList<SimplePathPoint>();

        for (double t = 0; t < t1; t += dt)
        {
            double current_angle = .5 * max_accel * t * t;
            double current_velocity = max_accel * t;
            double current_accel = max_accel;

            turnInfo.add(new SimplePathPoint(0, t, current_angle, current_velocity, current_accel));
        }

        double end_accel_pos = turnInfo.get(turnInfo.size() - 1).getAngle();
        for (double t = t1; t < t2; t += dt)
        {
            double current_angle = end_accel_pos + max_vel * (t - t1 + dt);
            double current_velocity = max_vel;
            double current_accel = 0;

            turnInfo.add(new SimplePathPoint(1, t, current_angle, current_velocity, current_accel));
        }

        for (double t = t2; t < t3; t += dt)
        {
            double t_field = t - (t1 + t2);
            double current_angle = destination - .5 * max_accel * t_field * t_field;
            double current_velocity = -max_accel * t_field;
            double current_accel = -max_accel;

            turnInfo.add(new SimplePathPoint(2, t, current_angle, current_velocity, current_accel));
        }

        String output = "";
        output += String.format("Segment,Time,Position,Velocity,Accel\n");

        for (SimplePathPoint info : turnInfo)
        {
            output += String.format("%d, %.3f, %.3f, %.3f, %.3f\n", info.getSegment(), info.getDt(), info.getAngle(), info.getVelocity(),
                    info.getAcceleration());
        }

        return output;
    }
}
