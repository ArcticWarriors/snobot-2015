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

    private ITableListener mPositionListener = new ITableListener()
    {

        @Override
        public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
        {
            boolean aClawArmState = Boolean.parseBoolean(arg2.toString());
            mPanel.setRobotPosition(Robot.getTable().getNumber(SmartDashboardNames.sSNOBOT_X_POSITION, 0),
                    Robot.getTable().getNumber(SmartDashboardNames.sSNOBOT_Y_POSITION, 0),
                    Robot.getTable().getNumber(SmartDashboardNames.sSNOBOT_HEADING, 0));
        }
    };

    public PositionerWidget()
    {
        setLayout(new BorderLayout());
        mPanel = new RobotWidget2015Positioner();
        add(mPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 400));

        // Only listen to one, you won't ever be off by more than 20ms, good
        // enough
        Robot.getTable().addTableListener(SmartDashboardNames.sSNOBOT_HEADING, mPositionListener, true);
    }

    public void init()
    {
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent arg0)
            {
                mPanel.updateScale();
            }
        });
    }

    @Override
    public void propertyChanged(Property prprt)
    {
    }
}
