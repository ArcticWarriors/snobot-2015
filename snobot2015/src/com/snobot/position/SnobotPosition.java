package com.snobot.position;

import edu.wpi.first.wpilibj.Gyro;

/**
 * Calculates Snobot's current position and
 * orientation with the side of the field
 * opposite drivers defined as north
 * @author Alec
 *
 */
public class SnobotPosition{
	// TODO Add SnobotPosition object declaration to com.snobot.Snobot
	
	/**
	 * Snobot's X-position
	 */
	private double mPositionX;
	
	/**
	 * Snobot's Y-position
	 */
	private double mPositionY;
	
	/**
	 * Snobot's rotation in radians
	 */
	private double mRadianRotation;
	
	/**
	 * Distance traveled by Snobot since last update
	 */
	private double mDistanceTraveled;
	
	/**
	 * Gyro-sensor that SnobotPosition uses to calculate direction
	 */
	private Gyro mGyroSensor;
	
	/**
	 * Constructs a SnobotPosition object
	 * @param aPositionX
	 * @param aPositionY
	 * @param aRadianRotation
	 */
	public SnobotPosition(double aPositionX, double aPositionY, double aRadianRotation, Gyro aGyroSensor) {
		mPositionX=aPositionX;
		mPositionY=aPositionY;
		mRadianRotation=aRadianRotation;
		
		// TODO Add Gyro declaration to com.snobot.Snobot and port to com.snobot.ConfigurationNames (0-1 only)
		mGyroSensor=aGyroSensor;
	}

	/**
	 * Returns Snobot's X-position
	 * @return
	 */
	public double getSnobotX() {
		return Math.sin(mRadianRotation)*mDistanceTraveled+mPositionX;
	}

	/**
	 * Returns Snobot's Y-position
	 * @return
	 */
	public double getSnobotY() {
		return Math.cos(mRadianRotation)*mDistanceTraveled;
	}

	/**
	 * Returns the direction Snobot is facing in radians
	 * @return
	 */
	public double getSnobotDirection() {
		double gyroDegrees=mGyroSensor.getAngle();
		if(gyroDegrees>360){
			gyroDegrees=gyroDegrees-360;;
		}
		
		else if(gyroDegrees<-360){
			gyroDegrees=gyroDegrees+360;
		}
		return Math.toRadians(gyroDegrees);
	}

	/**
	 * Returns the distance traveled by Snobot as the
	 * average of the left/right distances
	 * @return
	 */
	public double getDistanceTraveled() {
		double distanceRight;
		double distanceLeft;
		
		// TODO Code for getting wheel rotations goes here  (encoders needed)
		// Forward/backward must be considered
		// TODO distanceLeft/distanceRight are also declared here
		
		return (distanceRight+distanceLeft)/2;
	}
	
	/**
	 * Updates member variables with numbers calculated
	 * via object methods
	 */
	public void updateAll(){
		this.mRadianRotation=this.getSnobotDirection();
		this.mDistanceTraveled=this.getDistanceTraveled();
		this.mPositionX=this.getSnobotX();
		this.mPositionY=this.getSnobotY();
	}

}
