package com.snobot.trajectory.config;

import java.util.ArrayList;
import java.util.List;

public class SplinePathConfig
{
    public static class WaypointConfig
    {
        private double x;
        private double y;
        private double angle;

        public double getX()
        {
            return x;
        }

        public void setX(double x)
        {
            this.x = x;
        }

        public double getY()
        {
            return y;
        }

        public void setY(double y)
        {
            this.y = y;
        }

        public double getAngle()
        {
            return angle;
        }

        public void setAngle(double angle)
        {
            this.angle = angle;
        }

        @Override
        public String toString()
        {
            return "WaypointConfig [x=" + x + ", y=" + y + ", angle=" + angle + "]";
        }

    }

    private String name;
    private double max_vel;
    private double max_accel;
    private double max_jerk;

    private List<WaypointConfig> waypoints;

    public SplinePathConfig()
    {
        waypoints = new ArrayList<WaypointConfig>();
    }

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

    public double getMax_jerk()
    {
        return max_jerk;
    }

    public void setMax_jerk(double max_jerk)
    {
        this.max_jerk = max_jerk;
    }

    public List<WaypointConfig> getWaypoints()
    {
        return waypoints;
    }

    public void setWaypoints(List<WaypointConfig> waypoints)
    {
        this.waypoints = waypoints;
    }

    @Override
    public String toString()
    {
        return "PathConfig [name=" + name + ", max_vel=" + max_vel + ", max_accel=" + max_accel + ", max_jerk=" + max_jerk + ", waypoints="
                + waypoints + "]";
    }

}