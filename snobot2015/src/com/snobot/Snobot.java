
package com.snobot;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.snobot.claw.SnobotClaw;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.drivetrain.SnobotDriveTrain.DriveMode;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.SnobotFlightstickJoystick;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.DigitalInput;
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
	private Joystick mRawDriverJoystick;
	private Joystick mRawDriverJoystickPrimary;
	private Joystick mRawDriverJoystickSecondary;
	private DigitalInput mUpperLimitSwitch;
	private DigitalInput mLowerLimitSwitch;

	private SnobotOperatorJoystick mOperatorJoystick;
	private SnobotXBoxDriverJoystick mXBoxDriverJoystick;
	
	private IDriverJoystick mDriverJoystick;
	
	private DriveMode mDriveMode;
	
	//TODO Calvin - This is for testing, remove later
	public String logHeader = "";
	
	private SendableChooser mTankModeButtonChooser;
	private SendableChooser mArcadeModeButton;
	
	//Modules
	private SnobotStacker mStacker;
	private SnobotClaw mClaw;
	private SnobotDriveTrain mDriveTrain;
	private Logger mLogger;
	
	//Motors
	private Talon mDriveLeft1;
	private Talon mDriveRight1;
	private Talon mStackerMotor;
	
	
	//Vector of iSubsystems for group actions
	private ArrayList<ISubsystem> mSubsystems;
	
	
	
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
    	mXBoxDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystick, mTankModeButtonChooser, mDriveMode);
    	mStacker = new SnobotStacker(mOperatorJoystick, mStackerMotor, mUpperLimitSwitch, mLowerLimitSwitch);
    	mClaw = new SnobotClaw (mOperatorJoystick);
    	mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mXBoxDriverJoystick, mDriveMode);
    	
    	String joystickType = ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sJoystickMode, ConfigurationNames.sJoystickMode_Xbox);
    	
    	mTankModeButtonChooser = new SendableChooser();
    	mTankModeButtonChooser.addDefault("xboxButtonA", ConfigurationNames.sXbox_Button_A);
    	mTankModeButtonChooser.addObject("xboxButtonB", ConfigurationNames.sXbox_Button_B);
    	SmartDashboard.putData("Tank Mode Button Chooser", mTankModeButtonChooser);
    	
    	if(joystickType.equals(ConfigurationNames.sJoystickMode_Xbox))
    	{
        	mDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystickPrimary, mTankModeButtonChooser, mDriveMode);
    	}
    	else 
    	{
    		mRawDriverJoystickPrimary = new Joystick (ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 0));
    		mDriverJoystick = new SnobotFlightstickJoystick(mRawDriverJoystickPrimary, mRawDriverJoystickSecondary);
    	}

    	mSubsystems = new ArrayList<ISubsystem>();
	    	mSubsystems.add(mOperatorJoystick);
	    	mSubsystems.add(mXBoxDriverJoystick);
	    	mSubsystems.add(mStacker);
	    	mSubsystems.add(mClaw);
	    	mSubsystems.add(mDriveTrain);
    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String headerDate = sdf.format(new Date());
	    	logHeader = logHeader + headerDate; 
	    	
    	for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.init();
		}
    	

    	
    	ConfigurationNames.saveIfUpdated();
    	
    	//TODO add "addHeaders" from separate modules/components
    	
    	mLogger = new Logger(logHeader,headerDate);

			mLogger.init();
		
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
        Date logDate = new Date();
        
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.update();
			
		}
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.control();
		}
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.updateSmartDashboard();
		}
        

        mLogger.startLogEntry(); 
        
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.updateLog();
			
		mLogger.endLogger();
			
		}
        
        
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public void teleopInit() {
  
    }
    
}
