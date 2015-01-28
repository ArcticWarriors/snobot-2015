package com.snobot.operatorjoystick;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Monitors state of operator joystick for other
 * classes/objects to use
 * @author Alec/Jeffrey
 *
 */
public class SnobotOperatorJoystick implements IOperatorJoystick{
	
	private Joystick mOperatorJoystick;
	
	/**
	 * Constructs a SnobotOperatorJoystick object
	 * @param aOperatorJoystick Argument for operator Joystick
	 */
	public SnobotOperatorJoystick(Joystick aOperatorJoystick){
	    mOperatorJoystick = aOperatorJoystick;
	}

	@Override
	public boolean getStackerUp() {
		
		if (mOperatorJoystick.getRawAxis(1) >= .2){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean getStackerDown() {
		
		if (mOperatorJoystick.getRawAxis(1) <= - .2){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean getClawUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClawOpen() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Perform initialization.
	 */
	public void init() {
	}
	
	/**
	 * Gathering and storing current sensor information. 
	 * Ex. Motor Speed.
	 */
	public void update(){
		
	}
	
	/**
	 * Setting sensor and device states.
	 */
	public void control(){
		
	}
	
	/**
	 * Rereads and applies current preferences.
	 */
	public void rereadPreferences() {
		
	}
	
	/**
	 * Updates information that is sent to SmartDashboard
	 * Takes Enum argument
	 */
	public void updateSmartDashboard(){
		
	}
	
	/**
	 * Updates the logger.
	 */
	public void updateLog(){
		
	}
	
	/**
	 * Stops all sensors and motors
	 */
	public void stop() {
		
	}

	
}
