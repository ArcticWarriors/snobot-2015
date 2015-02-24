package com.snobot.xlib.simple_trajectory;

public class SimplePathPoint
{
    public double dt;
    public double angle;
    public double velocity;
    public double acceleration;

    public SimplePathPoint()
    {
    }

    public SimplePathPoint(double angle, double velocity, double acceleration, double dt)
    {
        this.angle = angle;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.dt = dt;
    }
    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getDt()
    {
        return dt;
    }

    public void setDt(double dt)
    {
        this.dt = dt;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    public double getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(double acceleration)
    {
        this.acceleration = acceleration;
    }

    @Override
    public String toString()
    {
        return "SimplePathPoint [dt=" + dt + ", angle=" + angle + ", velocity=" + velocity + ", acceleration=" + acceleration + "]";
    }

}
