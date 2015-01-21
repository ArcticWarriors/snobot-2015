package com.snobot.sd.drivetrain;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class DrivetrainContainer extends JPanel
{

    private static final long serialVersionUID = 1170352727788455761L;
    protected DrivetrainDrawer mDrawer;
    protected TextboxPanel boxDrawer;

    public DrivetrainContainer()
    {
        setLayout(new BorderLayout());
    	setBorder(new LineBorder(Color.black));
    	boxDrawer = new TextboxPanel();

        mDrawer = new DrivetrainDrawer();
        add(mDrawer);
        add(boxDrawer, BorderLayout.EAST);

        showIndicators(true);
        

    }
    
    public void showIndicators(boolean aShow)
    {
        boxDrawer.setVisible(aShow);
    }

    public void updateWheelAngles(double aPortFrontAngle, double aPortBackAngle, double aStarFrontAngle, double aStarBackAngle)
    {
        mDrawer.updateWheelAngle(aPortFrontAngle, aPortBackAngle, aStarFrontAngle, aStarBackAngle);
        boxDrawer.updateWheelAngles(aPortFrontAngle, aPortBackAngle, aStarFrontAngle, aStarBackAngle);
    }

    public void updateRobotSpeed(double aXSpeed, double aYSpeed)
    {
    	boxDrawer.updateRobotSpeed(aXSpeed, aYSpeed);
    }

    public void updateSteeringSpeed(double aPortFrontSpeed, double aPortBckSpeed, double aStarFrontSpeed, double aStarBckSpeed)
    {
        mDrawer.updateWheelSpeed(aPortFrontSpeed, aPortBckSpeed, aStarFrontSpeed, aStarBckSpeed);
        boxDrawer.updateWheelSpeed(aPortFrontSpeed, aPortBckSpeed, aStarFrontSpeed, aStarBckSpeed);
    }

    public void updateDrivetrainSpeed(double aPortFrontSpeed, double aPortBckSpeed, double aStarFrontSpeed, double aStarBckSpeed)
    {
        mDrawer.updateDriveSpeed(aPortFrontSpeed, aPortBckSpeed, aStarFrontSpeed, aStarBckSpeed);
        boxDrawer.updateDrivetrainSpeed(aPortFrontSpeed, aPortBckSpeed, aStarFrontSpeed, aStarBckSpeed);
    }

    public void updateRobotAngle(double aRobotAngle)
    {
        if (boxDrawer.isUseGyroSelected())
        {
            mDrawer.updateRobotAngle(0);
        }
        else
        {
            mDrawer.updateRobotAngle(aRobotAngle);
        }
        
    	boxDrawer.updateRobotAngle(aRobotAngle);
    }


	public void updateDriveMode(String aDriveMode) {
		boxDrawer.setDriveMode(aDriveMode);
	}
	
	public void updateSize() 
	{
		mDrawer.updateSize();
	}
}
