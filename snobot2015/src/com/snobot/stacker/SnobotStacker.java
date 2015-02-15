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
    private double mStackerCoopHeight;
    private double mStackerStackingMargin;
    private double mAdjustedStackerSpeed;
    private double mStackerLimitSpeedUp;
    private double mStackerLimitSpeedDown;
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
            double safeSpeed = mAdjustedStackerSpeed;
            if (safeSpeed > mStackerLimitSpeedUp)
            {
                safeSpeed = mStackerLimitSpeedUp;
            }
            // System.out.println("Move Stacker Up Moving :) safe speed = " + safeSpeed + "... " + mStackerLimitSpeedUp);

            setElevatorSpeed(safeSpeed);
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
            double safeSpeed = mAdjustedStackerSpeed;
            if (safeSpeed < mStackerLimitSpeedDown)
            {
                safeSpeed = mStackerLimitSpeedDown;
            }
//            System.out.println("Move Stacker Down Moving :) safe speed = " + safeSpeed + "... " + mStackerLimitSpeedDown);
            setElevatorSpeed(safeSpeed);
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

    public boolean moveStackerToCoopHeight()
    {
        return moveStackerToHeight(mStackerCoopHeight);
    }

    public boolean moveStackerToHeight(double aHeight)
    {
        double current_height = getStackerHeight();
        double error = aHeight - current_height;
        double kp = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_KP, .35);
        double pid_speed = error * kp;

//        System.out.println("Current Hieght: " + current_height + " , error: " + error + " , Stacker Speed: " + pid_speed);

        if (Math.abs(error) < mStackerStackingMargin)
        {
            stop();
            return true;
        }
        else if (current_height > aHeight)
        {
            mAdjustedStackerSpeed = pid_speed;
            return !moveStackerDown();
        }
        else
        {
            mAdjustedStackerSpeed = pid_speed;
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
        double pot_top_min = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sPOT_TOP_MIN_VOLT, 3.596190);
        double pot_max_bot = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sPOT_BOT_MAX_VOLT, 4.9731405);
        double max_stacker_height = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_MAX_HEIGHT, 24);
        double pot_diff = (pot_max_bot - pot_top_min);
        double pot_ipv = max_stacker_height / pot_diff;
        mStackerHeight = max_stacker_height - ((pot_voltage - pot_top_min) * pot_ipv);

        // System.out.println("Pot Voltage: " + pot_voltage + ", diff=" + pot_diff + ", Stacker Height: " + mStackerHeight);

    }

    @Override
    public void control()
    {
        if (mOperatorJoystick.getStackerUp())
        {
            moveStackerUp();
        }
        else if (mOperatorJoystick.getStackerDown())
        {
            moveStackerDown();
        }

        else if (mOperatorJoystick.getStackerToFloorButton())
        {
            moveStackerToGround();
        }

        else if (mOperatorJoystick.getMoveToScoring())
        {
            moveStackerToScoringPlatform();
        }
        else if (mOperatorJoystick.getMoveToOneStack())
        {
            moveStackerToOneStack();
        }
        else if (mOperatorJoystick.getMoveToCoopHeight())
        {
            moveStackerToCoopHeight();
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
        mStackerCoopHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_COOP_HEIGHT, 23.5);

        mStackerLimitSpeedUp = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_LIMIT_SPEED_UP, 0.7);
        mStackerLimitSpeedDown = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_LIMIT_SPEED_DOWN, -0.35);

        // Just reading these so they will get automatically put into the preferences
        ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_KP, .05);
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
        mStackerMotor.set(-aSpeed);
    }

}
