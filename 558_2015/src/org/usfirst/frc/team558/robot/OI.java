package org.usfirst.frc.team558.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team558.robot.RobotMap;
import org.usfirst.frc.team558.robot.commands.*;


public class OI {
		
	  //Define each driver/operator controller
	  public Joystick driveController;
	  public Joystick driveController2;
	  public Joystick operatorController;

	  //Define Joystick values to be used
	  public double yvalue;
	  public double yvalue2;
	  public double liftvalueleft;
	  public double liftvalueright;
	  
	  //Joystick Values used for Halo Drive
	  //public double twist;
	  //public double throttle;
	  
	public OI() {
	
	//Instantiate the driver/operator controllers
    driveController = new Joystick(RobotMap.driveControllerChannel);
    driveController2 = new Joystick(RobotMap.driveController2Channel);
    operatorController = new Joystick(RobotMap.operatorControllerChannel);
    
    //Instantiate and define the buttons on the operator controller
    new JoystickButton(operatorController, RobotMap.gripperButton).toggleWhenPressed(new GripperActuateCommand());
    new JoystickButton(operatorController, RobotMap.whaleTailArmButton).toggleWhenPressed(new WhaleTailActuateCommand());
    new JoystickButton(operatorController, RobotMap.whaleTailFinButton).toggleWhenPressed(new WhaleTailFinsActuateCommand());
    
	}
	
	//Returns D-pad values on operator controller
	public int GetPOVValue(){
		return operatorController.getPOV();
	}
	
    //Returns left joystick values
    public double getLeftJoystickPower() {
        yvalue = driveController.getRawAxis(1);
        
// Commented code for Halo Drive
        
//        twist = driveController.getRawAxis(4);
//        throttle = driveController.getRawAxis(5);
//        if ((twist < .1) && (twist > -.1) && (throttle < .1) && (throttle > -.1) && (yvalue > -.1) && (yvalue < .1)) {
//           return 0;
//               }
//        else if ((twist > 0) && (throttle < .3)) {
//            return (yvalue);
//            
//        } else if ((twist < 0) && (throttle <.3)) {
//            return (((1 - (Math.abs(twist))) * (yvalue)));
//            
//        } else if (throttle >.9) {
//            return yvalue;
//            
//        } else {
            return yvalue;
//        }

    }

    //Returns right joystick values
    public double getRightJoystickPower() {
    	yvalue2 = driveController2.getRawAxis(1);

//  Commented code for Halo Drive   
//        twist = driveController.getRawAxis(4);
//        throttle = driveController.getRawAxis(5);
//       if ((twist < .1) && (twist > -.1) && (throttle < .1) && (throttle > -.1) && (yvalue > -.1) && (yvalue < .1)) {
//           return 0;
//       }
//       else if ((twist > 0) && (throttle < .3)) {
//            return (((1 - (Math.abs(twist))) * (yvalue)));
//
//        } else if ((twist < 0) && (throttle <.3)) {
//            return (yvalue);
//            
//        } else if (throttle >.9) {
//            return -yvalue;
//            
//        } else {
            return yvalue2;
//        }

    }
    
    //Returns trigger values on operator controller
    public double GetElevatorJoystickPower(){
    	liftvalueleft = operatorController.getRawAxis(2);
    	liftvalueright = operatorController.getRawAxis(3);
    	
    	if ((liftvalueleft > .1) && (liftvalueright > .1)){
    		return 0;
    	}
    	else if (liftvalueleft > .1){
    		return liftvalueleft;
    	}
    	else if (liftvalueright > .1){
    		return (-liftvalueright);
    	}
    	else{
    		return 0;
    	}
    }
	
	
}

