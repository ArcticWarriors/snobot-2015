package com.snobot.xlib.simple_trajectory;

import java.text.DecimalFormat;
import java.util.List;


public class SimplePathFollower
{

    private double kp_;
    private double kd_;
    private double kv_;
    private double ka_;
    private double last_error_;
    private int current_segment;
    private List<SimplePathPoint> profile_;

    public SimplePathFollower()
    {
    }

    public void configure(double kp, double ki, double kd, double kv, double ka)
    {
        kp_ = kp;
        kd_ = kd;
        kv_ = kv;
        ka_ = ka;
    }

    public void reset()
    {
        last_error_ = 0.0;
        current_segment = 0;
    }

    public void setPath(List<SimplePathPoint> profile)
    {
        profile_ = profile;
    }

    public double calculate(double distance_so_far)
    {

        if (current_segment < profile_.size())
        {
            SimplePathPoint segment = profile_.get(current_segment);
            double error = segment.angle - distance_so_far;

            double p_term = kp_ * error;
            double d_term = kd_ * ((error - last_error_) / segment.dt - segment.velocity);
            double va_term = (kv_ * segment.velocity + ka_ * segment.acceleration);
            double output = p_term + d_term + va_term;

            DecimalFormat df = new DecimalFormat("#.000");
            System.out.println("Desired: " + df.format(segment.angle) + ",\t" + "current: " + df.format(distance_so_far) + ",\t" + "Error: "
                    + df.format(error) + ",\t" + "output:" + df.format(output) + ",\t" + "p: " + df.format(p_term) + ",\t" + "d: "
                    + df.format(d_term) + ",\t" + "va: " + df.format(va_term));

            last_error_ = error;
            current_segment++;
            return output;
        }
        else
        {
            return 0;
        }
    }

    public boolean isFinishedTrajectory()
    {
        return current_segment >= profile_.size();
    }

    public int getCurrentSegment()
    {
        return current_segment;
    }

    public int getNumSegments()
    {
        return profile_.size();
    }
}
