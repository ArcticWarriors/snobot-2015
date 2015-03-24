package com.snobot.xlib.path;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IdealPlotSerializer
{

    private IdealPlotSerializer()
    {
        
    }
    
    public static List<SimplePathPoint> deserializePath(String aString)
    {
        List<SimplePathPoint> points = new ArrayList<SimplePathPoint>();
        StringTokenizer tokenizer = new StringTokenizer(aString, ",");

        while (tokenizer.hasMoreElements())
        {
            double position = Double.parseDouble(tokenizer.nextToken());
            double velocity = Double.parseDouble(tokenizer.nextToken());

            SimplePathPoint point = new SimplePathPoint();
            point.mPosition = position;
            point.mVelocity = velocity;

            points.add(point);
        }

        return points;
    }

    public static String serializePath(List<SimplePathPoint> aPoints)
    {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < aPoints.size(); ++i)
        {
            output.append(aPoints.get(i).mPosition + ",");
            output.append(aPoints.get(i).mVelocity + ",");
        }

        return output.toString();
    }

    public static String serializePathPoint(SimplePathPoint aPoint)
    {
        return "" + aPoint.mPosition + "," + aPoint.mVelocity;
    }

    public static SimplePathPoint deserializePathPoint(StringTokenizer tokenizer)
    {
        SimplePathPoint point = new SimplePathPoint();

        try
        {
            point.mPosition = Double.parseDouble(tokenizer.nextToken());
            point.mVelocity = Double.parseDouble(tokenizer.nextToken());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return point;
    }
}
