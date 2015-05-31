package org.usfirst.frc.team558.robot.autocommands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team558.robot.Robot;

import org.usfirst.frc.team558.robot.autocommands.WhaleTailOutCommand;
import org.usfirst.frc.team558.robot.autocommands.TestEncoderCommand;
import org.usfirst.frc.team558.robot.autocommands.WhaleTailInCommand;
import org.usfirst.frc.team558.robot.autocommands.TestEncoderCommand;
import org.usfirst.frc.team558.robot.autocommands.DoNothingCommand;


/**
 *
 */
public class WhaleTail25558DirtyCommand extends CommandGroup
{

    public WhaleTail25558DirtyCommand()
    {

        // Add Commands here:

        addSequential(new WhaleTailOutCommand(.5));
        addSequential(new TestEncoderCommand(50, -.85, 3));
        addSequential(new WhaleTailInCommand(1.5));
        addSequential(new TestEncoderCommand(22, .57, 3));
        addSequential(new DoNothingCommand(1));

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