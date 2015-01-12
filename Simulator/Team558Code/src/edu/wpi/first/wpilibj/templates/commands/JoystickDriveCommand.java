
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 */
public class JoystickDriveCommand extends CommandBase {

    public JoystickDriveCommand() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivetrain.drive(oi.getLeftJoystickPower(), oi.getRightJoystickPower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        drivetrain.drive(0, 0);
    }
}
