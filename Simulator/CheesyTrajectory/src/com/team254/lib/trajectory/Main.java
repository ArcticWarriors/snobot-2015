package com.team254.lib.trajectory;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.snobot.trajectory.SimplePathGenerator;
import com.snobot.trajectory.config.GeneratorConfig;
import com.snobot.trajectory.config.SimpleConfig;
import com.snobot.trajectory.config.SplinePathConfig;
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
        return file2.getAbsolutePath();
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

    public static void generatePath(String outDir, double dt, double wheelbase, SplinePathConfig aPath)
    {
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.dt = dt;
        config.max_acc = aPath.getMax_accel();
        config.max_jerk = aPath.getMax_jerk();
        config.max_vel = aPath.getMax_vel();

        WaypointSequence p = new WaypointSequence(10);

        for (SplinePathConfig.WaypointConfig waypoint : aPath.getWaypoints())
        {
            p.addWaypoint(new WaypointSequence.Waypoint(waypoint.getX(), waypoint.getY(), waypoint.getAngle()));
        }

        Path path = PathGenerator.makePath(p, config, wheelbase, aPath.getName());

        TextFileSerializer js = new TextFileSerializer();
        String serialized = js.serialize(path);
        String fullpath = joinPath(outDir, aPath.getName() + ".txt");

        if (!writeFile(fullpath, serialized))
        {
            System.err.println(fullpath + " could not be written!!");
            System.exit(1);
        }
        else
        {
            System.out.println("Wrote " + fullpath);
        }
    }

    private static void generateTurn(String output_dir, double dt, SimpleConfig pathConfig)
    {
        SimplePathGenerator generator = new SimplePathGenerator();

        String serialized = generator.serialize(pathConfig, dt);

        String fullpath = joinPath(output_dir, pathConfig.getName() + ".txt");

        if (!writeFile(fullpath, serialized))
        {
            System.err.println(fullpath + " could not be written!!");
            System.exit(1);
        }
        else
        {
            System.out.println("Wrote " + fullpath);
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {

        Constructor constructor = new Constructor(GeneratorConfig.class);
        Yaml yaml = new Yaml(constructor);

        GeneratorConfig config = (GeneratorConfig) yaml.load(new FileInputStream("waypoint_generator.yml"));

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Generating spline paths");
        System.out.println("--------------------------------------------------------------------------------");
        for (SplinePathConfig pathConfig : config.getPaths())
        {
            generatePath(config.getOutput_dir(), config.getDt(), config.getWheelbase(), pathConfig);
        }
        System.out.println("\n\n");

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Generating simple paths");
        System.out.println("--------------------------------------------------------------------------------");
        for (SimpleConfig pathConfig : config.getSimple_paths())
        {
            generateTurn(config.getOutput_dir(), config.getDt(), pathConfig);
        }
        System.out.println("\n\n");

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Generating simple turning paths");
        System.out.println("--------------------------------------------------------------------------------");
        for (SimpleConfig pathConfig : config.getTurns())
        {
            generateTurn(config.getOutput_dir(), config.getDt(), pathConfig);
        }
        System.out.println("\n\n");
    }
}
