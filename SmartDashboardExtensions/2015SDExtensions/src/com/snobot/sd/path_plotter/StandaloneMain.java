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

        PathPlotterPanel panel = new PathPlotterPanel();

        List<Double> path_points = new ArrayList<Double>();

        for (int i = 0; i < 10; ++i)
        {
            path_points.add(0 + i * .7);
        }
        for (int i = 0; i < 20; ++i)
        {
            path_points.add(7.0);
        }
        for (int i = 0; i < 10; ++i)
        {
            path_points.add(7 - i * .7);
        }
        path_points.add(0.0);

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
                for (int i = 0; i < path_points.size(); ++i)
                {
                    panel.setVelPoint(i, 5);;

                    try
                    {
                        Thread.sleep(1000);
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
