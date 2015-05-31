package com.snobot.claw;

import com.snobot.xlib.ISubsystem;

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
     * @return Air Pressure for the robot
     */
    double getRobotAirPressure();
}
