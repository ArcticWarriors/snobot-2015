package com.snobot.trajectory.config;

import java.util.ArrayList;
import java.util.List;

public class GeneratorConfig
{


    private String output_dir;
    private double wheelbase;
    private double dt;

    private List<PathConfig> paths;
    private List<SimpleConfig> simple_paths;
    private List<SimpleConfig> turns;

    public GeneratorConfig()
    {
        paths = new ArrayList<PathConfig>();
        simple_paths = new ArrayList<SimpleConfig>();
        turns = new ArrayList<SimpleConfig>();
    }

    public double getWheelbase()
    {
        return wheelbase;
    }

    public void setWheelbase(double wheelbase)
    {
        this.wheelbase = wheelbase;
    }

    public double getDt()
    {
        return dt;
    }

    public void setDt(double dt)
    {
        this.dt = dt;
    }

    public List<PathConfig> getPaths()
    {
        return paths;
    }

    public void setPaths(List<PathConfig> paths)
    {
        this.paths = paths;
    }

    public String getOutput_dir()
    {
        return output_dir;
    }

    public void setOutput_dir(String output_dir)
    {
        this.output_dir = output_dir;
    }

    public List<SimpleConfig> getSimple_paths()
    {
        return simple_paths;
    }

    public void setSimple_paths(List<SimpleConfig> simple_paths)
    {
        this.simple_paths = simple_paths;
    }

    public List<SimpleConfig> getTurns()
    {
        return turns;
    }

    public void setTurns(List<SimpleConfig> turns)
    {
        this.turns = turns;
    }

    @Override
    public String toString()
    {
        return "GeneratorConfig [output_dir=" + output_dir + ", wheelbase=" + wheelbase + ", dt=" + dt + ", paths=" + paths + "]";
    }


}
