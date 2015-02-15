package com.snobot.rake;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotRake implements IRake {

    private SpeedController mRakeMotor;
    private IOperatorJoystick mOperatorJoystick;
    private DigitalInput mLimitSwitch;
    private boolean mLimitSwitchDepressed;
    private Logger mLogger;
    private double mMotorSpeed;
    private double mRakeValueDown;
    private double mRakeValueUp;

    /**
     * Constructs a SnobotRake object
     * 
     * @param aRakeMotor
     * @param aOperatorJoystick
     * @param aLimitSwitch
     *            Argument of operator Rake Motor, Operator Joystick, Limit
     *            Switch
     */
    public SnobotRake(SpeedController aRakeMotor, IOperatorJoystick aOperatorJoystick, DigitalInput aLimitSwitch, Logger aLogger) {
        mRakeMotor = aRakeMotor;
        mOperatorJoystick = aOperatorJoystick;
        mLimitSwitch = aLimitSwitch;
        mLogger = aLogger;
    }

    @Override
    public void init() {
        stop();
        mLogger.addHeader("LimitSwitchDepressed");
        mLogger.addHeader("RakeMotor");
        rereadPreferences();
    }

    @Override
    public void update() {
        mLimitSwitchDepressed = !mLimitSwitch.get();

        mRakeValueUp = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sRAKE_JOYSTICK_VALUE_UP, .2);
        mRakeValueDown = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sRAKE_JOYSTICK_VALUE_DOWN, -.2);

    }

    @Override
    public void control() {

        if (mOperatorJoystick.getMoveRake() > mRakeValueUp)
        {
            moveRakeDown();
        }
        else if (mOperatorJoystick.getMoveRake() < mRakeValueDown && mLimitSwitchDepressed)
        {
            moveRakeUp();
        }
        else {
            stop();
        }
    }

    @Override
    public void rereadPreferences() {
        mMotorSpeed = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sRAKE_MOTOR_SPEED, 0.5);
    }

    @Override
    public void updateSmartDashboard() {

        SmartDashboard.putBoolean(SmartDashboardNames.sLIMIT_SWITCH_DEPRESSED, mLimitSwitchDepressed);
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_MOTOR, mRakeMotor.get());
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_JOYSTICK_VALUE_UP, mRakeValueUp);
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_JOYSTICK_VALUE_DOWN, mRakeValueDown);

    }

    @Override
    public void updateLog() {


        mLogger.updateLogger(mLimitSwitchDepressed);
        mLogger.updateLogger(mRakeMotor.get());
    }

    @Override
    public void stop() {

        mRakeMotor.set(0);
    }

    @Override
    public void moveRakeDown() {
        mRakeMotor.set(mMotorSpeed);
    }

    @Override
    public void moveRakeUp() {
        mRakeMotor.set(-mMotorSpeed);
    }

}
