package com.team254.lib.trajectory;

public class Waypoint
{

    public Waypoint(double x, double y, double theta)
    {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public Waypoint(Waypoint tocopy)
    {
        this.x = tocopy.x;
        this.y = tocopy.y;
        this.theta = tocopy.theta;
    }

    public double x;
    public double y;
    public double theta;
}