/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Vandle
 */
public class IntakeOutCommand extends CommandBase {

    public IntakeOutCommand() {
        requires(intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (oi.getLeftControllerPower() < -.25) {
            intake.IntakeOut();
            intake.SetIntakeMotors(-1,-1);
        } else if (oi.getLeftControllerPower() > .25) {
            intake.SetIntakeMotors(1, 1);
        } else {
            intake.SetIntakeMotors(0, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        intake.SetIntakeMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
