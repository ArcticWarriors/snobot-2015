
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick leftJoystick; 
    private Joystick rightJoystick; 
    private Joystick controller;
   
    
    public OI() {
        
       //Map Joysticks and Controller
       leftJoystick = new Joystick(RobotMap.leftJoystickChannel);
       rightJoystick = new Joystick(RobotMap.rightJoystickChannel);
       controller = new Joystick(RobotMap.controllerChannel);
  
       //Map Buttons to Controller Inputs
       
       JoystickButton catchOpenButton = new JoystickButton(controller, RobotMap.catchOpenButton);
       JoystickButton intakeInButton = new JoystickButton(controller, RobotMap.intakeInButton);
       //JoystickButton catchCloseButton = new JoystickButton(controller, RobotMap.catchCloseButton);
       JoystickButton shortShotButton = new JoystickButton(controller, RobotMap.shortShotButton);
       JoystickButton longShotButton = new JoystickButton(controller, RobotMap.longShotButton);
       // JoystickButton spinCWButton = new JoystickButton(controller, 4);
       
       //Map Buttons to Commands
       
       catchOpenButton.whileHeld(new OpenCatchCommand());
       intakeInButton.whileHeld(new IntakeInCommand());
       //catchCloseButton.whileHeld(new CloseCatchCommand());
       shortShotButton.whenPressed(new ShortShotCommand());
       longShotButton.whenPressed(new LongShotCommand());
       //spinCWButton.whileHeld(new SpinRollerCWCommand());
       
       
       
       
       
    }
    
    //Intake In/Out Joystick Value
    public double getLeftControllerPower(){
        return controller.getY();
    }
        
    
    //Tank Drive Joystick Values
    public double getLeftJoystickPower() {
        return -leftJoystick.getY();
    }
    
    public double getRightJoystickPower() {
        return -rightJoystick.getY();
    }
}

