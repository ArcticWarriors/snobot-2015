package com.snobot.stacker;

import com.snobot.ISubsystem;

/**
 * Main interface for stacker subsystem
 * 
 * @author Alec/Jeffrey
 *
 */
public interface IStacker extends ISubsystem
{

    /**
     * Moves stacker up
     * returns true if moving, false if stopped 
     */
    boolean moveStackerUp();

    /**
     * Moves stacker down
     * returns true if moving, false if stopped
     */
    boolean moveStackerDown();

    // TODO comments
    boolean moveStackerToGround();

    boolean moveStackerToScoringPlatform();

    boolean moveStackerToOneStack();

}
