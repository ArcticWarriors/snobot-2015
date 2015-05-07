package org.usfirst.frc.team558.robot.autocommands.groups;

import org.usfirst.frc.team558.robot.Robot;
import org.usfirst.frc.team558.robot.autocommands.DoNothingCommand;
import org.usfirst.frc.team558.robot.autocommands.DriveBackwardsCommand;
import org.usfirst.frc.team558.robot.autocommands.TestEncoderCommand;
import org.usfirst.frc.team558.robot.autocommands.WhaleTailInCommand;
import org.usfirst.frc.team558.robot.autocommands.WhaleTailOutCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WhaleTailAutoEncoderCommand extends CommandGroup {
    
    public  WhaleTailAutoEncoderCommand() {
    	requires(Robot.gripper);
    	requires(Robot.drivetrain);
    	requires(Robot.elevator);
    
    	//Test Improvement sequential breakup of driving and dropping arm
//    	addSequential(new TestEncoderCommand(4.5 , .68, 3));
//    	addSequential(new WhaleTailOutCommand(.02));
//    	addSequential(new TestEncoderCommand(4.4 , .68, 3));
//    	addSequential(new TestEncoderCommand(50 , -1.0, 3));
//    	addSequential(new WhaleTailInCommand(1.5));
//    	addSequential(new TestEncoderCommand(22, .57, 3));
//    	addSequential(new DoNothingCommand(1));    	
    	
    	
    	//1.5 Working
    	addSequential(new TestEncoderCommand(9.0 , .68, 3));
        addParallel(new DriveBackwardsCommand(.9));
    	addSequential(new WhaleTailOutCommand(.9));
    	addSequential(new TestEncoderCommand(50 , -1.0, 1));
    	addSequential(new WhaleTailInCommand(1.5));
    	addSequential(new TestEncoderCommand(22, .57, 1));
    	addSequential(new DoNothingCommand(1));    	
//    	
    	//Qual Match 2
//    	addSequential(new TestEncoderCommand(9.2 , .68, 3));
//    	addSequential(new WhaleTailOutCommand(1));
//    	addSequential(new TestEncoderCommand(50 , -1.0, 3));
//    	addSequential(new WhaleTailInCommand(1.5));
//    	addSequential(new TestEncoderCommand(22, .57, 3));
//    	addSequential(new DoNothingCommand(1));    	
    	
    	// Qual Match 1
//    	addSequential(new TestEncoderCommand(9.2 , .68, 3));
//    	addSequential(new WhaleTailOutCommand(1.25));
//    	addSequential(new TestEncoderCommand(50 , -1.0, 3));
//    	addSequential(new WhaleTailInCommand(1.5));
//    	addSequential(new TestEncoderCommand(22, .57, 3));
//    	addSequential(new DoNothingCommand(1));
    	
    	//Practice Match 2
//    	addSequential(new TestEncoderCommand(9.5 , .68, 3));
//    	addSequential(new WhaleTailOutCommand(1.5));
//    	addSequential(new TestEncoderCommand(50 , -1.0, 3));
//    	addSequential(new WhaleTailInCommand(1.5));
//    	addSequential(new TestEncoderCommand(22, .57, 3));
//    	addSequential(new DoNothingCommand(1));
    	
//		Practice Match 1
//    	addSequential(new TestEncoderCommand(10 , .65, 3));
//    	addSequential(new WhaleTailOutCommand(2.0));
//    	addSequential(new TestEncoderCommand(50 , -1.0, 3));
//    	addSequential(new WhaleTailInCommand(1.5));
//    	addSequential(new TestEncoderCommand(22, .57, 3));
//    	addSequential(new DoNothingCommand(1));
//    	

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
