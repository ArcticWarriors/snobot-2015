package org.usfirst.frc.team558.robot.autocommands;

import org.usfirst.frc.team558.robot.Robot;
import org.usfirst.frc.team558.robot.autocommands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WhaleTailAutoCommand extends CommandGroup {
    
	//Encoder Distance to Whale tail out 1.005
	// Encoder distance to auto zone 7.315 - 12.335
	
    public  WhaleTailAutoCommand() {
    	requires(Robot.gripper);
    	requires(Robot.drivetrain);
    	requires(Robot.elevator);
    	
    	addSequential(new WhaleTailFinsOutCommand());
    	addSequential(new DriveBackwardsCommand(0.65));
    	addSequential(new WhaleTailOutCommand(2.5));
    	addSequential(new DriveForwardCommand(0.7));
    	addSequential(new WhaleTailInCommand(1.5));
    	
    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
