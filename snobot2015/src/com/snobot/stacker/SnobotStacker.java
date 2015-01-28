package com.snobot.stacker;

import com.snobot.ConfigurationNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * Main class for Snobot Stacker subsystem
 * @author Alec/Jeffrey
 *
 */
public class SnobotStacker implements IStacker{

	private SpeedController mStackerMotor;
	private IOperatorJoystick mOperatorJoystick;
	private double mStackerDefaultSpeed;
	private boolean mUpperLimitSwitchState;
	private boolean mLowerLimitSwitchState;
	private Logger mLogger;
	DigitalInput mUpperLimitSwitch;
	DigitalInput mLowerLimitSwitch;
	
	/**
	 * Constructs a SnobotStacker object
	 * @param aOperatorJoystick Argument of operator joy stick
	 */
	public SnobotStacker(IOperatorJoystick aOperatorJoystick, SpeedController aStackerMotor,
			DigitalInput aUpperLimitSwitch, DigitalInput aLowerLimitSwitch, Logger aLogger ){
	    mOperatorJoystick= aOperatorJoystick;
	    mStackerMotor = aStackerMotor; 
	    mStackerDefaultSpeed = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_DEFAULT_SPEED, .5);
	    mUpperLimitSwitch = aUpperLimitSwitch;
	    mLowerLimitSwitch = aLowerLimitSwitch;
	    mLogger = aLogger;
	}

	@Override
	public void moveStackerUp() {
	
		if (mUpperLimitSwitchState == true){
			stop();
		}
		else {
			mStackerMotor.set(mStackerDefaultSpeed);
		}
		/**
		 * Assuming Physical Limit Switch will stop stacker at limit
		 */
		
	}

	@Override
	public void moveStackerDown() {
	
		if (mLowerLimitSwitchState == true){
			stop ();
		}
		else {
			mStackerMotor.set(-mStackerDefaultSpeed);
		}
		/**
		 * Assuming Physical Limit Switch will stop stacker at limit
		 */
	}

	@Override
	public void init() {
		stop ();
	}

	@Override
	public void update() {
		mUpperLimitSwitchState = mUpperLimitSwitch.get();
		mLowerLimitSwitchState = mLowerLimitSwitch.get();
		
	}

	@Override
	public void control() {
		 if (mOperatorJoystick.getStackerUp()){
			 moveStackerUp();
		 }
		 else if (mOperatorJoystick.getStackerDown()){
			 moveStackerDown();
		 }
		 else {
			 stop();
		 }
		
	}

	@Override
	public void rereadPreferences() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSmartDashboard() {
	
	}

	@Override
	public void updateLog() {

		mLogger.updateLogger(mUpperLimitSwitchState);
		mLogger.updateLogger(mLowerLimitSwitchState);
		
	}

	@Override
	public void stop() {
		mStackerMotor.set(0);
		
	}

}
