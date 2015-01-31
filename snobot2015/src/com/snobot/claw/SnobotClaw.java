package com.snobot.claw;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class for the snobot claw and implements the main interface for claw
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotClaw implements IClaw {

    private IOperatorJoystick mJoystick;
    private double mClawPressureA;
    private double mClawPressureB;
    private Logger mLogger;
    private AnalogInput mTransducer;

    private boolean mRumbleOn;

    private double mAirPressureRangeMin;

    /**
     * Constructs a SnobotClaw object
     * 
     * @param aJoystick
     *            Argument for operator Joystick
     *
     */
    public SnobotClaw(IOperatorJoystick aJoystick, Logger alogger, AnalogInput aTransducer) {
        mJoystick = aJoystick;
        mLogger = alogger;
        mTransducer = aTransducer;

    }

    @Override
    public void openClaw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeClaw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveClawUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveClawDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        mLogger.addHeader("Claw Up/Down Pressure");
        mLogger.addHeader("Claw Open/Close Pressure");
        rereadPreferences();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        mClawPressureA = -1;
        mClawPressureB = -1;

        if (mClawPressureA < mAirPressureRangeMin) {
            mRumbleOn = true;
        }
        else {
            mRumbleOn = false;
        }
    }

    @Override
    public void control() {
        mJoystick.setRumble(mRumbleOn);
    }

    @Override
    public void rereadPreferences() {

        mAirPressureRangeMin = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sAir_Pressure_Range_Min, 50);
    }

    @Override
    public void updateSmartDashboard() {
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE, mClawPressureA);
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE, mClawPressureB);
    }

    @Override
    public void updateLog() {
        mLogger.updateLogger(mClawPressureA);

        mLogger.updateLogger(mClawPressureB);

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getAirPressureA() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getAirPressureB() {
        // TODO Auto-generated method stub
        return 0;
    }

}
