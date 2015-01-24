
package com.snobot;

import javax.security.auth.login.Configuration;

import com.snobot.claw.SnobotClaw;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.SnobotFlightstickJoystick;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private Joystick mRawDriverJoystickPrimary;
	private Joystick mRawDriverJoystickSecondary;
	
	private SnobotOperatorJoystick mOperatorJoystick;
	private IDriverJoystick mDriverJoystick;
	
	private SendableChooser mTankModeButtonChooser;
	private SendableChooser mArcadeModeButton;
	
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
    	
    	mRawDriverJoystickPrimary   = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_1_PORT, 0));
    	
    	mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
    	
    	String joystickType = ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sJoystickMode, ConfigurationNames.sJoystickMode_Xbox);
    	
    	mTankModeButtonChooser = new SendableChooser();
    	mTankModeButtonChooser.addDefault("xboxButtonA", ConfigurationNames.sXbox_Button_A);
    	mTankModeButtonChooser.addObject("xboxButtonB", ConfigurationNames.sXbox_Button_B);
    	SmartDashboard.putData("Tank Mode Button Chooser", mTankModeButtonChooser);
    	
    	if(joystickType.equals(ConfigurationNames.sJoystickMode_Xbox))
    	{
        	mDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystickPrimary, mTankModeButtonChooser);
    	}
    	else 
    	{
    		mRawDriverJoystickPrimary = new Joystick (ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 0));
    		mDriverJoystick = new SnobotFlightstickJoystick(mRawDriverJoystickPrimary, mRawDriverJoystickSecondary);
    	}
    	
    	mStacker = new SnobotStacker(mOperatorJoystick);
    	mClaw = new SnobotClaw (mOperatorJoystick);
    	mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mDriverJoystick);
    	
    	
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
        mDriverJoystick.update();
        
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
    
    public void teleopInit() {
  
    }
    
}
