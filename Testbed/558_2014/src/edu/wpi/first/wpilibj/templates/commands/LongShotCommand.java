/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Vandle
 */
public class LongShotCommand extends CommandBase {

    //double startTime1;

    public LongShotCommand() {
        requires(shooter);
        requires(intake);
        this.setTimeout(1.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(timeSinceInitialized() < 1)
    	{
            intake.IntakeOut();
    	}
    	else if(timeSinceInitialized() < 1.5)
    	{
            shooter.FireShooter();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.RetractShooter();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.RetractShooter();
        //shooter.LatchUp();
    }
}
