package com.snobot.operatorjoystick;

import com.snobot.ConfigurationNames;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Monitors state of operator joystick for other classes/objects to use
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotOperatorJoystick implements IOperatorJoystick
{
    double mStackerJoystickDirection;
    int mStackerJoystickAxis1;
    int mXBOXButtonClawUp;
    int mXBOXButtonClawDown; 
    int mXBOXButtonClawOpen;
    int mXBOXButtonClawClose;
    double mXBOXStackerJoystickUp;
    double mXBOXStackerJoystickDown;
    

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

    
    public boolean getStackerUp()
    {
       return mStackerJoystickDirection >= mXBOXStackerJoystickUp;
    }

    
    public boolean getStackerDown()
    {
       return mStackerJoystickDirection <= mXBOXStackerJoystickDown;
    }

    
    public boolean getClawUp()
    {
        if (mOperatorJoystick.getRawButton(mXBOXButtonClawUp))
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
        if (mOperatorJoystick.getRawButton(mXBOXButtonClawDown))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    

    
    public boolean getClawOpen()
    {
        if (mOperatorJoystick.getRawButton(mXBOXButtonClawOpen))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public boolean getClawClose()
    {
        if (mOperatorJoystick.getRawButton(mXBOXButtonClawClose))
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
    
    public void init()
    {
    }

    /**
     * Gathering and storing current sensor information. Ex. Motor Speed.
     */
    
    public void update()
    {
        mXBOXStackerJoystickUp = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sXBOX_JOYSTICK_STACKER_UP, .2);
        mXBOXStackerJoystickDown = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sXBOX_JOYSTICK_STACKER_DOWN, -.2);
        mXBOXButtonClawOpen = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_OPEN, 3);
        mXBOXButtonClawClose = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_CLOSE, 4);
        mXBOXButtonClawUp = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_UP, 1);
        mXBOXButtonClawDown = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_DOWN, 2);
        mStackerJoystickAxis1 = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFlightsticks_Y_Axis, 1);
        mStackerJoystickDirection =mOperatorJoystick.getRawAxis(mStackerJoystickAxis1);
    }

    /**
     * Setting sensor and device states.
     */
    
    public void control()
    {

    }

    /**
     * Rereads and applies current preferences.
     */
    
    public void rereadPreferences()
    {

    }

    /**
     * Updates information that is sent to SmartDashboard Takes Enum argument
     */
    
    public void updateSmartDashboard()
    {

    }

    /**
     * Updates the logger.
     */
    
    public void updateLog()
    {

    }

    /**
     * Stops all sensors and motors
     */
    
    public void stop()
    {

    }

    
    public void setRumble(boolean aRumbleOn)
    {
    }
}
