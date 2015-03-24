package com.team254.lib.trajectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private static void generate(TrajectoryGenerator.Config config, List<Waypoint> p, String directory, String path_name, double kWheelbaseWidth)
    {

        Path path = PathGenerator.makePath(p, config,
                kWheelbaseWidth, path_name);

        // Outputs to the directory supplied as the first argument.
        TextFileSerializer js = new TextFileSerializer();
        String serialized = js.serialize(path);
        // System.out.print(serialized);
        String fullpath = joinPath(directory, path_name + ".csv");
        if (!writeFile(fullpath, serialized))
        {
            System.err.println(fullpath + " could not be written!!!!");
            System.exit(1);
        }
        else
        {
            System.out.println("Wrote " + fullpath);
        }
    }

    private static void generate3TotesComplicated(String directory, double kWheelbaseWidth)
    {
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        final String path_name = "3TotesSWERVE";

        config.dt = .02;
        config.max_acc = 7.0 * 12;
        config.max_jerk = 40.0 * 12;
        config.max_vel = 2.2 * 12;

        List<Waypoint> p = new ArrayList<Waypoint>();
        p.add(new Waypoint(0, 0, 0));
        p.add(new Waypoint(20, 20, 0));
        p.add(new Waypoint(80, 0, 0));
        p.add(new Waypoint(100, 20, 0));
        p.add(new Waypoint(160, 0, 0));
        p.add(new Waypoint(200, 70, (Math.PI / 2) - .005));

        generate(config, p, directory, path_name, kWheelbaseWidth);
    }

    private static void generate3ToteStraight(String directory, double kWheelbaseWidth)
    {
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        final String path_name = "3ToteStraight";

        config.dt = .02;
        config.max_acc = 7.0 * 12;
        config.max_jerk = 40.0 * 12;
        config.max_vel = 1.6 * 12;

        double end_x = 20 * 12;

        List<Waypoint> p = new ArrayList<Waypoint>();
        p.add(new Waypoint(0, 0, 0));
        p.add(new Waypoint(160, 0, 0));
        p.add(new Waypoint(200, 70, (Math.PI / 2) - .005));

        generate(config, p, directory, path_name, kWheelbaseWidth);
    }

    private static void generateTestTrajectory(String directory, double kWheelbaseWidth)
    {
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        final String path_name = "TestTrajectory";

        config.dt = .02;
        config.max_acc = 9.0 * 12;
        config.max_jerk = 50.0 * 12;
        config.max_vel = 4.0 * 12;

        List<Waypoint> p = new ArrayList<Waypoint>();
        p.add(new Waypoint(0, 0, 0));
        p.add(new Waypoint(48, 24, 0));

        generate(config, p, directory, path_name, kWheelbaseWidth);
    }

    public static void main(String[] args)
    {
        String directory = "resources/paths";
        if (args.length >= 1)
        {
            directory = args[0];
        }

        final double kWheelbaseWidth = 25.5 / 12;

        generate3TotesComplicated(directory, kWheelbaseWidth);
        generate3ToteStraight(directory, kWheelbaseWidth);
        generateTestTrajectory(directory, kWheelbaseWidth);
    }
}
