package org.usfirst.frc.team558.robot.autocommands;

import org.usfirst.frc.team558.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WhaleTailAutoDirtyCommand extends CommandGroup {
    
    public  WhaleTailAutoDirtyCommand() {
    	requires(Robot.gripper);
    	requires(Robot.drivetrain);
    	requires(Robot.elevator);
    	
    	
    	
    	addSequential(new TestEncoderCommand(9.0 , .68, 3));
        addParallel(new DriveBackwardsCommand(.9));
    	addSequential(new WhaleTailOutCommand(.9));
    	addSequential(new TestEncoderCommand(50 , -.85, 1));
    	addSequential(new WhaleTailInCommand(1.5));
    	addSequential(new TestEncoderCommand(22, .57, 1));
    	addSequential(new DoNothingCommand(1));
    	//addSequential(new TurnEncoderCommand(50, -.9, 3));
    	
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
