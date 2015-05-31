package org.usfirst.frc.team558.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team558.robot.Robot;

/**
 *
 */
public class JoystickDriveCommand extends Command {

	
    public JoystickDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(Robot.oi.getLeftJoystickPower(), Robot.oi.getRightJoystickPower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
