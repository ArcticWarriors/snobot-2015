package com.snobot.drivetrain;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets up specific snobot drive train
 * 
 * @author Ayush/Ammar
 *
 */
public class SnobotDriveTrain implements IDriveTrain
{

    private SpeedController mSpeedControllerLeft;
    private SpeedController mSpeedControllerRight;
    private IDriverJoystick mDriverJoystick;
    private RobotDrive mRobotDrive;

    private Encoder mEncoderLeft;
    private Encoder mEncoderRight;

    private Logger mLogger;
    private UnitOfMeasure mDefaultMeasure;
    private double mRightEncDistance;
    private double mLeftEncDistance;

    
    
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
             Encoder aEncoderLeft, Encoder aEncoderRight, Logger aLogger)
    {
        mSpeedControllerLeft = aSpeedControllerLeft;
        mSpeedControllerRight = aSpeedControllerRight;
        mDriverJoystick = aDriverJoystick;
        mRobotDrive = new RobotDrive(mSpeedControllerLeft, mSpeedControllerRight);
        mDefaultMeasure = UnitOfMeasure.Feet;
        mEncoderLeft = aEncoderLeft;
        mEncoderRight = aEncoderRight;
        mLogger=aLogger;
        
        mRobotDrive.setSafetyEnabled(false); // TODO - PJ - probably not the safest thing for competition....
        mEncoderLeft.setReverseDirection(true); // TODO PJ - is this totally useless?
    }

    @Override
    public void init()
    {
        mLogger.addHeader("Left Drive Speed");
        mLogger.addHeader("Right Drive Speed");
        rereadPreferences();
    }

    @Override
    public void update()
    {
        mRightEncDistance = mEncoderLeft.getDistance();
        mLeftEncDistance = mEncoderRight.getDistance(); 
        mSpeedControllerLeft.get();
        mSpeedControllerRight.get();
        
    }

    @Override
    public void control()
    {
        
        if (IDriverJoystick.DriveMode.Tank == mDriverJoystick.getDriveMode())
        {
            double left = mDriverJoystick.getLeftY();
            double right = mDriverJoystick.getRightY();
            double chooChooTrain = 0.65;

            if (mDriverJoystick.getDriveForward())
            {
                mRobotDrive.tankDrive(chooChooTrain, chooChooTrain);
            }
            else if (mDriverJoystick.getDriveBackward())
            {
                mRobotDrive.tankDrive(-chooChooTrain, -chooChooTrain);
            }
            else
            {
                if (mDriverJoystick.isSmarturDriving())
                {
                    left *= 0.8;
                    right *= 0.8;
                }
                else
                {
                    if (left < 0 && right > 0)
                    {
                        left *= 1.0;
                        right *= 1.0;
                    }
                    else if (left > 0 && right < 00)
                    {
                        left *= 1.0;
                        right *= 1.0;
                    }
                    else
                    {
                        left *= 1.0;
                        right *= 1.0;
                    }
                }
                mRobotDrive.tankDrive(left, right, true);
            }
        }
        // else if (IDriverJoystick.DriveMode.Arcade ==
        // mDriverJoystick.getDriveMode())
        // {
        // mRobotDrive.arcadeDrive(mDriverJoystick.getSpeed(),
        // mDriverJoystick.getRotate());
        // }

    }

    @Override
    public void rereadPreferences()
    {
        mEncoderLeft.setDistancePerPulse(ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sLEFT_ENC_DPP, -0.0389483932));
        mEncoderRight.setDistancePerPulse(ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sRIGHT_ENC_DPP, 0.0389483932));
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sLEFT_DRIVE_SPEED, mSpeedControllerLeft.get());
        SmartDashboard.putNumber(SmartDashboardNames.sRIGHT_DRIVE_SPEED, mSpeedControllerRight.get());
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_DISTANCE_LEFT, mRightEncDistance);
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_DISTANCE_RIGHT, mLeftEncDistance);
        SmartDashboard.putNumber(SmartDashboardNames.sLEFT_ENC_RAW, mEncoderLeft.getRaw());
        SmartDashboard.putNumber(SmartDashboardNames.sRIGHT_ENC_RAW, mEncoderRight.getRaw());
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mSpeedControllerLeft.get());
        mLogger.updateLogger(mSpeedControllerRight.get());
    }

    @Override
    public void stop()
    {
        this.setMotorSpeed(0, 0);

    }

    @Override
    public void setMotorSpeed(double aLeft, double aRight)
    {
        mRobotDrive.setLeftRightMotorOutputs(aLeft, aRight);
    }

    @Override
    public double calculateDistanceRight()
    {
        return mLeftEncDistance;
    }

    @Override
    public double calculateDistanceLeft()
    {
        return mRightEncDistance;
    }

    @Override
    public void setDefaultMeasure(UnitOfMeasure aMeasure)
    {
        mDefaultMeasure = aMeasure;

    }
    
    public void resetEncoders()
    {
        this.mEncoderLeft.reset();
        this.mEncoderRight.reset();
    }
}
