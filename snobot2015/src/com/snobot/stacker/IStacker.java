package com.snobot.stacker;

import com.snobot.ISubsystem;

public interface IStacker extends ISubsystem {
	
	/**
	 * Moves stacker up
	 */
	void up();
	
	/**
	 * Moves stacker down
	 */
	void down();

}
