package com.snobot.claw;

import com.snobot.ISubsystem;

public interface IClaw extends ISubsystem{

	/**
	 * Opens claw
	 */
	void open();
	
	/**
	 * Closes claw
	 */
	void close();
	
	/**
	 * Moves claw up
	 */
	void up();
	
	/**
	 * Moves claw down
	 */
	void down();
    	
}
