/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    private final Compressor robotCompressor;
    private final Joystick joystick1;
    private final Joystick joystick2;
    
    private final Vector controllers = new Vector();
    
    public RobotTemplate()
    {
        robotCompressor = new Compressor(1,1);
        joystick1 = new Joystick(1);
        joystick2 = new Joystick(2);
        
        controllers.addElement(new MotorMode_Stick(new Talon(1) , "SC 1", joystick1, 1));
        controllers.addElement(new MotorMode_Stick(new Talon(2) , "SC 2", joystick2, 1));
        controllers.addElement(new SolenoidMode_Toggle(new Solenoid(1) , "SOL 1", joystick1, 1));
        
    }
    
    
    public void robotInit() {
        robotCompressor.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        Enumeration elements = controllers.elements();
        while(elements.hasMoreElements())
        {
            ControlMode controller = (ControlMode) elements.nextElement();
            controller.update();
        }
    }
    
}
