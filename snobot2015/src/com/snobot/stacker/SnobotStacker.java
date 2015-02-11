package com.snobot.stacker;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Main class for Snobot Stacker subsystem
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotStacker implements IStacker
{

    private SpeedController mStackerMotor;
    private IOperatorJoystick mOperatorJoystick;
    private boolean mUpperLimitSwitchState;
    private boolean mLowerLimitSwitchState;
    private double mStackerMotorValue;
    private double mStackerHeight;
    private double mStackerGroundHeight;
    private double mStackerScoringPlatformHeight;
    private double mStackerOneStackHeight;
    private double mStackerStackingMargin;
    private double mAdjustedStackerSpeed;
    private Logger mLogger;
    private DigitalInput mUpperLimitSwitch;
    private DigitalInput mLowerLimitSwitch;
    private AnalogInput mStackerPotentiometer;

    /**
     * Constructs a SnobotStacker object
     * 
     * @param aOperatorJoystick
     *            Argument of operator joy stick
     */
    public SnobotStacker(IOperatorJoystick aOperatorJoystick, SpeedController aStackerMotor, DigitalInput aUpperLimitSwitch,
            DigitalInput aLowerLimitSwitch, Logger aLogger, AnalogInput aStackerEncoder)
    {
        mOperatorJoystick = aOperatorJoystick;
        mStackerMotor = aStackerMotor;
        mUpperLimitSwitch = aUpperLimitSwitch;
        mLowerLimitSwitch = aLowerLimitSwitch;
        mLogger = aLogger;
        mStackerPotentiometer = aStackerEncoder;
    }

    @Override
    public boolean moveStackerUp()
    {

        if (mUpperLimitSwitchState)
        {
//            System.out.println("Move Stacker Up Stopped :)");
            stop();
            return false;
        }
        else
        {
//            System.out.println("Move Stacker Up Moving :)");
            setElevatorSpeed(mAdjustedStackerSpeed);
            return true;
        }
        /**
         * Assuming Physical Limit Switch will stop stacker at limit
         */

    }

    @Override
    public boolean moveStackerDown()
    {

        if (mLowerLimitSwitchState)
        {
//            System.out.println("Move Stacker Down Stopped :)");
            stop();
            return false;
        }
        else
        {
//            System.out.println("Move Stacker Down Moving :)");
            setElevatorSpeed(mAdjustedStackerSpeed);
            return true;
        }
        /**
         * Assuming Physical Limit Switch will stop stacker at limit
         */
    }
    
    public boolean moveStackerToGround()
    {
       return moveStackerToHeight(mStackerGroundHeight);
    }
    
    public boolean moveStackerToScoringPlatform()
    {
        return moveStackerToHeight(mStackerScoringPlatformHeight);
    }

    public boolean moveStackerToOneStack()
    {
        return moveStackerToHeight(mStackerOneStackHeight);
    } 

    public boolean moveStackerToHeight(double aHeight)
    {
        double current_height = getStackerHeight();
        double error = aHeight - current_height;
        double kp = .1; // TODO make configurable

        if (Math.abs(error) < mStackerStackingMargin)
        {
            stop();
            return true;
        }
        else if (current_height > aHeight)
        {
            mAdjustedStackerSpeed = error * kp;
            return !moveStackerDown();
        }
        else
        {
            mAdjustedStackerSpeed = error * kp;
            return !moveStackerUp();
        }
    }

    @Override
    public void init()
    {
        stop();
        mLogger.addHeader("UpperLimitSwitchState");
        mLogger.addHeader("LowerLimitSwitchState");
        mLogger.addHeader("StackerEncoderDistance");
        mLogger.addHeader("StackerMotorValue");
        
        rereadPreferences ();
    }

    @Override
    public void update()
    {
        mAdjustedStackerSpeed = mOperatorJoystick.getJoystickValue();
        mUpperLimitSwitchState = !mUpperLimitSwitch.get();
        mLowerLimitSwitchState = !mLowerLimitSwitch.get();
        mStackerMotorValue = mStackerMotor.get();

        double pot_voltage = mStackerPotentiometer.getVoltage();
        double pot_min = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_POT_MIN_VOLTS, 0);
        double pot_vpi = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_POT_VOLTS_PER_INCH, .25);

        mStackerHeight = (pot_voltage - pot_min) / pot_vpi;
    }

    @Override
    public void control()
    {
        if (mOperatorJoystick.getStackerToFloorButton())
        {
            moveStackerToGround();
        }
        else if (mOperatorJoystick.getStackerUp())
        {
            moveStackerUp();
        }
        else if (mOperatorJoystick.getStackerDown())
        {
            moveStackerDown();
        }
        else
        {
            stop();
        }

    }

    private double getStackerHeight()
    {
        return mStackerHeight;
    }

    @Override
    public void rereadPreferences()
    {
        mStackerGroundHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_GROUND_HEIGHT, 0);
        mStackerScoringPlatformHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_SCORING_PLATFORM_HEIGHT, 2);
        mStackerOneStackHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_ONE_STACK_HEIGHT, 13.1);
        mStackerStackingMargin = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_STACKING_MARGIN, .5);

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putBoolean(SmartDashboardNames.sUPPER_LIMIT_SWITCH_STATE, mUpperLimitSwitchState);
        SmartDashboard.putBoolean(SmartDashboardNames.sLOWER_LIMIT_SWITCH_STATE, mLowerLimitSwitchState);
        SmartDashboard.putNumber(SmartDashboardNames.sENCODER_HEIGHT, getStackerHeight());
        SmartDashboard.putNumber(SmartDashboardNames.sSTACKER_MOTOR_VALUE, mStackerMotorValue);
    }

    @Override
    public void updateLog()
    {

        mLogger.updateLogger(mUpperLimitSwitchState);
        mLogger.updateLogger(mLowerLimitSwitchState);
        mLogger.updateLogger(getStackerHeight());
        mLogger.updateLogger(mStackerMotorValue);

    }

    @Override
    public void stop()
    {
        setElevatorSpeed(0);

    }

    private void setElevatorSpeed(double aSpeed)
    {
        mStackerMotor.set(aSpeed);
    }

}
