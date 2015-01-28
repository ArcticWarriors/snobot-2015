package com.snobot.joystick;

import com.snobot.ConfigurationNames;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Implements Driver Joystick Interface Sets up Snobot Flight Stick for Driver
 * Ayush/Ammar
 *
 */
public class SnobotFlightstickJoystick implements IDriverJoystick {

    private Joystick mLeftFlightStick;
    private Joystick mRightFlightStick;
    private DriveMode mDriveMode;
    private Logger mLogger;

    /**
     * Constructor for Flight Stick
     * 
     * @param aLeftFlightStick
     *            Argument for Left Flight Stick
     * @param aRightFlightStick
     *            Argument for Right Flight Stick
     */
    public SnobotFlightstickJoystick(Joystick aLeftFlightStick,
            Joystick aRightFlightStick, Logger aLogger) {
        System.out.println("Creating flightstick joystick");
        mLeftFlightStick = aLeftFlightStick;
        mRightFlightStick = aRightFlightStick;
        mLogger = aLogger;
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        mLogger.addHeader("Left Y Axis");
        mLogger.addHeader("Right Y Axis");
        mLogger.addHeader("Speed (-1 to 1)");
        mLogger.addHeader("Right X Axis");
        mDriveMode = DriveMode.Tank;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        if (mRightFlightStick
                .getRawButton(ConfigurationNames.sFlightsticks_Button_4)) {
            mDriveMode = DriveMode.Tank;
        }
        else if (mRightFlightStick
                .getRawButton(ConfigurationNames.sFlightsticks_Button_5)) {
            mDriveMode = DriveMode.Arcade;
        }
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

    }

    @Override
    public void updateLog() {
        // TODO Auto-generated method stub
        // Left Y Axis
        mLogger.updateLogger(getLeftY());

        // Right Y Axis
        mLogger.updateLogger(getRightY());

        // Speed
        mLogger.updateLogger(getSpeed());

        // Angle of the Joy stick (for arcade drive)
        mLogger.updateLogger(getRotate());
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getLeftY() {
        // TODO Auto-generated method stub
        return mLeftFlightStick
                .getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
    }

    @Override
    public double getRightY() {
        // TODO Auto-generated method stub
        return mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
    }

    @Override
    public double getSpeed() {
        // TODO Auto-generated method stub
        return mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
    }

    @Override
    public double getRotate() {
        // TODO Auto-generated method stub
        return mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_X_Axis);
    }

    @Override
    public DriveMode getDriveMode() {
        // TODO Auto-generated method stub
        return mDriveMode;
    }

}
