package com.snobot.operatorjoystick;

import com.snobot.ConfigurationNames;
import com.snobot.XboxButtonMap;

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
    private double mStackerJoystickDirection;
    private int mStackerJoystickAxis1;
    private int mXBOXButtonClawUp;
    private int mXBOXButtonClawDown;
    private int mXBOXButtonClawOpen;
    private int mXBOXButtonClawClose;
    private double mXBOXStackerJoystickUp;
    private double mXBOXStackerJoystickDown;

    private int mMoveStackerToFloorButton;
    

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

    @Override
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

    @Override
    public double getJoystickValue()
    {
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
        //Thresholds
        mXBOXStackerJoystickUp = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sXBOX_JOYSTICK_STACKER_UP, .2);
        mXBOXStackerJoystickDown = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sXBOX_JOYSTICK_STACKER_DOWN, -.2);
        
        // Buttons
        mXBOXButtonClawOpen = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_OPEN, XboxButtonMap.RB_BUTTON);
        mXBOXButtonClawClose = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_CLOSE, XboxButtonMap.LB_BUTTON);
        mXBOXButtonClawUp = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_UP, XboxButtonMap.Y_BUTTON);
        mXBOXButtonClawDown = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_CLAW_DOWN, XboxButtonMap.X_BUTTON);
        mStackerJoystickAxis1 = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_Y_AXIS, 1);

        mMoveStackerToFloorButton = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_TO_FLOOR_BTN, XboxButtonMap.B_BUTTON);
        
        // Joystick values
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

    @Override
    public boolean getStackerToFloorButton()
    {
        return mOperatorJoystick.getRawButton(mMoveStackerToFloorButton);
    }
}
