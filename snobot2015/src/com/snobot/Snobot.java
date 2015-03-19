package com.snobot;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.snobot.claw.IClaw;
import com.snobot.claw.SnobotClaw;
import com.snobot.commands.CommandParser;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.SnobotFlightstickJoystick;
import com.snobot.joystick.SnobotXBoxDriverJoystick;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;
import com.snobot.position.SnobotPosition;
import com.snobot.rake.IRake;
import com.snobot.rake.SnobotRake;
import com.snobot.stacker.IStacker;
import com.snobot.stacker.SnobotStacker;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

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

    private IOperatorJoystick mOperatorJoystick;
    private IDriverJoystick mDriverJoystick;

    // Modules
    private IStacker mStacker;
    private IClaw mClaw;
    private IDriveTrain mDriveTrain;
    private IRake mRake;
    private Logger mLogger;
    private SnobotPosition mPositioner;

    // Solenoids
    private Solenoid mClawHandSolenoid;
    private Solenoid mClawArmSolenoid;

    // Motors
    private SpeedController mDriveLeft1;
    private SpeedController mDriveRight1;
    private SpeedController mStackerMotor;
    private SpeedController mRakeMotor;
    

    //Digital Inputs
    private DigitalInput mUpperLimitSwitch;
    private DigitalInput mLowerLimitSwitch;
    private Encoder mEncoderLeft;
    private Encoder mEncoderRight;
    private DigitalInput mRakeLimitSwitch;
    
    //Analog Inputs
    private Gyro mGyroSensor;
    private AnalogInput mTransducer;
    private AnalogInput mStackerPot;

    // Vector of iSubsystems for group actions
    private ArrayList<ISubsystem> mSubsystems;

    private SimpleDateFormat sdf;
    private Command mAutonCommand;
    private SendableChooser mAutonChooser;
    private PowerDistributionPanel mPowerDistributionPanel;
    private CommandParser mParser;

   
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit()
    {
        SmartDashboard.putBoolean(SmartDashboardNames.sSAVE_REQUEST, false);

        // USBCamera camera = new USBCamera("cam0");
        // camera.setFPS(5);
        // camera.setBrightness(10);
        // camera.setExposureManual(50);
        //
        // CameraServer server = CameraServer.getInstance();
        // server.setQuality(10);
        // server.setSize(2);
        // server.startAutomaticCapture(camera);



        //Joysticks
    	int operator_joystick_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sOPERATOR_JOYSTICK_PORT,    1);
    	int driver_joystick_1_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_1_PORT, 0);
    	int driver_joystick_2_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVER_FLIGHTSTICK_2_PORT, 2);
    	
    	//PWM
    	int left_drive_motor_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_LEFT_1,  0);
    	int right_drive_motor_port 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_RIGHT_1, 1);
        int stacker_motor_port 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_MOTOR,       2);
        int rake_motor_port = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRAKE_MOTOR, 4);
        
        //Digital IO
    	int left_drive_enc_a 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_A, 7);
    	int left_drive_enc_b 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_B, 8);
    	int right_drive_enc_a 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_A, 5);
    	int right_drive_enc_b 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_B, 6);
        int stacker_upper_limit_sw 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_UPPER_LIMIT_SWITCH, 1);
        int stacker_lower_limit_sw 	= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_LOWER_LIMIT_SWITCH, 2);
        int rake_limit_switch_port = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRAKE_MOTOR_LS, 0);
        
        //Analog
        int transducer_port         = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sTRANSDUCER, 2);
        int stacker_pot_port 		= ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_POT, 1);
        int gyro_port               = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sGYRO_SENSOR, 0);
        
        //Solenoid
        int claw_solenoid_port = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sCLAW_HAND_SOLENOID, 2);
        int arm_solenoid_port = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sCLAW_ARM_SOLENOID, 1);

        String joystickType = ConfigurationNames.getOrSetPropertyString(SmartDashboardNames.sJOYSTICK_MODE, SmartDashboardNames.sJOYSTICK_MODE_XBOX);
        
        //Save these port mappings before you try to start the robot in case there are conflicts
        ConfigurationNames.saveIfUpdated();
    	
        mPowerDistributionPanel = new PowerDistributionPanel();
        
        sdf = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
        String headerDate = sdf.format(new Date());
        mLogger = new Logger(headerDate);
        
        ////////////////////////////////////////
        // User Interface
        ////////////////////////////////////////
        mRawOperatorJoystick = new Joystick(operator_joystick_port);
        mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
        
        if (joystickType.equals(SmartDashboardNames.sJOYSTICK_MODE_XBOX))
        {
            mRawDriverJoystickPrimary = new Joystick(driver_joystick_1_port);
            mDriverJoystick = new SnobotXBoxDriverJoystick(mRawDriverJoystickPrimary, mLogger);
        }
        else
        {
            mRawDriverJoystickPrimary = new Joystick(driver_joystick_1_port);
            mRawDriverJoystickSecondary = new Joystick(driver_joystick_2_port);
            mDriverJoystick = new SnobotFlightstickJoystick(mRawDriverJoystickPrimary, mRawDriverJoystickSecondary, mLogger);
        }
        
        //Rake
        mRakeMotor = new Talon(rake_motor_port);
        mRakeLimitSwitch = new DigitalInput(rake_limit_switch_port);
        mRake = new SnobotRake(mRakeMotor, mOperatorJoystick, mRakeLimitSwitch, mLogger);

        ////////////////////////////////////////
        // Drivetrain
        ////////////////////////////////////////
        mDriveLeft1 = new Talon(left_drive_motor_port);
        mDriveRight1 = new Talon(right_drive_motor_port);
        mEncoderLeft = new Encoder(left_drive_enc_a, left_drive_enc_b);
        mEncoderRight = new Encoder(right_drive_enc_a, right_drive_enc_b);
        mDriveTrain = new SnobotDriveTrain(mDriveLeft1, mDriveRight1, mDriverJoystick, mEncoderLeft, mEncoderRight, mLogger);
        
        ////////////////////////////////////////
        // Stacker
        ////////////////////////////////////////
        mUpperLimitSwitch = new DigitalInput(stacker_upper_limit_sw);
        mLowerLimitSwitch = new DigitalInput(stacker_lower_limit_sw);
        mStackerMotor = new Talon(stacker_motor_port);
        mStackerPot = new AnalogInput(stacker_pot_port);
        mStacker = new SnobotStacker(mOperatorJoystick, mStackerMotor, mUpperLimitSwitch, mLowerLimitSwitch, mLogger, mStackerPot);

        ////////////////////////////////////////
        // Claw
        ////////////////////////////////////////
        mClawHandSolenoid = new Solenoid (claw_solenoid_port);
        mClawArmSolenoid = new Solenoid (arm_solenoid_port);
        mTransducer = new AnalogInput(transducer_port);
        mClaw = new SnobotClaw(mOperatorJoystick, mLogger, mTransducer, mClawHandSolenoid, mClawArmSolenoid );

        ////////////////////////////////////////
        // Positioning
        ////////////////////////////////////////
        mGyroSensor = new Gyro(gyro_port);
        mPositioner = new SnobotPosition(mGyroSensor, mDriveTrain, mLogger);

        ////////////////////////////////////////
        // Auton Parser
        ////////////////////////////////////////
        mParser = new CommandParser(this);
        readAutoFiles();
        addSmartDashboardListeners();
        
        //Finish setup
        mSubsystems = new ArrayList<ISubsystem>();
        mSubsystems.add(mOperatorJoystick);
        mSubsystems.add(mDriverJoystick);
        mSubsystems.add(mStacker);
        mSubsystems.add(mClaw);
        mSubsystems.add(mDriveTrain);
        mSubsystems.add(mPositioner);
        mSubsystems.add(mRake);

        // TODO Combine for loops
        mLogger.init();
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.init();
        }
        mLogger.endHeader();

        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.rereadPreferences();
        }

        //Now all the preferences should be loaded, make sure they are all saved
        ConfigurationNames.saveIfUpdated();

        readFile();
    }
    
    private void readFile()
    {
        if (mAutonChooser.getSelected() != null)
        {
            mAutonCommand = mParser.readFile(mAutonChooser.getSelected().toString());
        }
        else
        {
            mAutonCommand = null;
        }
    }

    private void addSmartDashboardListeners()
    {
        mAutonChooser.getTable().addTableListener(new ITableListener() {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
                readFile();
            }
        });

        ITableListener saveModeListener = new ITableListener() {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
                if (SmartDashboard.getBoolean(SmartDashboardNames.sSAVE_REQUEST, false))
                {
                    mParser.saveAutonMode();
                    readFile();
                }
                else
                {
                    mAutonCommand = mParser.parseAutonString(SmartDashboard.getString(SmartDashboardNames.sSD_COMMAND_TEXT));
                }
            }
        };
        NetworkTable.getTable("SmartDashboard").addTableListener(SmartDashboardNames.sSD_COMMAND_TEXT, 
                saveModeListener, true);
    }

    @Override
    public void autonomousInit()
    {
        String auton_text = SmartDashboard.getString(SmartDashboardNames.sSD_COMMAND_TEXT, "THIS WILL BREAK IT");
        // mAutonCommand = mParser.parseAutonString(auton_text);

        // TODO This will break "send only" from the widget

        readFile();

    	if(mAutonCommand != null)
    	{
    	    mAutonCommand.start();
    	}
        else
        {
            System.out.println("Couldn't start auton command because it was null");
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
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
    @Override
    public void teleopPeriodic()
    {
        update();
        control();
        updateSmartDashboard();
        updateLog();
    }
    
    @Override
    public void disabledInit()
    {
        ConfigurationNames.saveIfUpdated();
    }
    
    private void update()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.update();

        }
    }
    
    private void control()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
            iSubsystem.control();
        }
    }
    
    private void updateSmartDashboard()
    {
        for (ISubsystem iSubsystem : mSubsystems)
        {
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
             
            for (ISubsystem iSubsystem : mSubsystems)
            {
                iSubsystem.updateLog();
            }

            mLogger.endLogger();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic()
    {

    }

    @Override
    public void teleopInit()
    {
        if(mAutonCommand != null)
        {
            mAutonCommand.cancel();
        }
    }

    public IDriveTrain getDriveTrain()
    {
        return this.mDriveTrain;
    }

    public SnobotPosition getPositioner()
    {
        return this.mPositioner;
    }

    public IStacker getSnobotStacker() 
    {
        return this.mStacker;
    }
    
    public IClaw getSnobotClaw()
    {
        return this.mClaw;
    }
    

    private void readAutoFiles()
    {
        mAutonChooser = new SendableChooser();
        
        File autonDr = new File(ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sAUTON_DIR, ConfigurationNames.sDEFAULT_AUTON_DIR));
        String autonIgnoreFilter = ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sAUTON_IGNORE_STRING, "");

        System.out.println("Reading auton files from directory " + autonDr.getAbsolutePath());
        System.out.println(" Using filter : \"" + autonIgnoreFilter + "\"");


        try
        {
            SnobotAutonCrawler fileProcessor = new SnobotAutonCrawler();
            Files.walkFileTree(Paths.get(autonDr.toURI()), fileProcessor);

            for (Path p : fileProcessor.mPaths)
            {
                mAutonChooser.addObject(p.getFileName().toString(), p.toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        SmartDashboard.putData("mAutonChooser", mAutonChooser );

    }

    private static final class SnobotAutonCrawler extends SimpleFileVisitor<Path>
    {
        private List<Path> mPaths;

        public SnobotAutonCrawler()
        {
            mPaths = new ArrayList<Path>();
        }

        @Override
        public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) throws IOException
        {
            System.out.println("  Keeping file " + aFile);
            mPaths.add(aFile);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path aDir, BasicFileAttributes aAttrs) throws IOException
        {
            Path dirName = aDir.getFileName();
            if (dirName.startsWith(ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sAUTON_IGNORE_STRING, "")))
            {
                System.out.println(" Skipping directory: " + dirName);
                return FileVisitResult.SKIP_SUBTREE;
            }
            else
            {
                System.out.println(" Processing directory: " + dirName);
                return FileVisitResult.CONTINUE;
            }
        }
    }
}
