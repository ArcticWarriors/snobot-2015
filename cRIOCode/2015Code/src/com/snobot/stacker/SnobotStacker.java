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
        mStackerDefaultSpeed = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_DEFAULT_SPEED, .5);
        mUpperLimitSwitch = aUpperLimitSwitch;
        mLowerLimitSwitch = aLowerLimitSwitch;
        mLogger = aLogger;
        mStackerEncoder = aStackerEncoder;

        // TODO - PJ make configurable
        mStackerEncoder.setDistancePerPulse(.4);
    }

    
    public void moveStackerUp()
    {

        if (mUpperLimitSwitchState)
        {
            stop();
        }
        else
        {
            mStackerMotor.set(mStackerDefaultSpeed);
        }
        /**
         * Assuming Physical Limit Switch will stop stacker at limit
         */

    }

    
    public void moveStackerDown()
    {

        if (mLowerLimitSwitchState)
        {
            stop();
        }
        else
        {
            mStackerMotor.set(-mStackerDefaultSpeed);
        }
        /**
         * Assuming Physical Limit Switch will stop stacker at limit
         */
    }

    
    public void init()
    {
        stop();
        mLogger.addHeader("UpperLimitSwitchState");
        mLogger.addHeader("LowerLimitSwitchState");
        mLogger.addHeader("StackerEncoderDistance");
        mLogger.addHeader("StackerMotorValue");
    }

    
    public void update()
    {
        mUpperLimitSwitchState = mUpperLimitSwitch.get();
        mLowerLimitSwitchState = mLowerLimitSwitch.get();
        mStackerEncoderDistance = mStackerEncoder.getDistance();
        mStackerMotorValue = mStackerMotor.get();

    }

    
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

    
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    
    public void updateSmartDashboard()
    {
        SmartDashboard.putBoolean(SmartDashboardNames.sUPPER_LIMIT_SWITCH_STATE, mUpperLimitSwitchState);
        SmartDashboard.putBoolean(SmartDashboardNames.sLOWER_LIMIT_SWITCH_STATE, mLowerLimitSwitchState);
        SmartDashboard.putNumber(SmartDashboardNames.sENCODER_HEIGHT, mStackerEncoderDistance);
        SmartDashboard.putNumber(SmartDashboardNames.sSTACKER_MOTOR_VALUE, mStackerMotorValue);
    }

    
    public void updateLog()
    {

        mLogger.updateLogger(mUpperLimitSwitchState);
        mLogger.updateLogger(mLowerLimitSwitchState);
        mLogger.updateLogger(mStackerEncoderDistance);
        mLogger.updateLogger(mStackerMotorValue);

    }

    
    public void stop()
    {
        mStackerMotor.set(0);

    }

}