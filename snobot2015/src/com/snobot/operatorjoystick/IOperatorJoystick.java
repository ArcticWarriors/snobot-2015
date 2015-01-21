package com.snobot.operatorjoystick;

/**
 * Used by SnobotOperatorJoystick to monitor
 * State of operator joy stick
 * @author Alec/Jeffrey
 *
 */
public interface IOperatorJoystick {

	/**
	 * Asks if stacker is up
	 * @return True if stacker is up else false
	 */
	boolean getStackerUp();
	
	/**
	 * Asks if claw is up
	 * @return True if claw is up else false
	 */
	boolean getClawUp();
    
    /**
     * Asks if claw is open
     * @return True if claw is open else false
     */
    boolean getClawOpen();
    
}
