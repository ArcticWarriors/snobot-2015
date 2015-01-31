package com.snobot.operatorjoystick;

import com.snobot.ISubsystem;

/**
 * Used by SnobotOperatorJoystick to monitor State of operator joy stick
 * 
 * @author Alec/Jeffrey
 *
 */
public interface IOperatorJoystick extends ISubsystem
{

    /**
     * Asks if stacker is up
     * 
     * @return True if stacker is up else false
     */
    boolean getStackerUp();

    /**
     * Asks if stacker is down
     * 
     * @return True if stacker is down else false
     */
    boolean getStackerDown();

    /**
     * Asks if claw is up
     * 
     * @return True if claw is up else false
     */
    boolean getClawUp();

    /**
     * Asks if claw is open
     * 
     * @return True if claw is open else false
     */
    
    boolean getClawDown();

    /**
     * Asks if claw is down
     * 
     * @return True if claw is open else false
     */
    boolean getClawOpen();

    /**
     * Perform initialization.
     */
    
    public boolean getClawClose();
    
    /**
     * Perform initialization 
     * 
     */
    
    @Override
    void init();

    /**
     * Gathering and storing current sensor information. Ex. Motor Speed.
     */
    @Override
    void update();

    /**
     * Setting sensor and device states.
     */
    @Override
    void control();

    /**
     * Rereads and applies current preferences.
     */
    @Override
    void rereadPreferences();

    /**
     * Updates information that is sent to SmartDashboard Takes Enum argument
     */
    @Override
    void updateSmartDashboard();

    /**
     * Updates the logger.
     */
    @Override
    void updateLog();

    /**
     * Stops all sensors and motors
     */
    @Override
    void stop();

    /**
     * 
     * @return True if the controller should rumble and false if it should not
     */
    void setRumble(Boolean aRumbleOn);

}
