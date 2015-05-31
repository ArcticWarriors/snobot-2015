package org.usfirst.frc.team558.robot.autocommands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team558.robot.Robot;

import org.usfirst.frc.team558.robot.autocommands.WhaleTailFinsOutCommand;
import org.usfirst.frc.team558.robot.autocommands.DriveBackwardsCommand;
import org.usfirst.frc.team558.robot.autocommands.WhaleTailOutCommand;
import org.usfirst.frc.team558.robot.autocommands.DriveForwardCommand;
import org.usfirst.frc.team558.robot.autocommands.WhaleTailInCommand;


/**
 *
 */
public class WhaleTailAutoCommand extends CommandGroup
{

    public WhaleTailAutoCommand()
    {

        // Add Commands here:

        addSequential(new WhaleTailFinsOutCommand());
        addSequential(new DriveBackwardsCommand(0.65));
        addSequential(new WhaleTailOutCommand(2.5));
        addSequential(new DriveForwardCommand(0.7));
        addSequential(new WhaleTailInCommand(1.5));

        // addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        // addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}