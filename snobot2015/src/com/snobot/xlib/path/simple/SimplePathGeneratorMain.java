package com.snobot.xlib.path.simple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.snobot.xlib.path.SimplePathPoint;

public class SimplePathGeneratorMain
{

    private static final double sDEFAULT_DRIVE_VEL = 2 * 12;
    private static final double sDEFAULT_DRIVE_ACCEL = 5 * 12;

    private static final double sDEFAULT_TURN_VEL = 60;
    private static final double sDEFAULT_TURN_ACCEL = 200;
    
    
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

    // /////////////////////////////////
    // Test Files
    // /////////////////////////////////
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

    private SimplePathGeneratorConfig genTestTurn90Slow(double dt)
    {
        double max_vel = 20;
        double max_accel = 600;
        double position = 90;

        return new SimplePathGeneratorConfig("TestTurn90Slow", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genTestTurn90Fast(double dt)
    {
        double max_vel = 180;
        double max_accel = 600;
        double position = 90;

        return new SimplePathGeneratorConfig("TestTurn90", max_vel, max_accel, position, dt);
    }

    // /////////////////////////////////
    // Tactical Files
    // /////////////////////////////////
    private SimplePathGeneratorConfig genTurnPositive90(double dt)
    {
        double max_vel = 45;
        double max_accel = sDEFAULT_TURN_ACCEL;
        double position = 90;

        return new SimplePathGeneratorConfig("TurnPositive90", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genTurnPositive90NoTote(double dt)
    {
        double max_vel = sDEFAULT_TURN_VEL;
        double max_accel = sDEFAULT_TURN_ACCEL;
        double position = 90;

        return new SimplePathGeneratorConfig("TurnPositive90NoTote", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genDriveForwardsLandmarkToAutoZone(double dt)
    {
        double max_vel   = sDEFAULT_DRIVE_VEL;
        double max_accel = sDEFAULT_DRIVE_ACCEL;
        double position = 8 * 12;

        return new SimplePathGeneratorConfig("DriveForwardsLandmarkToAutoZone", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genDriveBackwardsLandmarkToAutoZone(double dt)
    {
        double max_vel   = -sDEFAULT_DRIVE_VEL;
        double max_accel = -sDEFAULT_DRIVE_ACCEL;
        double position = -8 * 12;

        return new SimplePathGeneratorConfig("DriveBackwardsLandmarkToAutoZone", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genStartingIntoAuto(double dt)
    {
        double max_vel   = sDEFAULT_DRIVE_VEL;
        double max_accel = sDEFAULT_DRIVE_ACCEL;
        double position = 10 * 12;

        return new SimplePathGeneratorConfig("StartingIntoAuto", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genBackupOffDatTote(double dt)
    {
        double max_vel = -5 * 12;
        double max_accel = -20 * 12;
        double position = -2.5 * 12;

        return new SimplePathGeneratorConfig("BackupOffDatTote", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genBackupOffDatContainer(double dt)
    {
        double max_vel = -5 * 12;
        double max_accel = -20 * 12;
        double position = -6.5 * 12;

        return new SimplePathGeneratorConfig("BackupOffDatContainer", max_vel, max_accel, position, dt);
    }

    private SimplePathGeneratorConfig genGrabContainer(double dt)
    {
        double max_vel = -5 * 12;
        double max_accel = -20 * 12;
        double position = -17;

        return new SimplePathGeneratorConfig("GrabContainer", max_vel, max_accel, position, dt);
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
        configs.add(genTestTurn90Slow(dt));
        configs.add(genTestTurn90Fast(dt));

        configs.add(genDriveForwardsLandmarkToAutoZone(dt));
        configs.add(genDriveBackwardsLandmarkToAutoZone(dt));
        configs.add(genTurnPositive90(dt));
        configs.add(genBackupOffDatTote(dt));
        configs.add(genStartingIntoAuto(dt));
        configs.add(genTurnPositive90NoTote(dt));
        configs.add(genBackupOffDatContainer(dt));
        configs.add(genGrabContainer(dt));

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
