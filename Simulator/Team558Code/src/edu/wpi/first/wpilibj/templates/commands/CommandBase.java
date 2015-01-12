package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.ShooterSubsystem;



/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 */
public abstract class CommandBase extends Command {

    //Initalize OI, RobotMap, and Subsystems (Order matters here)
    public static RobotMap robotmap = new RobotMap();
    public static OI oi;
    public static DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    public static IntakeSubsystem intake = new IntakeSubsystem();
    public static ShooterSubsystem shooter = new ShooterSubsystem();

    
    public static void init() {
        oi = new OI();

    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
