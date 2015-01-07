/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;


/**
 * Provide access to the network communication data to / from the Driver Station.
 */
public class DriverStation {

    /**
     * Number of Joystick Ports
     */
    public static final int kJoystickPorts = 6;
	
    /**
     * The robot alliance that the robot is a part of
     */
    public enum Alliance { Red, Blue, Invalid }

    private static DriverStation instance = new DriverStation();

    private boolean m_userInDisabled = false;
    private boolean m_userInAutonomous = false;
    private boolean m_userInTest = false;
    
    private double mMatchTime;
    
    private static final double sWAIT_TIME = .02;
    
    /**
     * Gets an instance of the DriverStation
     *
     * @return The DriverStation.
     */
    public static DriverStation getInstance() {
        return DriverStation.instance;
    }

    /**
     * DriverStation constructor.
     *
     * The single DriverStation instance is created statically with the
     * instance static member variable.
     */
    protected DriverStation() {
    }

    /**
     * Kill the thread
     */
    public void release() {
    }

    /**
     * Wait for new data from the driver station.
     */
    public void waitForData() {
    	Timer.delay(sWAIT_TIME);
    	
    	if(!isDisabled())
    	{
    		mMatchTime += sWAIT_TIME;
    	}
    }

    /**
     * Copy data from the DS task for the user.
     * If no new data exists, it will just be returned, otherwise
     * the data will be copied from the DS polling loop.
     */
    protected synchronized void getData() {
    }

    /**
     * Read the battery voltage.
     *
     * @return The battery voltage in Volts.
     */
    public double getBatteryVoltage() {
        return 0;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the
     * robot to be enabled.
     *
     * @return True if the robot is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return !m_userInDisabled;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the
     * robot to be disabled.
     *
     * @return True if the robot should be disabled, false otherwise.
     */
    public boolean isDisabled() {
        return !isEnabled();
    }

    /**
     * Gets a value indicating whether the Driver Station requires the
     * robot to be running in autonomous mode.
     *
     * @return True if autonomous mode should be enabled, false otherwise.
     */
    public boolean isAutonomous() {
        return m_userInAutonomous;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the
     * robot to be running in test mode.
     * @return True if test mode should be enabled, false otherwise.
     */
    public boolean isTest() {
    	return m_userInTest;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the
     * robot to be running in operator-controlled mode.
     *
     * @return True if operator-controlled mode should be enabled, false otherwise.
     */
    public boolean isOperatorControl() {
        return !(isAutonomous() || isTest());
    }
	
	/**
     * Gets a value indicating whether the FPGA outputs are enabled. The outputs may be disabled
	 * if the robot is disabled or e-stopped, the watdhog has expired, or if the roboRIO browns out.
     *
     * @return True if the FPGA outputs are enabled.
     */
	public boolean isSysActive() {
		return true;
	}
	
	/**
     * Check if the system is browned out.
	 * 
     * @return True if the system is browned out
     */
	public boolean isBrownedOut() {
		return false;
	}

    /**
     * Has a new control packet from the driver station arrived since the last time this function was called?
     * @return True if the control data has been updated since the last call.
     */
    public synchronized boolean isNewControlData() {
		return true;
    }

    /**
     * Get the current alliance from the FMS
     * @return the current alliance
     */
    public Alliance getAlliance() {
        return Alliance.Red;
    }

    /**
     * Gets the location of the team's driver station controls.
     *
     * @return the location of the team's driver station controls: 1, 2, or 3
     */
    public int getLocation() {
        return 1;
    }

    /**
     * Is the driver station attached to a Field Management System?
     * Note: This does not work with the Blue DS.
     * @return True if the robot is competing on a field being controlled by a Field Management System
     */
    public boolean isFMSAttached() {
    	return false;
    }
	
	public boolean isDSAttached() {
		return true;
	}

	/**
	 * Return the approximate match time
	 * The FMS does not send an official match time to the robots, but does send an approximate match time.
	 * The value will count down the time remaining in the current period (auto or teleop).
	 * Warning: This is not an official time (so it cannot be used to dispute ref calls or guarantee that a function
	 * will trigger before the match ends)
	 * The Practice Match function of the DS approximates the behaviour seen on the field.
	 * @return Time remaining in current match period (auto or teleop) in seconds 
	 */
    public double getMatchTime() {
    	return mMatchTime;
    }
	
	/**
	 * Report error to Driver Station.
	 * Also prints error to System.err
	 * Optionally appends Stack trace to error message
	 * @param printTrace If true, append stack trace to error string
	 */
	public static void reportError(String error, boolean printTrace) {
		String errorString = error;
		if(printTrace) {
			errorString += " at ";
			StackTraceElement[] traces = Thread.currentThread().getStackTrace();
			for (int i=2; i<traces.length; i++)
			{
				errorString += traces[i].toString() + "\n";
			}
		}
		System.err.println(errorString);
	}

    /** Only to be used to tell the Driver Station what code you claim to be executing
     *   for diagnostic purposes only
     * @param entering If true, starting disabled code; if false, leaving disabled code */
    public void InDisabled(boolean entering) {
        m_userInDisabled=entering;
        mMatchTime = 0;
    }

    /** Only to be used to tell the Driver Station what code you claim to be executing
    *   for diagnostic purposes only
     * @param entering If true, starting autonomous code; if false, leaving autonomous code */
    public void InAutonomous(boolean entering) {
        m_userInAutonomous=entering;
        mMatchTime = 0;
    }

    /** Only to be used to tell the Driver Station what code you claim to be executing
     *   for diagnostic purposes only
     * @param entering If true, starting test code; if false, leaving test code */
    public void InTest(boolean entering) {
        m_userInTest = entering;
    }
}
