package org.usfirst.frc.team558.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team558.robot.RobotMap;
import org.usfirst.frc.team558.robot.commands.MoveElevatorCommand;
/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
    
	private VictorSP leftElevator = new VictorSP(RobotMap.leftElevatorMotorChannel);
    private VictorSP rightElevator = new VictorSP(RobotMap.rightElevatorMotorChannel);
	private DigitalInput topLimitSwitch = new DigitalInput(RobotMap.topLimitSwitchChannel);
	private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.bottomLimitSwitchChannel);
	public Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderAChannel, RobotMap.elevatorEncoderBChannel);
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       setDefaultCommand(new MoveElevatorCommand());
    }
    public void SetElevatorMotors(double power){
    	if (!topLimitSwitch.get() && (power < 0)){
        	leftElevator.set(0);
        	rightElevator.set(0);
    	}
    	else if (!bottomLimitSwitch.get() && (power > 0)){
        	leftElevator.set(0);
        	rightElevator.set(0);
        	elevatorEncoder.reset();
    	}
    	else if (elevatorEncoder.getDistance() < 10){
    		leftElevator.set(-power * .6);
    		rightElevator.set(power * .6);
    	}
    	else if (elevatorEncoder.getDistance() > 50){
    		leftElevator.set(-power * .6);
    		rightElevator.set(power * .6);
    	}
    	else{
    		leftElevator.set(-power);
    		rightElevator.set(power);
    	}
    }
    public double GetElevatorEncoderValue(){
    	return elevatorEncoder.getDistance();
    }
    public double GetElevatorEncoderRate(){
    	return Math.abs(elevatorEncoder.getRate());
    }
    public boolean TopLimitSwitchValue(){
    	return topLimitSwitch.get();
    }
    public boolean BottomLimitSwitchValue(){
    	return bottomLimitSwitch.get();
    }
}

