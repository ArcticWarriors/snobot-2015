package com.snobot.sd.robot.positioner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.snobot.sd.config.SmartDashboardNames;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class PositionerWidget extends StaticWidget
{
    public static final String NAME = "2015 Positioner";
    private RobotWidget2015Positioner mPanel;

    public PositionerWidget()
    {
        setLayout(new BorderLayout());
        mPanel = new RobotWidget2015Positioner();
        add(mPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 400));

        addXListener();
        addYListener();
        addAngleListener();
    }

    public void init()
    {
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent arg0)
            {
                mPanel.updateSize();
            }
        });
    }

    private void addXListener()
    {
        ITableListener listener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
            }
        };

        Robot.getTable().addTableListener(SmartDashboardNames.sSNOBOT_X_POSITION, listener, true);

    }

    private void addYListener()
    {
        ITableListener listener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
            }
        };

        Robot.getTable().addTableListener(SmartDashboardNames.sSNOBOT_Y_POSITION, listener, true);

    }

    private void addAngleListener()
    {
        ITableListener listener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
            }
        };

        Robot.getTable().addTableListener(SmartDashboardNames.sSNOBOT_HEADING, listener, true);

    }

    @Override
    public void propertyChanged(Property prprt)
    {
    }
}
