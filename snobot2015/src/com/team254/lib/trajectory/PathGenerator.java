package com.team254.lib.trajectory;

import java.util.List;

/**
 * Generate a smooth Trajectory from a Path.
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341
 */
public class PathGenerator
{
    /**
     * Generate a path for autonomous driving.
     * 
     * @param waypoints
     *            The waypoints to drive to (FOR THE "GO LEFT" CASE!!!!)
     * @param config
     *            Trajectory config.
     * @param wheelbase_width
     *            Wheelbase separation; units must be consistent with config and waypoints.
     * @param name
     *            The name of the new path. THIS MUST BE A VALID JAVA CLASS NAME
     * @return The path.
     */
    public static Path makePath(List<Waypoint> waypoints,
            TrajectoryGenerator.Config config, double wheelbase_width,
            String name)
    {
        return new Path(name,
                generateLeftAndRightFromSeq(waypoints, config, wheelbase_width));
    }

    static Trajectory.Pair generateLeftAndRightFromSeq(List<Waypoint> path,
            TrajectoryGenerator.Config config, double wheelbase_width)
    {
        return makeLeftAndRightTrajectories(generateFromPath(path, config),
                wheelbase_width);
    }

    static Trajectory generateFromPath(List<Waypoint> path,
            TrajectoryGenerator.Config config)
    {
        if (path.size() < 2)
        {
            return null;
        }

        // Compute the total length of the path by creating splines for each pair
        // of waypoints.
        Spline[] splines = new Spline[path.size() - 1];
        double[] spline_lengths = new double[splines.length];
        double total_distance = 0;
        for (int i = 0; i < splines.length; ++i)
        {
            splines[i] = new Spline();
            if (!Spline.reticulateSplines(path.get(i),
                    path.get(i + 1), splines[i], Spline.Type.QuinticHermite))
            {
                return null;
            }
            spline_lengths[i] = splines[i].calculateLength();
            total_distance += spline_lengths[i];
        }

        // Generate a smooth trajectory over the total distance.
        Trajectory traj = TrajectoryGenerator.generate(config,
                TrajectoryGenerator.Strategy.SCurvesStrategy, 0.0, path.get(0).theta,
                total_distance, 0.0, path.get(0).theta);

        // Assign headings based on the splines.
        int cur_spline = 0;
        double cur_spline_start_pos = 0;
        double length_of_splines_finished = 0;
        for (int i = 0; i < traj.getNumSegments(); ++i)
        {
            double cur_pos = traj.getSegment(i).pos;

            boolean found_spline = false;
            while (!found_spline)
            {
                double cur_pos_relative = cur_pos - cur_spline_start_pos;
                if (cur_pos_relative <= spline_lengths[cur_spline])
                {
                    double percentage = splines[cur_spline].getPercentageForDistance(
                            cur_pos_relative);
                    traj.getSegment(i).heading = splines[cur_spline].angleAt(percentage);
                    double[] coords = splines[cur_spline].getXandY(percentage);
                    traj.getSegment(i).x = coords[0];
                    traj.getSegment(i).y = coords[1];
                    found_spline = true;
                }
                else if (cur_spline < splines.length - 1)
                {
                    length_of_splines_finished += spline_lengths[cur_spline];
                    cur_spline_start_pos = length_of_splines_finished;
                    ++cur_spline;
                }
                else
                {
                    traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1.0);
                    double[] coords = splines[splines.length - 1].getXandY(1.0);
                    traj.getSegment(i).x = coords[0];
                    traj.getSegment(i).y = coords[1];
                    found_spline = true;
                }
            }
        }

        return traj;
    }

    /**
     * Generate left and right wheel trajectories from a reference.
     *
     * @param input
     *            The reference trajectory.
     * @param wheelbase_width
     *            The center-to-center distance between the left and right sides.
     * @return [0] is left, [1] is right
     */
    static Trajectory.Pair makeLeftAndRightTrajectories(Trajectory input,
            double wheelbase_width)
    {
        Trajectory[] output = new Trajectory[2];
        output[0] = input.copy();
        output[1] = input.copy();
        Trajectory left = output[0];
        Trajectory right = output[1];

        for (int i = 0; i < input.getNumSegments(); ++i)
        {
            Segment current = input.getSegment(i);
            double cos_angle = Math.cos(current.heading);
            double sin_angle = Math.sin(current.heading);

            Segment s_left = left.getSegment(i);
            s_left.x = current.x - wheelbase_width / 2 * sin_angle;
            s_left.y = current.y + wheelbase_width / 2 * cos_angle;

            Segment s_right = right.getSegment(i);
            s_right.x = current.x + wheelbase_width / 2 * sin_angle;
            s_right.y = current.y - wheelbase_width / 2 * cos_angle;

            Segment previous_right = null;
            Segment previous_left = null;

            if (i > 0)
            {
                previous_left = left.getSegment(i - 1);
                previous_right = right.getSegment(i - 1);
            }

            updateWithPrevious(previous_right, s_right);
            updateWithPrevious(previous_left, s_left);
        }

        return new Trajectory.Pair(output[0], output[1]);
    }

    private static void updateWithPrevious(Segment previous, Segment s_right)
    {
        if (previous != null)
        {
            double dist = Math.sqrt((s_right.x - previous.x)
                    * (s_right.x - previous.x)
                    + (s_right.y - previous.y)
                    * (s_right.y - previous.y));
            s_right.pos = previous.pos + dist;
            s_right.vel = dist / s_right.dt;
            s_right.acc = (s_right.vel - previous.vel) / s_right.dt;
            s_right.jerk = (s_right.acc - previous.acc) / s_right.dt;
        }
    }
}
