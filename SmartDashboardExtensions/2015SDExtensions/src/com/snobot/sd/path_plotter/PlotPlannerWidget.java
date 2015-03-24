package com.snobot.sd.path_plotter;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.sd.config.SmartDashboardNames;
import com.snobot.sd2015.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class PlotPlannerWidget extends AutoUpdateWidget
{
    public static final String NAME = "2015 PathPlanning";
    private PathPlotterPanel mPanel;
    private int mLastIndex;

    public PlotPlannerWidget()
    {
        super(false, 10);
        setLayout(new BorderLayout());
        mPanel = new PathPlotterPanel();
        add(mPanel);

        mLastIndex = 0;

        addPathListener();
    }

    private void addPathListener()
    {

        ITableListener listener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                List<Double> points = new ArrayList<Double>();
                StringTokenizer tokenizer = new StringTokenizer(arg2.toString(), ",");

                while (tokenizer.hasMoreElements())
                {
                    double d = Double.parseDouble(tokenizer.nextToken());
                    points.add(d);
                }

                mPanel.setPath(points);
                mLastIndex = 0;
                revalidate();
                repaint();
            }
        };
        Robot.getTable().addTableListener(SmartDashboardNames.sSIMPLE_IDEAL_PATH, listener, true);
    }

    @Override
    public void propertyChanged(Property arg0)
    {
    }

    @Override
    public void init()
    {
    }

    @Override
    protected void poll()
    {
        String point_info = Robot.getTable().getString(SmartDashboardNames.sSIMPLE_PATH_POINT_INFO, "");


        StringTokenizer tokenizer = new StringTokenizer(point_info, ",");

        if (tokenizer.hasMoreElements())
        {
            int index = Integer.parseInt(tokenizer.nextElement().toString());

            if (index == 0 || index < mLastIndex)
            {
                mPanel.clearActuals();
            }

            if (index > mLastIndex)
            {
                double velocity = Double.parseDouble(tokenizer.nextElement().toString());
                mPanel.setVelPoint(index, velocity);
            }

            mLastIndex = index;
        }
    }

}
