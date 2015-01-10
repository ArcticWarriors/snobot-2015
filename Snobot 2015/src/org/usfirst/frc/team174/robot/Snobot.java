
package org.usfirst.frc.team174.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	private SpeedController leftMotors = new Talon(0);
	private SpeedController rightMotors = new Talon(1);
	
	Joystick rightJoystick = new Joystick(1);
	Joystick leftJoystick = new Joystick(2);
	
	RobotDrive drive = new RobotDrive(leftMotors, rightMotors);
	
    public void robotInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
 
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	if (rightJoystick.getRawButton(1) == true)
    	{
        drive.tankDrive(rightJoystick, leftJoystick, true);
    	}
    	else if (rightJoystick.getRawButton(2) == true)
    	{
    		drive.arcadeDrive(rightJoystick, true);
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
