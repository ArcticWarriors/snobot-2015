package org.usfirst.frc.team558.robot.autocommands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team558.robot.Robot;

import org.usfirst.frc.team558.robot.autocommands.TestEncoderCommand;
import org.usfirst.frc.team558.robot.autocommands.TestEncoderCommand;


/**
 *
 */
public class TestAutoEncoderCommand extends CommandGroup
{

    public TestAutoEncoderCommand()
    {

        // Add Commands here:

        addSequential(new TestEncoderCommand(.9, .4, 3));
        addSequential(new TestEncoderCommand(9.5, -.6, 3));

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