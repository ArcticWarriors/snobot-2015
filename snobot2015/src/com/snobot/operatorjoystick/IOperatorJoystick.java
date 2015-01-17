package com.snobot.operatorjoystick;

public interface IOperatorJoystick {

	/**
	 * Asks if stacker should be up
	 */
	boolean getStackerUp();
	
	/**
	 * Asks if stacker should be down
	 */
	boolean getStackerDown();
	
	/**
	 * Asks if claw should be up
	 */
	boolean getClawUp();
	
	/**
	 * Asks if claw should be down
	 */
    boolean getClawDown();
    
    /**
     * Asks if claw should be open
     */
    boolean getClawOpen();
    
    /**
     * Asks if claw should be closed
     */
    boolean getClawClosed();
    
}
