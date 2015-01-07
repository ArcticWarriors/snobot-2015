
package org.usfirst.frc.team174.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	

	private SpeedController controllerA;
	private SpeedController controllerB;
	private Solenoid solenoid;
	private DigitalOutput digitalOut;
	private Relay relay;
	private Timer autonTimer;
	
	Joystick joystick = new Joystick(1);
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	controllerA = new Talon(0);
    	controllerB = new Talon(1);
    	solenoid = new Solenoid(2);
    	digitalOut = new DigitalOutput(7);
    	relay = new Relay(2);
    	autonTimer = new Timer();
    }
    
    public void autonomousInit()
    {
    	autonTimer.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	if(autonTimer.get() < 2)
    	{
    		controllerA.set(.5);
    		controllerB.set(0);
    		solenoid.set(false);
    		digitalOut.set(false);
    		relay.set(Value.kOff);
    	}
    	else if(autonTimer.get() < 4)
    	{
    		controllerA.set(0);
    		controllerB.set(-.7);
    		solenoid.set(false);
    		digitalOut.set(false);
    		relay.set(Value.kOff);
    	}
    	else if(autonTimer.get() < 6)
    	{
    		controllerA.set(0);
    		controllerB.set(0);
    		solenoid.set(true);
    		digitalOut.set(false);
    		relay.set(Value.kOff);
    	}
    	else if(autonTimer.get() < 8)
    	{
    		controllerA.set(0);
    		controllerB.set(0);
    		solenoid.set(false);
    		digitalOut.set(true);
    		relay.set(Value.kOff);
    	}
    	else if(autonTimer.get() < 10)
    	{
    		controllerA.set(0);
    		controllerB.set(0);
    		solenoid.set(false);
    		digitalOut.set(false);
    		relay.set(Value.kForward);
    	}
    	else
    	{
    		controllerA.set(0);
    		controllerB.set(0);
    		solenoid.set(false);
    		digitalOut.set(false);
    		relay.set(Value.kOff);
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        solenoid.set(joystick.getRawButton(4));
        digitalOut.set(joystick.getRawButton(5));

        if(joystick.getRawButton(1))
        {
        	relay.set(Value.kForward);
        }
        else if(joystick.getRawButton(2))
        {
        	relay.set(Value.kReverse);
        }
        else if(joystick.getRawButton(3))
        {
        	relay.set(Value.kOn);
        }
        else
        {
        	relay.set(Value.kOff);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
