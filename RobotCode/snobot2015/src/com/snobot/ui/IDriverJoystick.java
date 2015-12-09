package com.snobot.ui;

import com.snobot.xlib.ISubsystem;

/**
 * Main interface for Driver Joystick
 * 
 * @author Ayush/Ammar
 *
 */

public interface IDriverJoystick extends ISubsystem
{

    /**
     * enumeration to decide drive mode
     * 
     * @author ayush_000
     *
     */
    public enum DriveMode
    {
        Arcade, Tank
    }

    /**
     * Gets input from Left Stick in tank mode
     * 
     * @return double for Left Joy stick (-1 to 1)
     */
    double getLeftY();

    /**
     * Gets Input from Right Stick in tank mode
     * 
     * @return double for Right Joy stick (-1 to 1)
     */
    double getRightY();

    /**
     * Get the speed for arcade mode
     * 
     * @return double for Speed
     */
    double getSpeed();

    /**
     * Gets angle for arcade mode
     * 
     * @return double for joy stick angle
     */
    double getRotate();

    /**
     * 
     * @return drive mode (Tank/Arcade)
     */
    DriveMode getDriveMode();

    /**
     * 
     * @return drive mode (Tank/Arcade)
     */
    void setDriveMode(DriveMode aDriveMode);

    boolean getDriveForward();

    boolean getDriveBackward();

    boolean isReducedSpeedMode();

}
