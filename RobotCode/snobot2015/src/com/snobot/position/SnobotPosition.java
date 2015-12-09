package com.snobot.position;

import com.snobot.SmartDashboardNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.logger.Logger;
import com.snobot.xlib.ISubsystem;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Calculates Snobot's current position and orientation with the side of the field opposite drivers defined as north
 * 
 * @author Alec
 *
 */
public class SnobotPosition implements ISubsystem, IPositioner
{

    /**
     * Snobot's X-position
     */
    private double mPositionX;

    /**
     * Snobot's Y-position
     */
    private double mPositionY;

    /**
     * The initial rotation of the robot. All gyro angles are measured relative to this.
     */
    private double mInitialRotationDegrees;

    /**
     * Snobot's rotation in radians
     */
    private double mRadianRotation;

    /**
     * The total distance saved from the previous update
     */
    private double mLastDistance;

    /**
     * The instentanous velocity of the robot, calculated by the average distance traveled since the last update
     */
    private double mInstantVelocity;

    /**
     * The time of the last update, retrieved from a {@link Timer}
     */
    private double mLastTime;

    /**
     * Gyro-sensor that SnobotPosition uses to calculate direction
     */
    private final Gyro mGyroSensor;

    /**
     * Snobot's drive-train that SnobotPosition uses to get distance
     */
    private final IDriveTrain mDriveTrain;

    /**
     * Logger for recording data
     */
    private final Logger mLogger;

    /**
     * Timer, used to calculate time between calls to update
     */
    private final Timer mTimer;

    /**
     * Constructs a SnobotPosition object
     * 
     * @param aPositionX
     *            Snobot's initial position on the X-axis
     * @param aPositionY
     *            Snobot's initial position on the Y-axis
     * @param aRadianRotation
     *            Snobot's initial orientation
     * @param aGryroSensor
     *            Gyro object to calculate orientation
     * @param aDefaultMeasure
     *            Measure that is to be used by default
     */
    public SnobotPosition(Gyro aGyroSensor, IDriveTrain aDriveTrain, Logger aLogger)
    {
        this.mPositionX = 0;
        this.mPositionY = 0;
        this.mRadianRotation = 0;
        this.mInitialRotationDegrees = 0;
        this.mGyroSensor = aGyroSensor;
        this.mDriveTrain = aDriveTrain;
        this.mLogger = aLogger;

        mTimer = new Timer();
        mTimer.start();
    }

    /**
     * Gives Logger headers for entries: X-position, Y-position, Degrees, Distance
     */
    @Override
    public void init()
    {
        this.mLogger.addHeader("X-position");
        this.mLogger.addHeader("Y-position");
        this.mLogger.addHeader("Heading");
        this.mLogger.addHeader("InstantVel");

    }

    /**
     * Gives Logger entries: X-position, Y-position, Degrees, Distance
     */
    @Override
    public void updateLog()
    {
        this.mLogger.updateLogger(mPositionX);
        this.mLogger.updateLogger(mPositionY);
        this.mLogger.updateLogger(this.getSnobotDegrees());
    }

    /**
     * Calculates the direction Snobot is facing
     * 
     * @return The direction Snobot is facing in radians between 2PI and -2PI
     */
    private double calculateRotation()
    {
        double gyroDegrees = mInitialRotationDegrees + mGyroSensor.getAngle();
        while (gyroDegrees > 360)
        {
            gyroDegrees = gyroDegrees - 360;
        }

        while (gyroDegrees < -360)
        {
            gyroDegrees = gyroDegrees + 360;
        }
        return Math.toRadians(gyroDegrees);
    }

    /**
     * Updates member variables with numbers calculated via object methods
     */
    @Override
    public void update()
    {
        double distanceRight = mDriveTrain.calculateDistanceRight();
        double distanceLeft = mDriveTrain.calculateDistanceLeft();

        double total_distance = (distanceRight + distanceLeft) / 2;
        double delta_distance = total_distance - mLastDistance;

        double current_time = mTimer.get();
        double delta_time = current_time - mLastTime;

        mRadianRotation = calculateRotation();
        mPositionX += Math.sin(mRadianRotation) * delta_distance;
        mPositionY += Math.cos(mRadianRotation) * delta_distance;
        mInstantVelocity = delta_distance / delta_time;

        mLastDistance = total_distance;
        mLastTime = current_time;
    }

    @Override
    public double getSnobotDegrees()
    {
        return Math.toDegrees(mRadianRotation);
    }

    @Override
    public double getSnobotX()
    {
        return mPositionX;
    }

    @Override
    public double getSnobotY()
    {
        return mPositionY;
    }

    @Override
    public double getSnobotRadians()
    {
        return mRadianRotation;
    }

    @Override
    public double getTotalDistance()
    {
        return mLastDistance;
    }

    @Override
    public void setPosition(double aX, double aY, double aAngle)
    {
        setSnobotXPosition(aX);
        setSnobotYPosition(aY);
        setSnobotDegreeRotation(aAngle);

        mDriveTrain.resetEncoders();
        mLastDistance = 0;
    }

    @Override
    public void setSnobotXPosition(double aXPosition)
    {
        mPositionX = aXPosition;
    }

    @Override
    public void setSnobotYPosition(double aYPosition)
    {
        mPositionY = aYPosition;
    }

    @Override
    public void setSnobotDegreeRotation(double aDegrees)
    {
        mInitialRotationDegrees = aDegrees;
        mGyroSensor.reset();
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_HEADING, getSnobotDegrees());
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_X_POSITION, getSnobotX());
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_Y_POSITION, getSnobotY());
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_INSTANT_VELOCITY, mInstantVelocity);

    }

    // Unused functions..
    @Override
    public void control()
    {
    }

    @Override
    public void rereadPreferences()
    {
    }

    @Override
    public void stop()
    {
    }
}
