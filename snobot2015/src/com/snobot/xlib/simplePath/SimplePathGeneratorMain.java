package com.snobot.xlib.simplePath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimplePathGeneratorMain
{
    private class SimplePathGeneratorConfig
    {
        String mName;
        double mMaxVelocity;
        double mMaxAcceleration;
        double mEndpoint;
        double mDt;

        public SimplePathGeneratorConfig(String aName, double aVel, double aAccel, double aEndpoint, double aDt)
        {
            mName = aName;
            mMaxVelocity = aVel;
            mMaxAcceleration = aAccel;
            mEndpoint = aEndpoint;
            mDt = aDt;
        }
    }

    public SimplePathGeneratorMain()
    {
        double dt = .02;
        String directory = "resources/paths/";
        File dir = new File(directory);
        if (!dir.exists())
        {
            dir.mkdir();
        }

        List<SimplePathGeneratorConfig> configs = new ArrayList<SimplePathGeneratorConfig>();

        configs.add(new SimplePathGeneratorConfig("TestDriveStraight", 7, 100, 7, dt));
        configs.add(new SimplePathGeneratorConfig("TestTurn90", 180, 1800, 90, dt));
        configs.add(new SimplePathGeneratorConfig("TestTurn90Slow", 20, 1800, 90, dt));

        SimplePathGenerator gen = new SimplePathGenerator();
        SimplePathSerializer serializer = new SimplePathSerializer();

        for (SimplePathGeneratorConfig config : configs)
        {
            File out_path = new File(directory + config.mName + ".csv");
            System.out.println("Generating path : " + out_path.getAbsolutePath());

            List<SimplePathPoint> points = gen.generate(
                    config.mMaxVelocity, config.mMaxAcceleration, config.mEndpoint, config.mDt);
            serializer.write(points, out_path.getPath());
        }

    }
    public static void main(String[] args)
    {
        new SimplePathGeneratorMain();
    }
}
