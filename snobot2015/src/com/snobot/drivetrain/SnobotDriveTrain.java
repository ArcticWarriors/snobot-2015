package com.snobot.drivetrain;

import com.snobot.SmartDashboardNames;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.IDriverJoystick.DriveMode;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets up specific snobot drive train
 * 
 * @author Ayush/Ammar
 *
 */
public class SnobotDriveTrain implements IDriveTrain {

    private SpeedController mSpeedControllerLeft;
    private SpeedController mSpeedControllerRight;
    private IDriverJoystick mDriverJoystick;
    private RobotDrive mRobotDrive;
    private DriveMode mDriveMode;

    private Logger mLogger;
    private UnitOfMeasure mDefaultMeasure;

    /**
     * Takes 2 speed controllers and joy stick arguments
     * 
     * @param aSpeedControllerLeft
     *            Argument for left Speed Controller
     * @param aSpeedControllerRight
     *            Argument for right Speed Controller
     * @param aDriverJoystick
     *            Argument Driver Joy stick
     */
    public SnobotDriveTrain(SpeedController aSpeedControllerLeft, SpeedController aSpeedControllerRight, IDriverJoystick aDriverJoystick,
            DriveMode aDriveMode) {
        mSpeedControllerLeft = aSpeedControllerLeft;
        mSpeedControllerRight = aSpeedControllerRight;
        mDriverJoystick = aDriverJoystick;
        mRobotDrive = new RobotDrive(mSpeedControllerLeft, mSpeedControllerRight);
        mDriveMode = aDriveMode;
        mDefaultMeasure = UnitOfMeasure.Feet;
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void control() {
        // TODO Add switch between the two drives

        if (IDriverJoystick.DriveMode.Arcade == mDriverJoystick.getDriveMode()) {
            mRobotDrive.arcadeDrive(mDriverJoystick.getSpeed(), mDriverJoystick.getRotate());
            System.out.println("Aracde Drive");
        }
        else if (IDriverJoystick.DriveMode.Tank == mDriverJoystick.getDriveMode()) {
            mRobotDrive.tankDrive(mDriverJoystick.getLeftY(), mDriverJoystick.getRightY(), true);
            System.out.println("Tank Drive");
        }

    }

    @Override
    public void rereadPreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard() 
    {
        SmartDashboard.putNumber(SmartDashboardNames.sLEFT_DRIVE_SPEED, mSpeedControllerLeft.get());
        SmartDashboard.putNumber(SmartDashboardNames.sRIGHT_DRIVE_SPEED, mSpeedControllerRight.get());
    }

    @Override
    public void updateLog() 
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() 
    {
        this.setMotorSpeed(0, 0);

    }

    @Override
    public void setMotorSpeed(double aLeft, double aRight) {
        // TODO Auto-generated method stub

    }

    @Override
    public double calculateDistanceRight() {
        // TODO Code to calculate distance goes here; needs encoders
        // Remember to refer to mDefaultMeasure
        return 0;
    }

    @Override
    public double calculateDistanceLeft() {
        // TODO Code to calculate distance goes here; needs encoders
        // Remember to refer to mDefaultMeasure
        return 0;
    }

    @Override
    public void setDefaultMeasure(UnitOfMeasure aMeasure) {
        mDefaultMeasure = aMeasure;

    }
}
