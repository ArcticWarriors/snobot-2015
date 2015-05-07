package org.usfirst.frc.team558.robot.autocommands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team558.robot.Robot;
import org.usfirst.frc.team558.robot.autocommands.CloseGripperCommand;
import org.usfirst.frc.team558.robot.autocommands.DriveBackwardsCommand;
import org.usfirst.frc.team558.robot.autocommands.DriveForwardCommand;
import org.usfirst.frc.team558.robot.autocommands.RaiseElevatorCommand;

/**
 *
 */
public class AutoCommand extends CommandGroup {
    
    public  AutoCommand() {
    	requires(Robot.drivetrain);
    	requires(Robot.elevator);
    	requires(Robot.gripper);
        // Add Commands here:
    	
    	
    	addSequential(new CloseGripperCommand());
    	addSequential(new DriveBackwardsCommand(1));
    	addSequential(new RaiseElevatorCommand(1.15));
        addSequential(new DriveForwardCommand(.85));
        
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
