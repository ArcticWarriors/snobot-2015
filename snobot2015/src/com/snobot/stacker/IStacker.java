package com.snobot.stacker;

import com.snobot.ISubsystem;

/**
 * Main interface for stacker subsystem
 * 
 * @author Alec/Jeffrey
 *
 */
public interface IStacker extends ISubsystem {

    /**
     * Moves stacker up
     */
    void moveStackerUp();

    /**
     * Moves stacker down
     */
    void moveStackerDown();

}
