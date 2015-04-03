package com.snobot.sd2015.path_plotter;

import java.awt.BorderLayout;
import java.util.StringTokenizer;

import com.snobot.sd2015.config.SmartDashboardNames;
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
                mPanel.setPath(IdealPlotSerializer.deserializePath(arg2.toString()));
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
    protected void poll() throws Exception
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
                mPanel.setPoint(index, IdealPlotSerializer.deserializePathPoint(tokenizer));
            }

            mLastIndex = index;
        }
    }

}
