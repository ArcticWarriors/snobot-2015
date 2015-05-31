package com.snobot.claw;

import com.snobot.Properties2015;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class for the snobot claw and implements the main interface for claw
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotClaw implements IClaw
{

    private IOperatorJoystick mOperatorJoystick;
    private double mRobotAirPressure;
    private Logger mLogger;
    private AnalogInput mTransducer;
    private Solenoid mClawHandSolenoid;
    private Solenoid mClawArmSolenoid;

    private boolean mRumbleOn;

    private double mAirPressureRangeMin;

    /**
     * Constructs a SnobotClaw object
     * 
     * @param aJoystick
     *            Argument for operator Joystick
     *
     */
    public SnobotClaw(IOperatorJoystick aJoystick, Logger alogger, AnalogInput aTransducer, Solenoid aClawHandSolenoid, Solenoid aClawArmSolenoid)
    {
        mOperatorJoystick = aJoystick;
        mLogger = alogger;
        mTransducer = aTransducer;
        mClawHandSolenoid = aClawHandSolenoid;
        mClawArmSolenoid = aClawArmSolenoid;

    }

    @Override
    public void openClaw()
    {
        mClawHandSolenoid.set(true);
    }

    @Override
    public void closeClaw()
    {
        mClawHandSolenoid.set(false);

    }

    @Override
    public void moveClawUp()
    {
        mClawArmSolenoid.set(true);

    }

    @Override
    public void moveClawDown()
    {
        mClawArmSolenoid.set(false);
    }

    @Override
    public void init()
    {
        mClawArmSolenoid.set(false);
        mClawHandSolenoid.set(false);
        mLogger.addHeader("Claw Pressure");
        rereadPreferences();
    }

    @Override
    public void update()
    {
        mRobotAirPressure = ((33.125 * mTransducer.getVoltage()) - 16.821);

        mRumbleOn = mRobotAirPressure < mAirPressureRangeMin;
    }

    @Override
    public void control()
    {
        mOperatorJoystick.setRumble(mRumbleOn);

        if (mOperatorJoystick.getClawOpen())
        {
            openClaw();
        }
        else if (mOperatorJoystick.getClawClose())
        {
            closeClaw();
        }
        else
        {
            stop();
        }

        if (mOperatorJoystick.getClawUp())
        {
            moveClawUp();
        }
        else if (mOperatorJoystick.getClawDown())
        {
            moveClawDown();
        }
        else
        {
            stop();
        }
    }

    @Override
    public void rereadPreferences()
    {

        mAirPressureRangeMin = Properties2015.sAIR_PRESSURE_RANGE_MIN.getValue();
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE, getRobotAirPressure());
        SmartDashboard.putBoolean(SmartDashboardNames.sCLAW_HAND_SOLENOID, mClawHandSolenoid.get());
        SmartDashboard.putBoolean(SmartDashboardNames.sCLAW_ARM_SOLENOID, mClawArmSolenoid.get());
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(getRobotAirPressure());

    }

    @Override
    public void stop()
    {
    }

    @Override
    public double getRobotAirPressure()
    {
        return mRobotAirPressure;
    }

}
