package com.snobot.sd2015.spline_plotter;

import java.awt.BorderLayout;
import java.util.StringTokenizer;

import com.snobot.sd2015.config.SmartDashboardNames;
import com.snobot.sd2015.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class SplinePlannerWidget extends AutoUpdateWidget
{
    public static final String NAME = "2015 SplinePlanning";
    private SplinePlotterPanel mPanel;
    private int mLastIndex;

    public SplinePlannerWidget()
    {
        super(false, 10);
        setLayout(new BorderLayout());
        mPanel = new SplinePlotterPanel();
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
                mPanel.setPath(IdealSplineSerializer.deserializePath(arg2.toString()));
                mLastIndex = 0;
                revalidate();
                repaint();
            }
        };
        Robot.getTable().addTableListener(SmartDashboardNames.sSPINE_IDEAL, listener, true);
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
        String point_info = Robot.getTable().getString(SmartDashboardNames.sSPLINE_POINT, "");

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
                SplineSegment segment = IdealSplineSerializer.deserializePathPoint(tokenizer);
                mPanel.setPoint(index, segment);
            }

            mLastIndex = index;
        }
    }

}
