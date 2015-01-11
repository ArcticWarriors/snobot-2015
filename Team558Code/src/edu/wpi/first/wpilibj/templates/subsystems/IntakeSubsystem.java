/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    
    private DoubleSolenoid catcherSolenoid1 = new DoubleSolenoid(RobotMap.catcherSolenoidChannel1, RobotMap.catcherSolenoidChannel2);

    private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(RobotMap.intakeSolenoidChannel1, RobotMap.intakeSolenoidChannel2);

    private Victor leftIntakeMotor = new Victor(RobotMap.leftIntakeMotorChannel);
    private Victor rightIntakeMotor = new Victor(RobotMap.rightIntakeMotorChannel);

    

// Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeOutCommand());
        //setDefaultCommand(new MySpecialCommand());
    }

    public void CatcherOut() {
        catcherSolenoid1.set(DoubleSolenoid.Value.kForward);
    }

    public void CatcherIn() {
        catcherSolenoid1.set(DoubleSolenoid.Value.kReverse);
    }

    public void IntakeIn() {
        intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        SmartDashboard.putBoolean("Harvester Frame In", false);
    }

    public void IntakeOut() {
        intakeSolenoid.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putBoolean("Harvester Frame In", true);
    }

    public void SetIntakeMotors(double left, double right) {
        leftIntakeMotor.set(-left);
        rightIntakeMotor.set(right);
        
        System.out.println("Intake speed : " + left);
        
        SmartDashboard.putNumber("Over Roller Motor Speed", left);
    }
}
