package com.snobot.sd2015.spline_plotter;

public class SplineSegment
{

    public double left_pos;
    public double left_vel;
    public double right_pos;
    public double right_vel;
    public double heading;

    public SplineSegment()
    {
    }

    public SplineSegment(double left_pos, double left_vel, double right_pos, double right_vel, double heading)
    {
        this.left_pos = left_pos;
        this.left_vel = left_vel;
        this.right_pos = right_pos;
        this.right_vel = right_vel;
        this.heading = heading;
    }
}