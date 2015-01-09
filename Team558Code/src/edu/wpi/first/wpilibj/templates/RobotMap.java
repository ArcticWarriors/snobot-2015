package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Joysticks
    static int leftJoystickChannel = 1;
    static int rightJoystickChannel = 2;
    static int controllerChannel = 3;

    //Buttons
    public static int catchOpenButton = 1;
    public static int intakeInButton = 2;
    public static int catchCloseButton = 3;
    public static int shortShotButton = 5;
    public static int longShotButton = 6;

    // Drive Motors
    public static int leftFrontMotorChannel = 1;
    public static int rightFrontMotorChannel = 3;
    public static int leftRearMotorChannel = 2;
    public static int rightRearChannel = 4;

    //Other Motors
    public static int leftIntakeMotorChannel = 5;
    public static int rightIntakeMotorChannel = 6;

    //Solenoids
    public static int catcherSolenoidChannel1 = 0;
    public static int catcherSolenoidChannel2 = 1;
    public static int intakeSolenoidChannel1  = 2;
    public static int intakeSolenoidChannel2  = 3;
    public static int shooterSolenoid1Channel = 4;
    public static int shooterSolenoid2Channel = 5;
    public static int latchSolenoidChannel1   = 6;
    public static int latchSolenoidChannel2   = 7;


    //Digital Inputs
    //Analog Inputs
}
