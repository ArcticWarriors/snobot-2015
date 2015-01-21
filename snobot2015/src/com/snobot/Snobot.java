
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
	private SnobotOperatorJoystick mOperatorJoystick;
	private SnobotStacker mStacker;
	private SnobotXBoxDriverJoystick mXBoxDriverJoystick;
	private SnobotClaw mClaw;
	private SnobotDriveTrain mDriveTrain;
	
	private Talon mTalonLeft;
	private Talon mTalonRight;
	private Joystick mRawOperatorJoystick;
	private Joystick mRawDriverJoystick;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
    	mXBoxDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystick);
    	mStacker = new SnobotStacker(mOperatorJoystick);
    	mClaw = new SnobotClaw (mOperatorJoystick);
    	mDriveTrain=new SnobotDriveTrain(mTalonLeft, mTalonRight, mXBoxDriverJoystick);
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
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
