package com.snobot;

import java.util.Date;

import com.snobot.claw.SnobotClaw;
import com.snobot.commands.CommandParser;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.IDriverJoystick.DriveMode;
import com.snobot.joystick.SnobotFlightstickJoystick;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.position.SnobotPosition;
import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import fake_java.text.SimpleDateFormat;
import fake_java.util.ArrayList;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot extends IterativeRobot
{

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
    private PowerDistributionPanel mPowerDistributionPanel;

    // Modules
    private SnobotStacker mStacker;
    private SnobotClaw mClaw;
    private SnobotDriveTrain mDriveTrain;
    private Logger mLogger;
    private SnobotPosition mPositioner;
    private String mAutonFilePath;

    private CommandParser mParser;

    // Solenoids
    private Solenoid mClawHandSolenoid;
    private Solenoid mClawArmSolenoid;

    // Motors
    private Talon mDriveLeft1;
    private Talon mDriveRight1;
    private Talon mStackerMotor;

    // Vector of iSubsystems for group actions
    private ArrayList mSubsystems;

    private AnalogInput mTransducer;
    private Encoder mEncoderLeft;
    private Encoder mEncoderRight;
    private AnalogChannel mStackerEncoder;

    private SimpleDateFormat sdf;
    private Command mAutonCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public void robotInit()
    {
        //Joysticks
    	int operator_joystick_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sOPERATOR_JOYSTICK_PORT, 1);
    	int driver_joystick_1_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_1_PORT, 0);
    	int driver_joystick_2_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 2);
    	
    	//PWM
    	int left_drive_motor_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_LEFT_1, 1);
    	int right_drive_motor_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_RIGHT_1, 2);
        int stacker_motor_port 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_MOTOR, 3);
        
        //Digital IO
    	int left_drive_enc_a 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_A, 1);
    	int left_drive_enc_b 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_B, 2);
    	int right_drive_enc_a 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_A, 3);
    	int right_drive_enc_b 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_B, 4);
        int stacker_upper_limit_sw 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_UPPER_LIMIT_SWITCH_PORT_1, 5);
        int stacker_lower_limit_sw 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_LOWER_LIMIT_SWITCH_PORT_1, 6);
        
        //Analog
        int stacker_pot_port 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_ENCODER_A, 1);
        int gyro_port 				= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sGYRO_SENSOR, 2);
        int transducer_port 		= 3;
        
        //Solenoid
        int claw_solenoid_port 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sCLAW_HAND_SOLENOID, 1);
        int arm_solenoid_port 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sCLAW_ARM_SOLENOID, 2);
        
        //Logger
        {
            sdf = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
            String headerDate = sdf.format(new Date());
            mLogger = new Logger(headerDate);
            mLogger.init();
        }
    	
    	//User IO
    	{
            String joystickType = ConfigurationNames.getOrSetPropertyString(SmartDashboardNames.sJoystickMode, SmartDashboardNames.sJOYSTICK_MODE_XBOX);

            mRawOperatorJoystick = new Joystick(operator_joystick_port);
            mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);


            if (joystickType.equals(SmartDashboardNames.sJOYSTICK_MODE_XBOX))
            {
                mRawDriverJoystickPrimary = new Joystick(driver_joystick_1_port);
                mDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystickPrimary, mLogger, mDriveMode);
            }
            else
            {
                mRawDriverJoystickPrimary = new Joystick(driver_joystick_1_port);
                mRawDriverJoystickSecondary = new Joystick(driver_joystick_2_port);
                mDriverJoystick = new SnobotFlightstickJoystick(mRawDriverJoystickPrimary, mRawDriverJoystickSecondary, mLogger);
            }
    	}
    	
    	//Drivetrain
    	{

	        mDriveLeft1 = new Talon(left_drive_motor_port);
	        mDriveRight1 = new Talon(right_drive_motor_port);

	        mEncoderLeft = new Encoder(left_drive_enc_a, left_drive_enc_b);
	        mEncoderRight = new Encoder(right_drive_enc_a, right_drive_enc_b);

	        mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mDriverJoystick, mDriveMode, mEncoderLeft, mEncoderRight, mLogger);
    	}
    	
    	//Stacker
    	{
            mUpperLimitSwitch = new DigitalInput(stacker_upper_limit_sw);
            mLowerLimitSwitch = new DigitalInput(stacker_lower_limit_sw);
            mStackerMotor = new Talon(stacker_motor_port);
            mStackerEncoder = new AnalogChannel(stacker_pot_port);
            
            mStacker = new SnobotStacker(mOperatorJoystick, mStackerMotor, mUpperLimitSwitch, mLowerLimitSwitch, mLogger, mStackerEncoder);
    	}
    	
    	//Claw
    	{
            mTransducer = new AnalogInput(transducer_port);
            mClawHandSolenoid = new Solenoid (claw_solenoid_port);
            mClawArmSolenoid = new Solenoid (arm_solenoid_port);
            
            mClaw = new SnobotClaw(mOperatorJoystick, mLogger, mTransducer, mClawHandSolenoid, mClawArmSolenoid );
    	}
    	
    	//Positioning
    	{
            mGyroSensor = new Gyro(gyro_port);
            mPositioner = new SnobotPosition(mGyroSensor, mDriveTrain, mLogger);
    	}
    	
    	
        mPowerDistributionPanel = new PowerDistributionPanel();
        mParser = new CommandParser(this);
        //TODO testing purposes only
        mAutonFilePath = new String("../../snobot2015/resources/autonoumous/TestAutonCommand.txt");

        mSubsystems = new ArrayList();
        mSubsystems.add(mOperatorJoystick);
        mSubsystems.add(mDriverJoystick);
        mSubsystems.add(mStacker);
        mSubsystems.add(mClaw);
        mSubsystems.add(mDriveTrain);
        mSubsystems.add(mPositioner);

        for (int i = 0; i < mSubsystems.size(); ++i)
        {
           ISubsystem iSubsystem = (ISubsystem) mSubsystems.get(i);
            iSubsystem.init();
        }
        mLogger.endHeader();

        ConfigurationNames.saveIfUpdated();
    }

    
    public void autonomousInit()
    {
    	mAutonCommand = mParser.readFile(mAutonFilePath);
        mAutonCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();

        update();
        updateSmartDashboard();
        updateLog();
        
    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic()
    {
        // System.out.println("Telop Period..." +
        // mRawDriverJoystickPrimary.getRawAxis(2));
        update();
        control();
        updateSmartDashboard();
        updateLog();
    }
    
    private void update()
    {
        for (int i = 0; i < mSubsystems.size(); ++i)
        {
           ISubsystem iSubsystem = (ISubsystem) mSubsystems.get(i);
            iSubsystem.update();

        }
    }
    
    private void control()
    {
        for (int i = 0; i < mSubsystems.size(); ++i)
        {
           ISubsystem iSubsystem = (ISubsystem) mSubsystems.get(i);
            iSubsystem.control();
        }
    }
    
    private void updateSmartDashboard()
    {
        mPositioner.updateSmartDashbaord();
        for (int i = 0; i < mSubsystems.size(); ++i)
        {
           ISubsystem iSubsystem = (ISubsystem) mSubsystems.get(i);
            iSubsystem.updateSmartDashboard();
        }
    }
    
    private void updateLog()
    {
        String logDate = sdf.format(new Date());
        if (mLogger.logNow())
        {
            mLogger.startLogEntry(logDate);
            
            mLogger.updateLogger(mPowerDistributionPanel.getVoltage());
            mLogger.updateLogger(mPowerDistributionPanel.getTotalCurrent());
             
        for (int i = 0; i < mSubsystems.size(); ++i)
        {
           ISubsystem iSubsystem = (ISubsystem) mSubsystems.get(i);
                iSubsystem.updateLog();
            }

            mLogger.endLogger();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    
    public void testPeriodic()
    {

    }

    
    public void teleopInit()
    {

    }

    public SnobotDriveTrain getDriveTrain()
    {
        return this.mDriveTrain;
    }

    public SnobotPosition getPositioner()
    {
        return this.mPositioner;
    }

    public SnobotStacker getSnobotStacker() 
    {
        return this.mStacker;
    }
    
    public SnobotClaw getSnobotClaw()
    {
        return this.mClaw;
    }

}
