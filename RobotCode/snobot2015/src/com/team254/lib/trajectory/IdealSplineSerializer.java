package com.team254.lib.trajectory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IdealSplineSerializer
{

    private IdealSplineSerializer()
    {

    }

    public static List<SplineSegment> deserializePath(String aString)
    {
        List<SplineSegment> points = new ArrayList<SplineSegment>();
        StringTokenizer tokenizer = new StringTokenizer(aString, ",");

        while (tokenizer.hasMoreElements())
        {
            double left_position = Double.parseDouble(tokenizer.nextToken());
            double left_velocity = Double.parseDouble(tokenizer.nextToken());
            double right_position = Double.parseDouble(tokenizer.nextToken());
            double right_velocity = Double.parseDouble(tokenizer.nextToken());
            double heading = Double.parseDouble(tokenizer.nextToken());

            SplineSegment segment = new SplineSegment();
            segment.left_pos = left_position;
            segment.left_vel = left_velocity;
            segment.right_pos = right_position;
            segment.right_vel = right_velocity;
            segment.heading = heading;

            points.add(segment);
        }

        return points;
    }

    public static String serializePath(List<SplineSegment> aPoints)
    {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < aPoints.size(); ++i)
        {
            output.append(aPoints.get(i).left_pos + ",");
            output.append(aPoints.get(i).left_vel + ",");
            output.append(aPoints.get(i).right_pos + ",");
            output.append(aPoints.get(i).right_vel + ",");
            output.append(aPoints.get(i).heading + ",");
        }

        return output.toString();
    }

    public static String serializePathPoint(SplineSegment aPoint)
    {
        return ""
                + aPoint.left_pos + ", "
                + aPoint.left_vel + ", "
                + aPoint.right_pos + ", "
                + aPoint.right_vel + ", "
                + aPoint.heading;
    }

    public static SplineSegment deserializePathPoint(StringTokenizer tokenizer)
    {
        SplineSegment point = new SplineSegment();

        try
        {
            point.left_pos = Double.parseDouble(tokenizer.nextToken());
            point.left_vel = Double.parseDouble(tokenizer.nextToken());
            point.right_pos = Double.parseDouble(tokenizer.nextToken());
            point.right_vel = Double.parseDouble(tokenizer.nextToken());
            point.heading = Double.parseDouble(tokenizer.nextToken());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return point;
    }
}
