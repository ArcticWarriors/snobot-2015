package com.snobot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.snobot.claw.SnobotClaw;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.IDriverJoystick.DriveMode;
import com.snobot.joystick.SnobotFlightstickJoystick;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.position.SnobotPosition;
import com.snobot.stacker.SnobotStacker;
import com.snobot.SmartDashboardNames;
import com.snobot.commands.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot extends IterativeRobot {

    // IO
    private Joystick mRawOperatorJoystick;
    private Joystick mRawDriverJoystickPrimary;
    private Joystick mRawDriverJoystickSecondary;
    private DigitalInput mUpperLimitSwitch;
    private DigitalInput mLowerLimitSwitch;
    private Gyro mGyroSensor;

    private SnobotOperatorJoystick mOperatorJoystick;
    private IDriverJoystick mDriverJoystick;

    private DriveMode mDriveMode;

    private SendableChooser mTankModeButtonChooser;
    private SendableChooser mArcadeModeButtonChooser;
    private int mTankModeButton;
    private int mArcadeModeButton;

    // Modules
    private SnobotStacker mStacker;
    private SnobotClaw mClaw;
    private SnobotDriveTrain mDriveTrain;
    private Logger mLogger;
    private SnobotPosition mPositioner;
    public CommandGroup mAutonCommands;

    // Motors
    private Talon mDriveLeft1;
    private Talon mDriveRight1;
    private Talon mStackerMotor;

    // Vector of iSubsystems for group actions
    private ArrayList<ISubsystem> mSubsystems;

    private AnalogInput mTransducer;
    private Encoder mEncoderLeft;
    private Encoder mEncoderRight;
    private Encoder mStackerEncoder;

    SimpleDateFormat sdf;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        mAutonCommands = new CommandGroup("Main executable CommandGroup");

        sdf = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
        String headerDate = sdf.format(new Date());
        mLogger = new Logger(headerDate);
        mLogger.init();
        mTransducer = new AnalogInput(1);

        mDriveLeft1 = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_LEFT_1, 0));
        mDriveRight1 = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_RIGHT_1, 1));
        mRawOperatorJoystick = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sOPERATOR_JOYSTICK_PORT, 1));

        mUpperLimitSwitch = new DigitalInput(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_UPPER_LIMIT_SWITCH_PORT_1, 1));
        mLowerLimitSwitch = new DigitalInput(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_LOWER_LIMIT_SWITCH_PORT_1, 2));

        mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
        mStackerMotor = new Talon(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_MOTOR, 2));
        mStacker = new SnobotStacker(mOperatorJoystick, mStackerMotor, mUpperLimitSwitch, mLowerLimitSwitch, mLogger);
        mClaw = new SnobotClaw(mOperatorJoystick, mLogger, mTransducer);

        // Various Button Chooser for mode changes
        mTankModeButtonChooser = new SendableChooser();
        mTankModeButtonChooser.addDefault("xboxButtonA", XboxButtonMap.A_BUTTON);
        mTankModeButtonChooser.addObject("xboxButtonB", XboxButtonMap.B_BUTTON);
        mTankModeButtonChooser.addObject("xboxButtonX", XboxButtonMap.X_BUTTON);
        mTankModeButtonChooser.addObject("xboxButtonY", XboxButtonMap.Y_BUTTON);
        mTankModeButtonChooser.addObject("xboxButtonLeftBumper", XboxButtonMap.LB_BUTTON);
        mTankModeButtonChooser.addObject("xboxButtonRightBumper", XboxButtonMap.RB_BUTTON);
        SmartDashboard.putData("Tank Mode Button Chooser", mTankModeButtonChooser);

        mArcadeModeButtonChooser = new SendableChooser();
        mArcadeModeButtonChooser.addDefault("xboxButtonB", XboxButtonMap.B_BUTTON);
        mArcadeModeButtonChooser.addObject("xboxButtonA", XboxButtonMap.A_BUTTON);
        mArcadeModeButtonChooser.addObject("xboxButtonX", XboxButtonMap.X_BUTTON);
        mArcadeModeButtonChooser.addObject("xboxButtonY", XboxButtonMap.Y_BUTTON);
        mArcadeModeButtonChooser.addObject("xboxButtonLeftBumper", XboxButtonMap.LB_BUTTON);
        mArcadeModeButtonChooser.addObject("xboxButtonRightBumper", XboxButtonMap.RB_BUTTON);
        SmartDashboard.putData("Arcade Mode Button Choose", mArcadeModeButtonChooser);

        String joystickType = ConfigurationNames.getOrSetPropertyString(SmartDashboardNames.sJoystickMode, SmartDashboardNames.sJoystickMode_Xbox);

        if (joystickType.equals(SmartDashboardNames.sJoystickMode_Xbox)) {
            mRawDriverJoystickPrimary = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_1_PORT, 0));
            mDriverJoystick = new SnobotXBoxDriverJoystick(mTankModeButton, mArcadeModeButton, mRawDriverJoystickPrimary, mLogger,
                    mTankModeButtonChooser, mArcadeModeButtonChooser, mDriveMode);
        }
        else {
            mRawDriverJoystickPrimary = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_1_PORT, 0));
            mRawDriverJoystickSecondary = new Joystick(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 0));
            mDriverJoystick = new SnobotFlightstickJoystick(mRawDriverJoystickPrimary, mRawDriverJoystickSecondary, mLogger);
        }

        mEncoderLeft = new Encoder(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_A, 7),
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_B, 4));

        mEncoderRight = new Encoder(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_A, 5),
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_B, 6));

        mStackerEncoder = new Encoder(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_ENCODER_A, 0),
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_ENCODER_B, 8));

        mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mDriverJoystick, mDriveMode, mEncoderLeft, mEncoderRight);

        mGyroSensor = new Gyro(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sGYRO_SENSOR, 0));

        mPositioner = new SnobotPosition(mGyroSensor, mDriveTrain, mLogger);

        mSubsystems = new ArrayList<ISubsystem>();
        mSubsystems.add(mOperatorJoystick);
        mSubsystems.add(mDriverJoystick);
        mSubsystems.add(mStacker);
        mSubsystems.add(mClaw);
        mSubsystems.add(mDriveTrain);

        for (ISubsystem iSubsystem : mSubsystems) {
            iSubsystem.init();
        }

        mLogger.endHeader();

        ConfigurationNames.saveIfUpdated();
    }

    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        String logDate = sdf.format(new Date());

        for (ISubsystem iSubsystem : mSubsystems) {
            iSubsystem.update();

        }
        for (ISubsystem iSubsystem : mSubsystems) {
            iSubsystem.control();
        }
        for (ISubsystem iSubsystem : mSubsystems) {
            iSubsystem.updateSmartDashboard();
        }

        if (mLogger.logNow()) {
            mLogger.startLogEntry(logDate);

            for (ISubsystem iSubsystem : mSubsystems) {
                iSubsystem.updateLog();
            }

            mLogger.endLogger();
        }

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }

    public void teleopInit() {

    }

    public SnobotDriveTrain getDriveTrain() {
        return this.mDriveTrain;
    }

    public SnobotPosition getPositioner() {
        return this.mPositioner;
    }

}
