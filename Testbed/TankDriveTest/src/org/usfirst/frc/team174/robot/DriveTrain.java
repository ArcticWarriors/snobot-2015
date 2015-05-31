package org.usfirst.frc.team174.robot;

import org.usfirst.frc.team174.robot.DriverJoystick_Flightsticks.DriveMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain { /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

	private SpeedController leftMotorsA;
	private SpeedController leftMotorsB;
	private SpeedController rightMotorsA;
	private SpeedController rightMotorsB;
	
	private DriverJoystick driverJoystick;

	RobotDrive drive;

	public DriveTrain(SpeedController aLeftSpeedController, SpeedController aLeftB,
			SpeedController aRightMotorsA, SpeedController aRightB, DriverJoystick aDriverJoystick) 
	{
		leftMotorsA = aLeftSpeedController;
		leftMotorsB = aLeftB;
		rightMotorsA = aRightMotorsA;
		rightMotorsB = aRightB;
		
		if(leftMotorsB == null || rightMotorsB == null)
		{
			drive = new RobotDrive(leftMotorsA, rightMotorsA);
		}
		else
		{
			drive = new RobotDrive(leftMotorsA, leftMotorsB, rightMotorsA, rightMotorsB);
		}
		
		driverJoystick = aDriverJoystick;
	}
	
    public void control() 
    {	
    	if (driverJoystick.getmode() == DriveMode.TwoStick)
    	{
    		drive.tankDrive(driverJoystick.getLeft(), driverJoystick.getRight(), true);
    	}
    	else if (driverJoystick.getmode() == DriveMode.OneStick)
    	{
    		drive.arcadeDrive(driverJoystick.getSpeed(), driverJoystick.getRotate(), true);
    	}

    	SmartDashboard.putNumber("Left Speed", leftMotorsA.get());
    	SmartDashboard.putNumber("Right Speed", rightMotorsA.get());
    	SmartDashboard.putString("Driver Mode", driverJoystick.getmode().toString());
    }

	public void setLeftRight(int i, int j) {
		drive.setLeftRightMotorOutputs(i, j);
	}
}
    	
