package com.team254.lib.trajectory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a Trajectory using arrays as the underlying storage mechanism.
 *
 * @author Jared341
 */
public class Trajectory
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

    List<Segment> segments_ = null;
    boolean inverted_y_ = false;

    public Trajectory(int length)
    {
        segments_ = new ArrayList<>(length);
        for (int i = 0; i < length; ++i)
        {
            segments_.add(new Segment());
        }
    }

    public Trajectory(List<Segment> segments)
    {
        segments_ = segments;
    }

    public void setInvertedY(boolean inverted)
    {
        inverted_y_ = inverted;
    }

    public int getNumSegments()
    {
        return segments_.size();
    }

    public Segment getSegment(int index)
    {
        if (index < getNumSegments())
        {
            if (!inverted_y_)
            {
                return segments_.get(index);
            }
            else
            {
                Segment segment = new Segment(segments_.get(index));
                segment.y *= -1.0;
                segment.heading *= -1.0;
                return segment;
            }
        }
        else
        {
            return new Segment();
        }
    }

    public void setSegment(int index, Segment segment)
    {
        if (index < getNumSegments())
        {
            segments_.set(index, segment);
        }
    }

    public void scale(double scaling_factor)
    {
        for (int i = 0; i < getNumSegments(); ++i)
        {
            segments_.get(i).pos *= scaling_factor;
            segments_.get(i).vel *= scaling_factor;
            segments_.get(i).acc *= scaling_factor;
            segments_.get(i).jerk *= scaling_factor;
        }
    }

    public void append(Trajectory to_append)
    {
        this.segments_.addAll(to_append.segments_);
    }

    public Trajectory copy()
    {
        Trajectory cloned = new Trajectory(getNumSegments());
        cloned.segments_ = copySegments(this.segments_);
        return cloned;
    }

    private List<Segment> copySegments(List<Segment> tocopy)
    {
        List<Segment> copied = new ArrayList<>(tocopy.size());
        for (Segment s : tocopy)
        {
            copied.add(new Segment(s));
        }
        return copied;
    }

    public String toString()
    {
        String str = "Segment\tPos\tVel\tAcc\tJerk\tHeading\n";
        for (int i = 0; i < getNumSegments(); ++i)
        {
            Segment segment = getSegment(i);
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

    public String toStringProfile()
    {
        return toString();
    }

    public String toStringEuclidean()
    {
        String str = "Segment\tx\ty\tHeading\n";
        for (int i = 0; i < getNumSegments(); ++i)
        {
            Segment segment = getSegment(i);
            str += i + "\t";
            str += segment.x + "\t";
            str += segment.y + "\t";
            str += segment.heading + "\t";
            str += "\n";
        }

        return str;
    }
}
