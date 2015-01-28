
package com.snobot;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.snobot.claw.SnobotClaw;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.IDriverJoystick.DriveMode;
import com.snobot.joystick.SnobotFlightstickJoystick;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.stacker.SnobotStacker;
import com.snobot.SmartDashboardNames;

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
	private IDriverJoystick mDriverJoystick;
	
	private DriveMode mDriveMode;
	
	private SendableChooser mTankModeButtonChooser;
	private SendableChooser mArcadeModeButtonChooser;
	private int mTankModeButton;
	private int mArcadeModeButton;
	
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
	
	SimpleDateFormat sdf;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	

    	sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String headerDate = sdf.format(new Date());
		mLogger = new Logger(headerDate);
	
		mLogger.init();
	
	
    	mDriveLeft1  = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_LEFT_1, 0));
    	mDriveRight1 = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_RIGHT_1, 1));
    	mRawOperatorJoystick = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sOPERATOR_JOYSTICK_PORT, 1));
    	mRawDriverJoystick   = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 0));

    	mUpperLimitSwitch = new DigitalInput (ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_UPPER_LIMIT_SWITCH_PORT_1, 1));
    	mLowerLimitSwitch = new DigitalInput (ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_LOWER_LIMIT_SWITCH_PORT_1, 2));
    	
    	
    	mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
    	mStackerMotor = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_MOTOR, 2));
    	mStacker = new SnobotStacker(mOperatorJoystick, mStackerMotor, mUpperLimitSwitch, mLowerLimitSwitch, mLogger);
    	mClaw = new SnobotClaw (mOperatorJoystick, mLogger);
    	
    	// Various Button Chooser for mode changes
    	mTankModeButtonChooser = new SendableChooser();
    	mTankModeButtonChooser.addDefault("xboxButtonA", ConfigurationNames.sXbox_Button_A);
    	mTankModeButtonChooser.addObject("xboxButtonB", ConfigurationNames.sXbox_Button_B);
    	mTankModeButtonChooser.addObject("xboxButtonX", ConfigurationNames.sXbox_Button_X);
    	mTankModeButtonChooser.addObject("xboxButtonY", ConfigurationNames.sXbox_Button_Y);
    	mTankModeButtonChooser.addObject("xboxButtonLeftBumper", ConfigurationNames.sXbox_Button_Left_Bumper);
    	mTankModeButtonChooser.addObject("xboxButtonRightBumper", ConfigurationNames.sXbox_Button_Right_Bumper);
    	SmartDashboard.putData("Tank Mode Button Chooser", mTankModeButtonChooser);
    	
    	mArcadeModeButtonChooser = new SendableChooser();
    	mArcadeModeButtonChooser.addDefault("xboxButtonB", ConfigurationNames.sXbox_Button_B);
    	mArcadeModeButtonChooser.addObject("xboxButtonA", ConfigurationNames.sXbox_Button_A);
    	mArcadeModeButtonChooser.addObject("xboxButtonX", ConfigurationNames.sXbox_Button_X);
    	mArcadeModeButtonChooser.addObject("xboxButtonY", ConfigurationNames.sXbox_Button_Y);
    	mArcadeModeButtonChooser.addObject("xboxButtonLeftBumper", ConfigurationNames.sXbox_Button_Left_Bumper);
    	mArcadeModeButtonChooser.addObject("xboxButtonRightBumper", ConfigurationNames.sXbox_Button_Right_Bumper);
    	SmartDashboard.putData("Arcade Mode Button Choose", mArcadeModeButtonChooser);
    	
    	String joystickType = ConfigurationNames.getOrSetPropertyString(SmartDashboardNames.sJoystickMode, SmartDashboardNames.sJoystickMode_Xbox);
    	
    	if(joystickType.equals(SmartDashboardNames.sJoystickMode_Xbox))
    	{
        	mDriverJoystick = new SnobotXBoxDriverJoystick(mTankModeButton, mArcadeModeButton, mRawDriverJoystickPrimary, mLogger, mTankModeButtonChooser, mArcadeModeButtonChooser, mDriveMode);
    	}
    	else 
    	{
    		mRawDriverJoystickPrimary = new Joystick (ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 0));
    		mDriverJoystick = new SnobotFlightstickJoystick(mRawDriverJoystickPrimary, mRawDriverJoystickSecondary);
    	}

    	mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mDriverJoystick, mDriveMode);

    	mSubsystems = new ArrayList<ISubsystem>();
	    	mSubsystems.add(mOperatorJoystick);
	    	mSubsystems.add(mDriverJoystick);
	    	mSubsystems.add(mStacker);
	    	mSubsystems.add(mClaw);
	    	mSubsystems.add(mDriveTrain);
    	
		
    	for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.init();
		}
    	
    	mLogger.endHeader();
    	
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

        String logDate = sdf.format(new Date());
        
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.update();
			
		}
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.control();
		}
        for (ISubsystem iSubsystem : mSubsystems) {
			iSubsystem.updateSmartDashboard();
		}
        
        mLogger.startLogEntry(logDate); 
        
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
