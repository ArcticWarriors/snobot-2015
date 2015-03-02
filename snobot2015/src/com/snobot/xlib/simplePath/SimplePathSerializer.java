package com.snobot.xlib.simplePath;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SimplePathSerializer
{

    public SimplePathSerializer()
    {
    }

    public void write(List<SimplePathPoint> points, String aFileSerialPath)
    {
        DecimalFormat df = new DecimalFormat("#.000");

        try
        {
            FileWriter mSerialFileWriter = new FileWriter(aFileSerialPath);

            mSerialFileWriter.write("Segment Number,Time,Position,Velocity,Acceleration\n");
            for (SimplePathPoint pathPoint : points)
            {

                mSerialFileWriter.write("" +
                        df.format(pathPoint.mSegment) + "," +
                        df.format(pathPoint.mTime) + "," +
                        df.format(pathPoint.mPosition) + "," +
                        df.format(pathPoint.mVelocity) + "," +
                        df.format(pathPoint.mAcceleration) + "\n");
            }
            mSerialFileWriter.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        SimplePathPoint p = new SimplePathPoint();

        ArrayList<SimplePathPoint> points = new ArrayList<>();
        points.add(p);
        points.add(p);
        points.add(p);
        points.add(p);
        points.add(p);

        new SimplePathSerializer().write(points, "test.csv");
    }
}
