
package org.usfirst.frc.team558.robot;

import org.usfirst.frc.team558.robot.autocommands.DoNothingCommand;
import org.usfirst.frc.team558.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team558.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team558.robot.subsystems.GripperSubsystem;
import org.usfirst.frc.team558.robot.subsystems.ToteGripperSubsystem;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	//Start Camera Code
    int session;
    Image frame;
	//End Camera Code
    
	
	public static OI oi;
	public static DriveTrainSubsystem drivetrain;
	public static ElevatorSubsystem elevator;
	public static GripperSubsystem gripper;
	public static ToteGripperSubsystem totegripper;
	
	public SendableChooser autoChooser;
	public Command autonomousCommand;



    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	
    	//Start Camera Code
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
    	//End Camera Code
    	
		drivetrain = new DriveTrainSubsystem();
		elevator = new ElevatorSubsystem();
		gripper = new GripperSubsystem();
		totegripper = new ToteGripperSubsystem();
		
		oi = new OI();
		
		elevator.elevatorEncoder.setDistancePerPulse(RobotMap.elevatorEncoderConversion);
		drivetrain.leftDriveEncoder.setDistancePerPulse(RobotMap.driveEncoderConversion);
		drivetrain.rightDriveEncoder.setDistancePerPulse(RobotMap.driveEncoderConversion);
		
        
		autoChooser = new SendableChooser();
        // autoChooser.addDefault("Staying Clean Quick", new WhaleTailAutoEncoderCommand());
        // autoChooser.addObject("Riding Dirty Quick", new WhaleTailAutoDirtyCommand());
        // autoChooser.addObject("Staying Clean Slow", new WhaleTailAutoCleanSlowCommand());
        // autoChooser.addObject("Riding Dirty Slow", new WhaleTailAutoDirtySlowCommand());
        // autoChooser.addObject("Do Nothing", new DoNothingCommand(1));
        // autoChooser.addObject("Super Mega Ultra Fast Staying Clean Auto", new WhaleTailAutoSequentialArmDropCommand());
		autoChooser.addObject("Helen Keller Auto", new DoNothingCommand(1));
		
		
		SmartDashboard.putData("Auto Mode", autoChooser);

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
    	
    	elevator.elevatorEncoder.reset();
        drivetrain.leftDriveEncoder.reset();
        drivetrain.rightDriveEncoder.reset();

        
		autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Encoder Value", drivetrain.GetLeftDriveEncoderValue() );
        SmartDashboard.putNumber("Right Encoder Value", drivetrain.GetRightDriveEncoderValue() );
        
        //SmartDashboard.putNumber("Encoder Value", drivetrain.GetLeftDriveEncoderValue() );
        //SmartDashboard.putNumber("Encoder Rate", drivetrain.leftDriveEncoder.getRate() );
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        drivetrain.testUltrasonic.setEnabled(true);
        drivetrain.testUltrasonic.setAutomaticMode(true);
        
        elevator.elevatorEncoder.reset();
        drivetrain.leftDriveEncoder.reset();
        drivetrain.rightDriveEncoder.reset();
        
        //Start Camera Code
        NIVision.IMAQdxStartAcquisition(session);
        //End Camera Code
        
      
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	  
    	//Start Camera Code
    	NIVision.IMAQdxStopAcquisition(session);
    	//End Camera Code
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        
        SmartDashboard.putNumber("Left Encoder Value", drivetrain.GetLeftDriveEncoderValue() );
        SmartDashboard.putNumber("Right Encoder Value", drivetrain.GetRightDriveEncoderValue() );
        SmartDashboard.putNumber("Elevator Encoder Value", elevator.GetElevatorEncoderValue()); 
       
        SmartDashboard.putNumber("Encoder Rate", drivetrain.GetAverageEncoderRate() );
        
        SmartDashboard.putBoolean(" Top Limit Switch", elevator.TopLimitSwitchValue());
        SmartDashboard.putBoolean(" Bottom Limit Switch", elevator.BottomLimitSwitchValue());
       
        SmartDashboard.putNumber("High Pressure", drivetrain.GetHighPressureValue());
        
        
        //Start Camera Code
        NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);
        //End Camera Code
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
