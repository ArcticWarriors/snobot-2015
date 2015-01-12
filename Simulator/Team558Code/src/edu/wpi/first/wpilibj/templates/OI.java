
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick drivingJoystick; 
    private Joystick controller;
   
    
    public OI() {
        
       //Map Joysticks and Controller
       drivingJoystick = new Joystick(RobotMap.leftJoystickChannel);
  
       //Map Buttons to Controller Inputs
       
       JoystickButton catchOpenButton = new JoystickButton(drivingJoystick, RobotMap.catchOpenButton);
       JoystickButton intakeInButton  = new JoystickButton(drivingJoystick, RobotMap.intakeInButton);
       JoystickButton shortShotButton = new JoystickButton(drivingJoystick, RobotMap.shortShotButton);
       JoystickButton longShotButton  = new JoystickButton(drivingJoystick, RobotMap.longShotButton);
       
       //Map Buttons to Commands
       
       catchOpenButton.whileHeld(new OpenCatchCommand());
       intakeInButton.whileHeld(new IntakeInCommand());
       shortShotButton.whenPressed(new ShortShotCommand());
       longShotButton.whenPressed(new LongShotCommand());
    }
    
    //Intake In/Out Joystick Value
    public double getLeftControllerPower(){
    	if(drivingJoystick.getRawButton(RobotMap.harvestIn))
    	{
    		return 1;
    	}
    	else if(drivingJoystick.getRawButton(RobotMap.harvestOut))
    	{
    		return -1;
    	}
    	else
    	{
    		return 0;
    	}
    }

    //Tank Drive Joystick Values
    public double getLeftJoystickPower() {
        return drivingJoystick.getY();
    }
    
    public double getRightJoystickPower() {
        return drivingJoystick.getRawAxis(5);
    }
}

