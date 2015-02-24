package com.snobot.trajectory.config;

public class SimpleConfig
{
    private String name;
    private double max_vel;
    private double max_accel;
    private double position;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getMax_vel()
    {
        return max_vel;
    }

    public void setMax_vel(double max_vel)
    {
        this.max_vel = max_vel;
    }

    public double getMax_accel()
    {
        return max_accel;
    }

    public void setMax_accel(double max_accel)
    {
        this.max_accel = max_accel;
    }

    public double getPosition()
    {
        return position;
    }

    public void setPosition(double position)
    {
        this.position = position;
    }

    @Override
    public String toString()
    {
        return "TurningConfig [name=" + name + ", max_vel=" + max_vel + ", max_accel=" + max_accel + ", degree=" + position
                + "]";
    }

}
