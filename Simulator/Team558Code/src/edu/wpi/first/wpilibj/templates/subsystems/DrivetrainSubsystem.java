
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.JoystickDriveCommand;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    
    private Victor leftFront = new Victor(RobotMap.leftFrontMotorChannel);
    private Victor rightFront = new Victor(RobotMap.rightFrontMotorChannel);
    private Victor leftRear = new Victor(RobotMap.leftRearMotorChannel);
    private Victor rightRear = new Victor(RobotMap.rightRearChannel);
    
    private RobotDrive robotDrive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);

    //Hacky making these public....
    public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
    public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB);
    
    public DrivetrainSubsystem()
    {
    	rightEncoder.setDistancePerPulse(1.0/255);
    	leftEncoder.setDistancePerPulse(1.0/255);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDriveCommand());
    }
    
    public void drive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }

    
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
        SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
        SmartDashboard.putNumber("Right Raw", rightEncoder.get());
        SmartDashboard.putNumber("Left Raw", leftEncoder.get());
    }
}

