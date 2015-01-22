
package com.snobot;

import com.snobot.claw.SnobotClaw;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot extends IterativeRobot {
	
	//IO
	private Joystick mRawOperatorJoystick;
	private Joystick mRawDriverJoystick;
	
	private SnobotOperatorJoystick mOperatorJoystick;
	private SnobotXBoxDriverJoystick mXBoxDriverJoystick;
	
	//Modules
	private SnobotStacker mStacker;
	private SnobotClaw mClaw;
	private SnobotDriveTrain mDriveTrain;
	
	//Motors
	private Talon mDriveLeft1;
	private Talon mDriveRight1;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	mDriveLeft1  = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_LEFT_1, 0));
    	mDriveRight1 = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_RIGHT_1, 1));
    	mRawOperatorJoystick = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sOPERATOR_JOYSTICK_PORT, 1));
    	mRawDriverJoystick   = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 0));
    	
    	mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
    	mXBoxDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystick);
    	mStacker = new SnobotStacker(mOperatorJoystick);
    	mClaw = new SnobotClaw (mOperatorJoystick);
    	mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mXBoxDriverJoystick);
    	
    	ConfigurationNames.saveIfUpdated();
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
        mStacker.update();
        mClaw.update();
        mDriveTrain.update();
        
        mStacker.control();
        mClaw.control();
        mDriveTrain.control();

        mStacker.updateSmartDashboard();
        mClaw.updateSmartDashboard();
        mDriveTrain.updateSmartDashboard();

        mStacker.updateLog();
        mClaw.updateLog();
        mDriveTrain.updateLog();
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
