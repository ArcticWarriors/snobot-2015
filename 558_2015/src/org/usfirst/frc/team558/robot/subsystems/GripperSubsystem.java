package org.usfirst.frc.team558.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team558.robot.RobotMap;


/**
 *
 */
public class GripperSubsystem extends Subsystem {
    
	private Solenoid gripperSolenoid = new Solenoid(RobotMap.gripperSolenoidChannel);
	private static DoubleSolenoid whaleTailArmSolenoid = new DoubleSolenoid(RobotMap.whaleTailArmSolenoidChannel1, RobotMap.whaleTailArmSolenoidChannel2);
	private static DoubleSolenoid whaleTailFinSolenoid = new DoubleSolenoid(RobotMap.whaletailFinsSolenoidChannel1,RobotMap.whaletailFinsSolenoidChannel2);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void GripperIn(){
    	gripperSolenoid.set(true);
    }
    public void GripperOut(){
    	gripperSolenoid.set(false);
    }
    public void WhaleTailOut(){
    	whaleTailArmSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void WhaleTailIn(){
    	whaleTailArmSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public void WhaleTailFinsOut(){
    	whaleTailFinSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void WhaleTailFinsIn(){
    	whaleTailFinSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    
}

