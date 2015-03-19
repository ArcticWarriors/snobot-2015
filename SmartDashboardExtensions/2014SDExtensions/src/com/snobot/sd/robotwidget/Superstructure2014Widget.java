package com.snobot.sd.robotwidget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.snobot.sd.SDKeys;
import com.snobot.sd.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;


public class Superstructure2014Widget extends AutoUpdateWidget
{
	private static final long serialVersionUID = 3489934466074251344L;

	public static final String NAME = "2014 Snobot Robot Widget";
    
    private RobotDrawer2014 mContainer = new RobotDrawer2014();
    private final RobotContainer2014 aContainer = new RobotContainer2014();
    
    public Superstructure2014Widget()
    {
        super(false);
        add(mContainer);
        add(mContainer, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 400));
    }
    
    @Override
    public void init()
    {
        revalidate();
        repaint();
        
               addComponentListener(new ComponentAdapter() {
    		@Override
    		public void componentResized(ComponentEvent arg0) {
    			aContainer.updateSize();
    			System.out.println("Widget size " + getSize());
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

        mContainer.setArmUp(Robot.getTable().getBoolean(SDKeys.sHARVESTOR_IN_SD_KEY, false));
        mContainer.setShooterUp(Robot.getTable().getBoolean(SDKeys.sSHOOTING_STATE_SD_KEY, false));
        mContainer.setRollerSpeed(Robot.getTable().getNumber(SDKeys.sHARVESTOR_ROLLER_SPEED_SD_KEY, 0));
        
        mContainer.repaint();
    }
}
