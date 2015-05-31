/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    private Solenoid shooterSolenoid1 = new Solenoid(RobotMap.shooterSolenoid1Channel);
    private Solenoid shooterSolenoid2 = new Solenoid(RobotMap.shooterSolenoid2Channel);
    private DoubleSolenoid latchSolenoid = new DoubleSolenoid(RobotMap.latchSolenoidChannel1, RobotMap.latchSolenoidChannel2);
// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void LatchDown() {
        latchSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void LatchUp() {
        latchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void FireShooter() {
        shooterSolenoid1.set(true);
        shooterSolenoid2.set(true);
    }

    public void RetractShooter() {
        shooterSolenoid1.set(false);
        shooterSolenoid2.set(false);
        
    }

    public void updateSmartDashboard()
    {
        SmartDashboard.putBoolean("Shooter Solenoid", shooterSolenoid1.get());
    }
}
