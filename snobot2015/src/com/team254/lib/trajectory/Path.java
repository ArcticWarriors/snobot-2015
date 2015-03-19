package com.team254.lib.trajectory;


/**
 * Base class for an autonomous path.
 * 
 * @author Jared341
 */
public class Path
{
    protected Trajectory.Pair go_left_pair_;
    protected String name_;
    protected boolean go_left_;

    public Path(String name, Trajectory.Pair go_left_pair)
    {
        name_ = name;
        go_left_pair_ = go_left_pair;
        go_left_ = true;
    }

    public Path()
    {

    }

    public String getName()
    {
        return name_;
    }

    public void goLeft()
    {
        go_left_ = true;
    }

    public void goRight()
    {
        go_left_ = false;
    }

    public Trajectory getLeftWheelTrajectory()
    {
        return (go_left_ ? go_left_pair_.left : go_left_pair_.right);
    }

    public Trajectory getRightWheelTrajectory()
    {
        return (go_left_ ? go_left_pair_.right : go_left_pair_.left);
    }

    public Trajectory.Pair getPair()
    {
        return go_left_pair_;
    }

    public double getEndHeading()
    {
        int numSegments = getLeftWheelTrajectory().size();
        Segment lastSegment = getLeftWheelTrajectory().get(numSegments - 1);
        return lastSegment.heading;
    }
}
