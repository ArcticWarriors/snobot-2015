package com.snobot.claw;

import com.snobot.ISubsystem;

/**
 * 
 * Interface for the claw subsystem
 * 
 * @author Alec/Jeffrey
 *
 */
public interface IClaw extends ISubsystem
{

	
    /**
     * Opens claw
     */
    void openClaw();

    /**
     * Closes claw
     */
    void closeClaw();

    /**
     * Moves claw up
     */
    void moveClawUp();

    /**
     * Moves claw down
     */
    void moveClawDown();
    
    /**
     * 
     * @return Air pressure for claw up and claw down.
     */
    double getAirPressureA();
    
    /**
     * 
     * @return Air pressure for claw open and claw close.
     */
    double getAirPressureB();
}
