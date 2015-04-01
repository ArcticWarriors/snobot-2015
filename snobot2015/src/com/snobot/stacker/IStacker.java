package com.snobot.stacker;

import com.snobot.xlib.ISubsystem;

/**
 * Main interface for stacker subsystem
 * 
 * @author Alec/Jeffrey
 *
 */
public interface IStacker extends ISubsystem
{

    /**
     * Moves stacker up returns true if moving, false if stopped
     */
    boolean moveStackerUp();

    /**
     * Moves stacker down returns true if moving, false if stopped
     */
    boolean moveStackerDown();

    /**
     * Moves stacker to the ground, i.e. the lowest position possible
     * 
     * @return True when it is at the desired position
     */
    boolean moveStackerToGround();

    /**
     * Moves stacker to the scoring platform height. This is the height in which
     * we can comfortably drive onto the platform
     * 
     * @return True when it is at the desired position
     */
    boolean moveStackerToScoringPlatform();

    /**
     * Moves stacker to the height where it can be comfortably above a single
     * tote
     * 
     * @return True when it is at the desired position
     */
    boolean moveStackerToOneStack();

}
