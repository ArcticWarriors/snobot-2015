package com.snobot.stacker;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
    private double mStackerDefaultSpeed;
    private boolean mUpperLimitSwitchState;
    private boolean mLowerLimitSwitchState;
    private double mStackerEncoderDistance;
    private double mStackerMotorValue;
    private double mStackerHeight;
    private double mStackerGroundHeight;
    private double mStackerScoringPlatformHeight;
    private double mStackerOneStackHeight;
    private double mStackerTwoStackHeight;
    private double mStackerThreeStackHeight;
    private double mStackerStackingMargin;
    private Logger mLogger;
    DigitalInput mUpperLimitSwitch;
    DigitalInput mLowerLimitSwitch;
    Encoder mStackerEncoder;

    /**
     * Constructs a SnobotStacker object
     * 
     * @param aOperatorJoystick
     *            Argument of operator joy stick
     */
    public SnobotStacker(IOperatorJoystick aOperatorJoystick, SpeedController aStackerMotor, DigitalInput aUpperLimitSwitch,
            DigitalInput aLowerLimitSwitch, Logger aLogger, Encoder aStackerEncoder)
    {
        mOperatorJoystick = aOperatorJoystick;
        mStackerMotor = aStackerMotor;
        mUpperLimitSwitch = aUpperLimitSwitch;
        mLowerLimitSwitch = aLowerLimitSwitch;
        mLogger = aLogger;
        mStackerEncoder = aStackerEncoder;

        // TODO - PJ make configurable
        mStackerEncoder.setDistancePerPulse(.4);
    }

    @Override
    public boolean moveStackerUp()
    {

        if (mUpperLimitSwitchState)
        {
            stop();
            return false;
        }
        else
        {
            mStackerMotor.set(mStackerDefaultSpeed);
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
            stop();
            return false;
        }
        else
        {
            mStackerMotor.set(-mStackerDefaultSpeed);
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
    
    public boolean moveStackerToTwoStack()
    {
        return moveStackerToHeight(mStackerTwoStackHeight);
    }
    
    public boolean moveStackerToThreeStack()
    {
        return moveStackerToHeight(mStackerThreeStackHeight);
    }

    public boolean moveStackerToHeight(double aHeight)
    {
        if (mStackerHeight <= aHeight + mStackerStackingMargin
                && mStackerHeight >= aHeight - mStackerStackingMargin)
        {
            stop();
            return true;
        }
        else if (mStackerHeight > aHeight)
        {
            return !moveStackerDown();
        }
        else
        {
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
        mStackerHeight = mStackerEncoder.getDistance();
        mUpperLimitSwitchState = mUpperLimitSwitch.get();
        mLowerLimitSwitchState = mLowerLimitSwitch.get();
        mStackerEncoderDistance = mStackerEncoder.getDistance();
        mStackerMotorValue = mStackerMotor.get();

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
        else
        {
            stop();
        }

    }

    @Override
    public void rereadPreferences()
    {
        mStackerDefaultSpeed = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_DEFAULT_SPEED, .5);
        mStackerGroundHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_GROUND_HEIGHT, 0);
        mStackerScoringPlatformHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_SCORING_PLATFORM_HEIGHT, 2);
        mStackerOneStackHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_ONESTACK_HEIGHT, 13.1);
        mStackerTwoStackHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_TWO_STACK_HEIGHT, 26.2);
        mStackerThreeStackHeight = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_THREE_STACK_HEIGHT, 39.3);
        mStackerStackingMargin = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_STACKING_MARGIN, .5);

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putBoolean(SmartDashboardNames.sUPPER_LIMIT_SWITCH_STATE, mUpperLimitSwitchState);
        SmartDashboard.putBoolean(SmartDashboardNames.sLOWER_LIMIT_SWITCH_STATE, mLowerLimitSwitchState);
        SmartDashboard.putNumber(SmartDashboardNames.sENCODER_HEIGHT, mStackerEncoderDistance);
        SmartDashboard.putNumber(SmartDashboardNames.sSTACKER_MOTOR_VALUE, mStackerMotorValue);
    }

    @Override
    public void updateLog()
    {

        mLogger.updateLogger(mUpperLimitSwitchState);
        mLogger.updateLogger(mLowerLimitSwitchState);
        mLogger.updateLogger(mStackerEncoderDistance);
        mLogger.updateLogger(mStackerMotorValue);

    }

    @Override
    public void stop()
    {
        mStackerMotor.set(0);

    }

}
