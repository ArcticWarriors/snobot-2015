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
    // TODO comments
    public boolean getStackerToFloorButton();

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
    
    public double getJoystickValue ();
    /**
     * Gets value of Joystick to set as stacker speed
     */
    
    
    void init();

    /**
     * Gathering and storing current sensor information. Ex. Motor Speed.
     */
    
    void update();

    /**
     * Setting sensor and device states.
     */
    
    void control();

    /**
     * Rereads and applies current preferences.
     */
    
    void rereadPreferences();

    /**
     * Updates information that is sent to SmartDashboard Takes Enum argument
     */
    
    void updateSmartDashboard();

    /**
     * Updates the logger.
     */
    
    void updateLog();

    /**
     * Stops all sensors and motors
     */
    
    void stop();

    /**
     * 
     * @return True if the controller should rumble and false if it should not
     */
    void setRumble(boolean aRumbleOn);

}
