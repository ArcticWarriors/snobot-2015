package com.snobot.sd.robot;

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

public class RobotWidget2015 extends StaticWidget
{
    public static final String NAME = "2015 Snobot Robot Widget";
    private RobotDrawer2015 mRobotDrawer2015;
    
    public RobotWidget2015()
    {
        setLayout(new BorderLayout());
        mRobotDrawer2015 = new RobotDrawer2015();
        add(mRobotDrawer2015, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 400));
        
        addStackerHeightListener();
        addUpperLimitSwitchListener();
        addLowerLimitSwitchListener();
        addArmListener();
        addClawListener();
    }

    public void init()
    {        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                mRobotDrawer2015.updateSize();
            }
        });
    }
    
    private void addStackerHeightListener()
    {
        ITableListener stackerHeightListener = new ITableListener()
        {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                double height = Double.parseDouble(arg2.toString());
                mRobotDrawer2015.setStackerHeight(height);
                repaint();
            }
        };
        
        Robot.getTable().addTableListener(SmartDashboardNames.sENCODER_HEIGHT, stackerHeightListener, true);
        
    }
    
    private void addUpperLimitSwitchListener()
    {
        ITableListener upperLimitSwitchListener = new ITableListener()
        {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                boolean aUpperLimitSwitch = Boolean.parseBoolean(arg2.toString());
                mRobotDrawer2015.setUpperLimitSwtich(aUpperLimitSwitch);
                repaint();
            }
        };
        
        Robot.getTable().addTableListener(SmartDashboardNames.sUPPER_LIMIT_SWITCH_STATE, upperLimitSwitchListener, true);
    }
    
    private void addLowerLimitSwitchListener()
    {
        ITableListener lowerLimitSwitchListener = new ITableListener()
        {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                boolean aLowerLimitSwitch = Boolean.parseBoolean(arg2.toString());
                mRobotDrawer2015.setLowerLimitSwitch(aLowerLimitSwitch);
                repaint();
            }
        };
        
        Robot.getTable().addTableListener(SmartDashboardNames.sLOWER_LIMIT_SWITCH_STATE, lowerLimitSwitchListener, true);
    }
    
    private void addArmListener()
    {
        ITableListener clawArmStateListener = new ITableListener()
        
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) 
            {
               boolean aClawArmState = Boolean.parseBoolean(arg2.toString());
               mRobotDrawer2015.setClawUp(aClawArmState);
               repaint();
            }
        };
        Robot.getTable().addTableListener(SmartDashboardNames.sCLAW_ARM_SOLENOID, clawArmStateListener, true);
    }

    private void addClawListener()
    {
        ITableListener clawOpenListener = new ITableListener()

        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                boolean aClawState = Boolean.parseBoolean(arg2.toString());
                mRobotDrawer2015.setClawOpen(aClawState);
                repaint();
            }
        };
        Robot.getTable().addTableListener(SmartDashboardNames.sCLAW_HAND_SOLENOID, clawOpenListener, true);
    }

    @Override
    public void propertyChanged(Property prprt)
    {
    }
}
