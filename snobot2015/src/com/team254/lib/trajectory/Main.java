package com.team254.lib.trajectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.team254.lib.trajectory.io.TextFileSerializer;

/**
 *
 * @author Jared341
 */
public class Main
{
    public static String joinPath(String path1, String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    private static boolean writeFile(String path, String data)
    {
        try
        {
            File file = new File(path);

            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        }
        catch (IOException e)
        {
            return false;
        }

        return true;
    }

    private static void generate(TrajectoryGenerator.Config config, WaypointSequence p, String directory, String path_name, double kWheelbaseWidth)
    {

        Path path = PathGenerator.makePath(p, config,
                kWheelbaseWidth, path_name);

        // Outputs to the directory supplied as the first argument.
        TextFileSerializer js = new TextFileSerializer();
        String serialized = js.serialize(path);
        // System.out.print(serialized);
        String fullpath = joinPath(directory, path_name + ".txt");
        if (!writeFile(fullpath, serialized))
        {
            System.err.println(fullpath + " could not be written!!!!1");
            System.exit(1);
        }
        else
        {
            System.out.println("Wrote " + fullpath);
        }
    }

    public static void main(String[] args)
    {
        String directory = "resources/paths";
        if (args.length >= 1)
        {
            directory = args[0];
        }

        final double kWheelbaseWidth = 25.5 / 12;
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();


        {
            config.dt = .01;
            config.max_acc = 8.0;
            config.max_jerk = 50.0;
            config.max_vel = 10.0;
            // Path name must be a valid Java class name.
            final String path_name = "InsideLanePathFar";

            // Description of this auto mode path.
            // Remember that this is for the GO LEFT CASE!
            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(7.0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(14.0, 1.0, 0));

            generate(config, p, directory, path_name, kWheelbaseWidth);
        }

        {
            final String path_name = "CenterLanePathFar";

            config.dt = .01;
            config.max_acc = 8.0;
            config.max_jerk = 50.0;
            config.max_vel = 10.0;

            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(7.0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(14.0, 5.0, Math.PI / 12.0));

            generate(config, p, directory, path_name, kWheelbaseWidth);
        }

        {
            final String path_name = "InsideLanePathClose";

            config.dt = .01;
            config.max_acc = 8.5;
            config.max_jerk = 50.0;
            config.max_vel = 12.0;

            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(7.0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(15.0, 3, Math.PI / 12.0));

            generate(config, p, directory, path_name, kWheelbaseWidth);
        }

        {
            // Path name must be a valid Java class name.
            final String path_name = "StraightAheadPath";

            config.dt = .01;
            config.max_acc = 9.0;
            config.max_jerk = 50.0;
            config.max_vel = 11.75;

            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(14, 0, 0));

            generate(config, p, directory, path_name, kWheelbaseWidth);
        }
    }
}
