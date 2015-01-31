package com.snobot.operatorjoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

/**
 * Monitors state of operator joystick for other classes/objects to use
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotOperatorJoystick implements IOperatorJoystick
{

    private Joystick mOperatorJoystick;

    /**
     * Constructs a SnobotOperatorJoystick object
     * 
     * @param aOperatorJoystick
     *            Argument for operator Joystick
     */
    public SnobotOperatorJoystick(Joystick aOperatorJoystick)
    {
        mOperatorJoystick = aOperatorJoystick;
    }

    @Override
    public boolean getStackerUp()
    {

        if (mOperatorJoystick.getRawAxis(1) >= .2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean getStackerDown()
    {

        if (mOperatorJoystick.getRawAxis(1) <= -.2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean getClawUp()
    {
        if (mOperatorJoystick.getRawButton(1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean getClawDown()
    {
        if (mOperatorJoystick.getRawButton(2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    

    @Override
    public boolean getClawOpen()
    {
        if (mOperatorJoystick.getRawButton(3))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public boolean getClawClose()
    {
        if (mOperatorJoystick.getRawButton(4))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Perform initialization.
     */
    @Override
    public void init()
    {
    }

    /**
     * Gathering and storing current sensor information. Ex. Motor Speed.
     */
    @Override
    public void update()
    {

    }

    /**
     * Setting sensor and device states.
     */
    @Override
    public void control()
    {

    }

    /**
     * Rereads and applies current preferences.
     */
    @Override
    public void rereadPreferences()
    {

    }

    /**
     * Updates information that is sent to SmartDashboard Takes Enum argument
     */
    @Override
    public void updateSmartDashboard()
    {

    }

    /**
     * Updates the logger.
     */
    @Override
    public void updateLog()
    {

    }

    /**
     * Stops all sensors and motors
     */
    @Override
    public void stop()
    {

    }

    @Override
    public void setRumble(Boolean aRumbleOn)
    {

        float lRumbleMagnitude = 0;
        if (aRumbleOn)
        {
            lRumbleMagnitude = 1;
        }

        mOperatorJoystick.setRumble(RumbleType.kLeftRumble, lRumbleMagnitude);
        mOperatorJoystick.setRumble(RumbleType.kRightRumble, lRumbleMagnitude);
    }
}
