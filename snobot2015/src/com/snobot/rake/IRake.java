package com.snobot.rake;

import com.snobot.xlib.ISubsystem;

/**
 * 
 * Interface for the rake subsystem
 * 
 * @author jbnol_000
 *
 */
public interface IRake extends ISubsystem {

    /**
     * Moves the rake down.
     */
    void moveRakeOut();

    /**
     * Moves rake up
     */
    void moveRakeIn();

}
