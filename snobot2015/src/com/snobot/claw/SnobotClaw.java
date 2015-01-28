package com.snobot.claw;

import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class for the snobot claw and implements the main interface for claw
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotClaw implements IClaw {

    private IOperatorJoystick mJoystick;
    private double mClawPressure;
    private Logger mLogger;

    /**
     * Constructs a SnobotClaw object
     * 
     * @param aJoystick
     *            Argument for operator Joystick
     *
     */
    public SnobotClaw(IOperatorJoystick aJoystick, Logger alogger) {
        mJoystick = aJoystick;
        mLogger = alogger;
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

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        mClawPressure = -1;
    }

    @Override
    public void control() {
        // TODO Auto-generated method stub

    }

    @Override
    public void rereadPreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard() {
        // TODO Auto-generated method stub
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE,
                mClawPressure);

    }

    @Override
    public void updateLog() {
        // TODO Auto-generated method stub

        String clawPressureString = String.valueOf(mClawPressure);

        mLogger.updateLogger(clawPressureString);

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

}
