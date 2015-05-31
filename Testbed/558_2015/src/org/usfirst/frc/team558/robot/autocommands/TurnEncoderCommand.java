package org.usfirst.frc.team558.robot.autocommands;

import org.usfirst.frc.team558.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnEncoderCommand extends Command {

	private double distance;
	private double speed;
	private double time;
	
    public TurnEncoderCommand(double distance, double speed, double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        this.distance = distance;
        this.speed = speed;
        this.time = time;
        
        setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.leftDriveEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(speed, -speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.drivetrain.GetLeftDriveEncoderValue()) > distance){
        	return true;
        }
        else {
        	return isTimedOut();
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
