package com.snobot.sd.drivetrainCal;

import com.snobot.sd.SDKeys;
import com.snobot.sd.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;


public class DrivetrainCalWidget extends AutoUpdateWidget
{
	private static final long serialVersionUID = 7796244955503407432L;

	public static final String NAME = "2014 Snobot Drivetrain Cal Widget";
    
    private static final String WHEEL_0_BIAS_KEY = "Wheel Bias[0]";
    private static final String WHEEL_1_BIAS_KEY = "Wheel Bias[1]";
    private static final String WHEEL_2_BIAS_KEY = "Wheel Bias[2]";
    private static final String WHEEL_3_BIAS_KEY = "Wheel Bias[3]";
    
    private DrivetrainCalDrawer mDrawer = new DrivetrainCalDrawer();

    public DrivetrainCalWidget()
    {
        super(false);
        setLayout(new BorderLayout());
        add(mDrawer, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 235));
    }
    
    @Override
    public void init()
    {
        revalidate();
        repaint();
    }

    @Override
    public void propertyChanged(Property prprt)
    {
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        mDrawer.updateWheelBias(
                (int) Robot.getTable().getNumber(SDKeys.sWHEEL_TO_CAL_SD_KEY, Double.NaN),
                Robot.getTable().getBoolean(SDKeys.sIS_CALIBRATING_SD_KEY, false),
        		
        		
                Robot.getTable().getNumber(WHEEL_0_BIAS_KEY, Double.NaN),
                Robot.getTable().getNumber(WHEEL_1_BIAS_KEY, Double.NaN),
                Robot.getTable().getNumber(WHEEL_2_BIAS_KEY, Double.NaN),
                Robot.getTable().getNumber(WHEEL_3_BIAS_KEY, Double.NaN)
                );
    }
}
