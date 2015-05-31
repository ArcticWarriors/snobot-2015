package org.usfirst.frc.team174.robot;

import org.usfirst.frc.team174.robot.DriverJoystick_Flightsticks.DriveMode;

public interface DriverJoystick {

	public abstract double getRight();

	public abstract double getLeft();

	public abstract double getSpeed();

	public abstract double getRotate();

	public abstract DriveMode getmode();

}