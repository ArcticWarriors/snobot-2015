package com.team254.lib.trajectory.io;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Segment;
import com.team254.lib.trajectory.Trajectory;

/**
 * Serializes a Path to a simple space and CR separated text file.
 * 
 * @author Jared341
 */
public class TextFileSerializer implements IPathSerializer
{

    /**
     * Format: PathName NumSegments LeftSegment1 ... LeftSegmentN RightSegment1
     * ... RightSegmentN
     * 
     * Each segment is in the format: pos vel acc jerk heading dt x y
     * 
     * @param path The path to serialize.
     * @return A string representation.
     */
    public String serialize(Path path)
    {
        Trajectory left = path.getLeftWheelTrajectory();
        Trajectory right = path.getRightWheelTrajectory();;

        String content = "";
        content += "LeftPos,LeftVelocity,LeftAcceleration,LeftJerk,LeftHeading,LeftDt,LeftX,LeftY,";
        content += "RightPos,RightVelocity,RightAcceleration,RightJerk,RightHeading,RightDt,RightX,RightY,";
        content += "\n";
        for (int i = 0; i < left.size(); ++i)
        {
            Segment left_segment = left.get(i);
            Segment right_segment = right.get(i);

            content += String.format("%.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f\n",
                    left_segment.pos, left_segment.vel, left_segment.acc, left_segment.jerk, left_segment.heading, left_segment.dt, left_segment.x,
                    left_segment.y, right_segment.pos, right_segment.vel, right_segment.acc, right_segment.jerk, right_segment.heading,
                    right_segment.dt, right_segment.x, right_segment.y);
        }

        return content;
    }

}
