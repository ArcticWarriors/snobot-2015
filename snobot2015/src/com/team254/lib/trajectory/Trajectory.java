package com.team254.lib.trajectory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a Trajectory using arrays as the underlying storage
 * mechanism.
 *
 * @author Jared341
 */
public class Trajectory extends ArrayList<Segment>
{
    public static class Pair
    {
        public Pair(Trajectory left, Trajectory right)
        {
            this.left = left;
            this.right = right;
        }

        public Trajectory left;
        public Trajectory right;
    }

    public Trajectory()
    {
        super();
    }

    public Trajectory(int length)
    {
        super(length);
        for (int i = 0; i < length; ++i)
        {
            add(new Segment());
        }
    }

    public Trajectory(List<Segment> segments)
    {
        super(segments);
    }

    public void scale(double scaling_factor)
    {
        for (int i = 0; i < size(); ++i)
        {
            get(i).pos *= scaling_factor;
            get(i).vel *= scaling_factor;
            get(i).acc *= scaling_factor;
            get(i).jerk *= scaling_factor;
        }
    }

    public Trajectory copy()
    {
        Trajectory cloned = new Trajectory();

        for (Segment s : this)
        {
            cloned.add(new Segment(s));
        }

        return cloned;
    }

    public String toStringProfile()
    {
        String str = "Segment\tPos\tVel\tAcc\tJerk\tHeading\n";
        for (int i = 0; i < size(); ++i)
        {
            Segment segment = get(i);
            str += i + "\t";
            str += segment.pos + "\t";
            str += segment.vel + "\t";
            str += segment.acc + "\t";
            str += segment.jerk + "\t";
            str += segment.heading + "\t";
            str += "\n";
        }

        return str;
    }

    public String toStringEuclidean()
    {
        String str = "Segment\tx\ty\tHeading\n";
        for (int i = 0; i < size(); ++i)
        {
            Segment segment = get(i);
            str += i + "\t";
            str += segment.x + "\t";
            str += segment.y + "\t";
            str += segment.heading + "\t";
            str += "\n";
        }

        return str;
    }

    @Override
    public String toString()
    {
        return toStringProfile();
    }
}
