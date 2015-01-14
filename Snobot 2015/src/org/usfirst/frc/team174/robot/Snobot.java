
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

	private SpeedController leftMotorsA;
	private SpeedController leftMotorsB;
	private SpeedController rightMotorsA;
	private SpeedController rightMotorsB;
	
	private Joystick leftJoystick;
	private Joystick rightJoystick;
	
	private DriverJoystick driverJoystick;
	
	private DriveTrain tank;
	
    public void robotInit() {
    	leftMotorsA = new Talon(0);
    	rightMotorsA = new Talon(1);
    	leftJoystick = new Joystick(1);
    	leftJoystick = new Joystick(1);
    	rightJoystick = new Joystick(2);
    	
    	driverJoystick = new DriverJoystick_Xbox(rightJoystick);
    	
    	tank = new DriveTrain (leftMotorsA, leftMotorsB, rightMotorsA, rightMotorsB, driverJoystick);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	tank.setLeftRight(1, 1);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    tank.control();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
