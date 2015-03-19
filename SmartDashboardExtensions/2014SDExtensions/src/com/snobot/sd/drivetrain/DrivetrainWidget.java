package com.snobot.sd.drivetrain;

import com.snobot.sd.SDKeys;
import com.snobot.sd.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class DrivetrainWidget extends AutoUpdateWidget
{
	private static final long serialVersionUID = 7796244955503407432L;

	public static final String NAME = "2014 Snobot Drivetrain Widget";
    
    private static final String WHEEL_0_ANGLE_KEY = "Wheel Angle[0]";
    private static final String WHEEL_1_ANGLE_KEY = "Wheel Angle[1]";
    private static final String WHEEL_2_ANGLE_KEY = "Wheel Angle[2]";
    private static final String WHEEL_3_ANGLE_KEY = "Wheel Angle[3]";
    
    private static final String WHEEL_0_SPEED_KEY = "Wheel Speed[0]";
    private static final String WHEEL_1_SPEED_KEY = "Wheel Speed[1]";
    private static final String WHEEL_2_SPEED_KEY = "Wheel Speed[2]";
    private static final String WHEEL_3_SPEED_KEY = "Wheel Speed[3]";
    
    private static final String X_SPEED_KEY = "Drivetrain X Speed";
    private static final String Y_SPEED_KEY = "Drivetrain Y Speed";
    
    private DrivetrainContainer mContainer = new DrivetrainContainer();

    public DrivetrainWidget()
    {
        super(false);
        setLayout(new BorderLayout());
        add(mContainer, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 235));
    }
    
    @Override
    public void init()
    {
        revalidate();
        repaint();

        addComponentListener(new ComponentAdapter() {
    		@Override
    		public void componentResized(ComponentEvent arg0) {
    			mContainer.updateSize();
    		}
    	});
    }

    @Override
    public void propertyChanged(Property prprt)
    {
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        mContainer.updateWheelAngles(
                Robot.getTable().getNumber(WHEEL_0_ANGLE_KEY, 0),
                Robot.getTable().getNumber(WHEEL_1_ANGLE_KEY, 0),
                Robot.getTable().getNumber(WHEEL_2_ANGLE_KEY, 0),
                Robot.getTable().getNumber(WHEEL_3_ANGLE_KEY, 0)
                );
        
        mContainer.updateSteeringSpeed(
                Robot.getTable().getNumber(WHEEL_0_SPEED_KEY, 0),
                Robot.getTable().getNumber(WHEEL_1_SPEED_KEY, 0),
                Robot.getTable().getNumber(WHEEL_2_SPEED_KEY, 0),
                Robot.getTable().getNumber(WHEEL_3_SPEED_KEY, 0)
                );
        
        mContainer.updateRobotSpeed(
                Robot.getTable().getNumber(X_SPEED_KEY, 0),
                Robot.getTable().getNumber(Y_SPEED_KEY, 0)
                );
        
        mContainer.updateRobotAngle(
                Robot.getTable().getNumber(SDKeys.sANGLE_KEY, 0) );
        
        mContainer.repaint();
    }
}
