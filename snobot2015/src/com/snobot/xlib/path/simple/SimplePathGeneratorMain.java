package com.snobot.xlib.path.simple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.snobot.xlib.path.SimplePathPoint;

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

    private SimplePathGeneratorConfig genTestDriveStraight(double dt)
    {
        double max_vel = 4;
        double max_accel = 10;
        double position = 7;

        return new SimplePathGeneratorConfig("TestDriveStraight", max_vel * 12, max_accel * 12, position * 12, dt);
    }

    private SimplePathGeneratorConfig genTestDriveStraightSlow(double dt)
    {
        double max_vel = 2;
        double max_accel = 5;
        double position = 10;

        return new SimplePathGeneratorConfig("TestDriveSlowStraight", max_vel * 12, max_accel * 12, position * 12, dt);
    }

    private SimplePathGeneratorConfig genTestDriveTurn90(double dt)
    {
        double max_vel = 3.5;
        double max_accel = 20;
        double position = 1;

        return new SimplePathGeneratorConfig("TestDrive90Degrees", max_vel * 12, max_accel * 12, position * 12, dt);
    }

    private SimplePathGeneratorConfig genTestDriveAutoZone(double dt)
    {
        double max_vel = 4;
        double max_accel = 20;
        double position = 9.5;

        return new SimplePathGeneratorConfig("TestDriveAutoZone", max_vel * 12, max_accel * 12, position * 12, dt);
    }

    private SimplePathGeneratorConfig genTestDriveCenterStep(double dt)
    {
        double max_vel = 5;
        double max_accel = 20;
        double position = 19;

        return new SimplePathGeneratorConfig("TestDriveCenterStep", max_vel * 12, max_accel * 12, position * 12, dt);
    }

    private SimplePathGeneratorConfig genTestDriveAutoZoneFromStep(double dt)
    {
        double max_vel = -4;
        double max_accel = 20;
        double position = 9.5;

        return new SimplePathGeneratorConfig("TestDriveAutoZoneFromStep", max_vel * 12, max_accel * 12, position * 12, dt);

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

        configs.add(genTestDriveStraight(dt));
        configs.add(genTestDriveStraightSlow(dt));
        configs.add(genTestDriveAutoZone(dt));
        configs.add(genTestDriveTurn90(dt));
        configs.add(genTestDriveCenterStep(dt));
        // configs.add(genTestDriveAutoZoneFromStep(dt));
        configs.add(new SimplePathGeneratorConfig("TestTurn90", 180, 600, 90, dt));
        configs.add(new SimplePathGeneratorConfig("TestTurn90Slow", 20, 600, 90, dt));

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
