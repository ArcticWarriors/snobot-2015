package com.snobot.rake;

import com.snobot.ISubsystem;

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
    void moveRakeDown();

    /**
     * Moves rake up
     */
    void moveRakeUp();

}
