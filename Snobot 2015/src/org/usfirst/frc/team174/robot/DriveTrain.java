package org.usfirst.frc.team174.robot;

import org.usfirst.frc.team174.robot.DriverJoystick_Flightsticks.DriveMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain { /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	private SpeedController leftMotors;
	private SpeedController rightMotors;
	
	private DriverJoystick driverJoystick;

	RobotDrive drive;

	public DriveTrain(SpeedController aLeftSpeedController, SpeedController aRightSpeedController,
			DriverJoystick aDriverJoystick) 
	{
		leftMotors = aLeftSpeedController;
		rightMotors = aRightSpeedController;
		drive = new RobotDrive(leftMotors, rightMotors);
		
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

    	SmartDashboard.putNumber("Left Speed", leftMotors.get());
    	SmartDashboard.putNumber("Right Speed", rightMotors.get());
    	SmartDashboard.putString("Right Speed", driverJoystick.getmode().toString());
    }

	public void setLeftRight(int i, int j) {
		drive.setLeftRightMotorOutputs(i, j);
	}
}
    	
