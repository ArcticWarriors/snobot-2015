package org.usfirst.frc.team558.robot.subsystems;

import org.usfirst.frc.team558.robot.RobotMap;
import org.usfirst.frc.team558.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class ToteGripperSubsystem extends Subsystem {
    
	
	private static DoubleSolenoid toteGripperSolenoid = new DoubleSolenoid(RobotMap.toteGripperSolenoidChannel1, RobotMap.toteGripperSolenoidChannel2);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ToteActuateCommand());
    }
    
    public void ToteGripperActuate(int position){
    	if(position == 0 || position == 45 || position == 315){
    		toteGripperSolenoid.set(DoubleSolenoid.Value.kForward);
    	}
    	if(position == 135 || position == 180 || position == 225){
    		toteGripperSolenoid.set(DoubleSolenoid.Value.kReverse);
    	}
    	
    }
    
}

