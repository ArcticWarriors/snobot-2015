package com.snobot.sd.path_plotter;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class StandaloneMain
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final PathPlotterPanel panel = new PathPlotterPanel();

        final List<SimplePathPoint> path_points = new ArrayList<SimplePathPoint>();

        SimplePathPoint p;

        p = new SimplePathPoint();
        p.mVelocity = 0;
        p.mPosition = 0;
        path_points.add(p);

        for (int i = 1; i < 10; ++i)
        {
            p = new SimplePathPoint();
            p.mVelocity = 0 + i * .7;
            p.mPosition = path_points.get(path_points.size() - 1).mPosition + p.mVelocity * .02;
            path_points.add(p);
        }
        for (int i = 0; i < 20; ++i)
        {
            p = new SimplePathPoint();
            p.mVelocity = 7.0;
            p.mPosition = path_points.get(path_points.size() - 1).mPosition + p.mVelocity * .02;
            path_points.add(p);
        }
        for (int i = 0; i < 10; ++i)
        {
            p = new SimplePathPoint();
            p.mVelocity = 7 - i * .7;
            p.mPosition = path_points.get(path_points.size() - 1).mPosition + p.mVelocity * .02;
            path_points.add(p);
        }

        p = new SimplePathPoint();
        p.mVelocity = 0;
        p.mPosition = path_points.get(path_points.size() - 1).mPosition + p.mVelocity * .02;
        path_points.add(p);

        panel.setPath(path_points);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent arg0)
            {
            }
        });

        Thread t = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                List<SimplePathPoint> actuals = new ArrayList<SimplePathPoint>();

                for (int i = 0; i < path_points.size(); ++i)
                {
                    SimplePathPoint p = path_points.get(i);
                    p.mVelocity *= .9;
                    p.mPosition = 0;

                    if (i != 0)
                    {
                        p.mPosition = actuals.get(i - 1).mPosition + p.mVelocity * .02;
                    }

                    actuals.add(p);
                }

                for (int i = 0; i < actuals.size(); ++i)
                {
                    panel.setPoint(i, actuals.get(i));

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
