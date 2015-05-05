package org.usfirst.frc.team558.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    // Joysticks
    static int driveControllerChannel = 0;
    static int driveController2Channel = 1;
    static int operatorControllerChannel = 2;
    
    //Buttons
    public static int gripperButton = 1;
    public static int lazyManHookButton = 3;
    public static int whaleTailArmButton = 5;
    public static int whaleTailFinButton = 6;
	
    // Drive Motors
    public static int leftFrontMotorChannel = 2;
    public static int rightFrontMotorChannel = 3;
    public static int leftRearMotorChannel = 0;
    public static int rightRearChannel = 1;
    
    
    // Elevator Motors
    public static int leftElevatorMotorChannel = 4;
    public static int rightElevatorMotorChannel = 5;
    
    //Cheesecake Motors
   // public static int leftCheesecakeMotorChannel = 8;
   // public static int rightCheesecakeMotorChannel = 9;
    
    //Solenoids
    public static int gripperSolenoidChannel = 0;
    public static int toteGripperSolenoidChannel1 = 2;
    public static int toteGripperSolenoidChannel2 = 3;
    public static int whaleTailArmSolenoidChannel1 = 4;
    public static int whaleTailArmSolenoidChannel2 = 5;
    public static int whaletailFinsSolenoidChannel1 = 6;
    public static int whaletailFinsSolenoidChannel2 = 7;
    
    
    // Digital Input/Outputs
    
    
    
    //Limit Switches
    public static int topLimitSwitchChannel = 0;
    public static int bottomLimitSwitchChannel = 1;
    
    //Ultrasonics
    // RC angled dio 2 to input, dio 3 to output
    // dio mxp 4 input, dio mxp 5 output
    
    //Encoders
    public static double elevatorEncoderConversion = .02454; // inches per count
    public static double driveEncoderConversion = .147262; // inches per count left inverted
    
    // 32.5 measured ---> 31.5 encoder reading
    
    public static int elevatorEncoderAChannel = 8;
    public static int elevatorEncoderBChannel = 9;
    public static int leftDriveEncoderAChannel = 6;
    public static int leftDriveEncoderBChannel = 7;
    public static int rightDriveEncoderAChannel = 4;
    public static int rightDriveEncoderBChannel = 5;
    
   
    // Analog Input
    public static int loPressureSensorChannel = 0;
    public static int highPressureSensorChannel = 1;
    
	
}
