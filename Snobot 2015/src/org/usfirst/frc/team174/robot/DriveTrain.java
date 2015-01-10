package org.usfirst.frc.team174.robot;

import org.usfirst.frc.team174.robot.DriverJoystick_Flightsticks.DriveMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

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
    	System.out.println("In control");
    	if (driverJoystick.getmode() == DriveMode.TwoStick)
    	{
    		drive.tankDrive(driverJoystick.getLeft(), driverJoystick.getRight(), true);
    		System.out.println("Tank: " + driverJoystick.getLeft() + ", " +  driverJoystick.getRight());
    	}
    	else if (driverJoystick.getmode() == DriveMode.OneStick)
    	{
    		drive.arcadeDrive(driverJoystick.getSpeed(), driverJoystick.getRotate(), true);
    		System.out.println("Arcade: " + driverJoystick.getSpeed() + ", " +  driverJoystick.getRotate());
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }

	public void setLeftRight(int i, int j) {
		drive.setLeftRightMotorOutputs(i, j);
	}
}
    	
