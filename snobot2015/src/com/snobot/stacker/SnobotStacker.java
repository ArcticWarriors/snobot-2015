package com.snobot.stacker;

import com.snobot.Properties2015;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;
import com.snobot.xlib.PropertyManager.DoubleProperty;

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
    public enum StackerHeights
    {
        Floor(Properties2015.sSTACKER_GROUND_HEIGHT),
        ScoringPlatform(Properties2015.sSTACKER_SCORING_PLATFORM_HEIGHT),
        OneTote(Properties2015.sSTACKER_ONE_STACK_HEIGHT),
        Coop(Properties2015.sSTACKER_COOP_HEIGHT);

        private DoubleProperty mHeight;

        StackerHeights(DoubleProperty aHeight)
        {
            mHeight = aHeight;
        }

        public double getDesiredHeight()
        {
            return mHeight.getValue();
        }
    }

    private SpeedController mStackerMotor;
    private IOperatorJoystick mOperatorJoystick;
    private boolean mUpperLimitSwitchState;
    private boolean mLowerLimitSwitchState;
    private double mStackerMotorValue;
    private double mStackerHeight;

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
     * @param aOperatorJoystick Argument of operator joy stick
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
            // System.out.println("Move Stacker Up Stopped :)");
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
            // System.out.println("Move Stacker Up Moving :) safe speed = " +
            // safeSpeed + "... " + mStackerLimitSpeedUp);

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
            // System.out.println("Move Stacker Down Stopped :)");
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
            // System.out.println("Move Stacker Down Moving :) safe speed = " +
            // safeSpeed + "... " + mStackerLimitSpeedDown);
            setElevatorSpeed(safeSpeed);
            return true;
        }
        /**
         * Assuming Physical Limit Switch will stop stacker at limit
         */
    }

    public boolean moveStackerToGround()
    {
        return moveStackerToHeight(StackerHeights.Floor);
    }

    public boolean moveStackerToScoringPlatform()
    {
        return moveStackerToHeight(StackerHeights.ScoringPlatform);
    }

    public boolean moveStackerToOneStack()
    {
        return moveStackerToHeight(StackerHeights.OneTote);
    }

    public boolean moveStackerToCoopHeight()
    {
        return moveStackerToHeight(StackerHeights.Coop);
    }

    public boolean moveStackerToHeight(StackerHeights floor)
    {
        double desired_height = floor.getDesiredHeight();
        double current_height = getStackerHeight();
        double error = desired_height - current_height;
        double kp = Properties2015.sSTACKER_KP.getValue();
        double pid_speed = error * kp;

        // System.out.println("Current Hieght: " + current_height + " , error: "
        // + error + " , Stacker Speed: " + pid_speed);

        if (Math.abs(error) < mStackerStackingMargin)
        {
            stop();
            return true;
        }
        else if (current_height > desired_height)
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

        rereadPreferences();
    }

    @Override
    public void update()
    {
        mAdjustedStackerSpeed = mOperatorJoystick.getJoystickValue();
        mUpperLimitSwitchState = !mUpperLimitSwitch.get();
        mLowerLimitSwitchState = !mLowerLimitSwitch.get();
        mStackerMotorValue = mStackerMotor.get();

        double pot_voltage = mStackerPotentiometer.getVoltage();
        double pot_top_min = Properties2015.sPOT_TOP_MIN_VOLT.getValue();
        double pot_max_bot = Properties2015.sPOT_BOT_MAX_VOLT.getValue();
        double max_stacker_height = Properties2015.sSTACKER_MAX_HEIGHT.getValue();
        double pot_diff = (pot_max_bot - pot_top_min);
        double pot_ipv = max_stacker_height / pot_diff;
        mStackerHeight = max_stacker_height - ((pot_voltage - pot_top_min) * pot_ipv);

        // System.out.println("Pot Voltage: " + pot_voltage + ", diff=" +
        // pot_diff + ", Stacker Height: " + mStackerHeight);

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
        mStackerStackingMargin = Properties2015.sSTACKER_STACKING_MARGIN.getValue();

        mStackerLimitSpeedUp = Properties2015.sSTACKER_LIMIT_SPEED_UP.getValue();
        mStackerLimitSpeedDown = Properties2015.sSTACKER_LIMIT_SPEED_DOWN.getValue();
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
