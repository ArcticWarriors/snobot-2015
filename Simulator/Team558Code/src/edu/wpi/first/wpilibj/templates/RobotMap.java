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

    //Buttons
    public static int catchOpenButton  = 3; //trianlge
    public static int catchCloseButton = 1; //x
    
    public static int intakeInButton   = 3;
    
    public static int shortShotButton  = 6; //square
    public static int longShotButton   = 2; //circle
    public static int harvestOut       = 4; //l1
    public static int harvestIn        = 5; //r1

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
    
    //Encoders
    public static int rightEncoderA = 1;
    public static int rightEncoderB = 2;
    public static int leftEncoderA  = 3;
    public static int leftEncoderB  = 4;


    //Digital Inputs
    //Analog Inputs
}
