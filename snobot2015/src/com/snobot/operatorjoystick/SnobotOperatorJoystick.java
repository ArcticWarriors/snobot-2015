package com.snobot.operatorjoystick;

import com.snobot.ConfigurationNames;

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

    @Override
    public boolean getStackerUp()
    {
       return mStackerJoystickDirection >= mXBOXStackerJoystickUp;
    }

    @Override
    public boolean getStackerDown()
    {
       return mStackerJoystickDirection <= mXBOXStackerJoystickDown;
    }

    @Override
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
    

    @Override
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
    
    @Override
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
    
    public double getJoystickValue (){
        return mStackerJoystickDirection;
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
        mXBOXStackerJoystickUp = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sXBOX_JOYSTICK_STACKER_UP, .2);
        mXBOXStackerJoystickDown = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sXBOX_JOYSTICK_STACKER_DOWN, -.2);
        mXBOXButtonClawOpen = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_OPEN, 6);
        mXBOXButtonClawClose = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_CLOSE, 5);
        mXBOXButtonClawUp = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_UP, 4);
        mXBOXButtonClawDown = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_DOWN, 3);
        mStackerJoystickAxis1 = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_Y_AXIS, 1);
        mStackerJoystickDirection = -mOperatorJoystick.getRawAxis(mStackerJoystickAxis1);
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
