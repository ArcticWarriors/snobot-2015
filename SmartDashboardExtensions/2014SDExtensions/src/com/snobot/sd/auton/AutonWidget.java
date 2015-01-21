package com.snobot.sd.auton;

import com.snobot.sd.SDKeys;
import com.snobot.sd.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;


public class AutonWidget extends AutoUpdateWidget
{
	private static final long serialVersionUID = 7796244955503407432L;

	public static final String NAME = "2014 Auton Widget";
    
    private AutonDrawer mDrawer = new AutonDrawer();

    public AutonWidget()
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
        
        mDrawer.setAutonInfo(
                Robot.getTable().getBoolean(SDKeys.sFILE_SUCCESS_KEY, false),
          (int) Robot.getTable().getNumber(SDKeys.sAUTON_MODE_SD_KEY, -1),
                Robot.getTable().getString(SDKeys.sAUTON_MODE_NAME_KEY, "Not Updated"),
                Robot.getTable().getString(SDKeys.sCURRENT_STEP_KEY, "Not Updated"),
                Robot.getTable().getString(SDKeys.sAUTON_COMMANDS_KEY, "Not Updated")
                );
    }
}
