package org.usfirst.frc.team558.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.AnalogInput;

import org.usfirst.frc.team558.robot.RobotMap;
import org.usfirst.frc.team558.robot.commands.JoystickDriveCommand;




/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
	
	private Victor leftFront = new Victor(RobotMap.leftFrontMotorChannel);
    private Victor rightFront = new Victor(RobotMap.rightFrontMotorChannel);
    private Victor leftRear = new Victor(RobotMap.leftRearMotorChannel);
    private Victor rightRear = new Victor(RobotMap.rightRearChannel);
    
    private RobotDrive robotDrive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
    
    public Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderAChannel,RobotMap.leftDriveEncoderBChannel);
    public Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderAChannel,RobotMap.rightDriveEncoderBChannel);
    
    //public AnalogInput loPressure = new AnalogInput(RobotMap.loPressureSensorChannel);
    public AnalogInput highPressure = new AnalogInput(RobotMap.highPressureSensorChannel);
    
    public  Ultrasonic testUltrasonic = new Ultrasonic(2,3);

    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDriveCommand());
    }
    
    public void drive(double left, double right) {
        robotDrive.tankDrive(-left, -right);
    }
    
    public double GetUltrasonicValue() {
    	return testUltrasonic.getRangeInches();
    }
    
    
    public double GetAverageEncoderRate(){
    	return (((Math.abs(leftDriveEncoder.getRate()))+(Math.abs(rightDriveEncoder.getRate())))/2);
    }
    public double GetLeftDriveEncoderValue(){
    	return leftDriveEncoder.getDistance();
    }
    public double GetRightDriveEncoderValue(){
    	return rightDriveEncoder.getDistance();
    }
    
    
    
   // public double GetLoPressureValue(){
   // 	return Math.round(((loPressure.getVoltage())*37.5)-18.75);
   // }
    public double GetHighPressureValue(){
    	return Math.round(((highPressure.getVoltage())*37.5)-18.75);
    }
}

