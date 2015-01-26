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
	 * Unit of measure to return as the distance traveled
	 */
	public enum unitOfMeasure{Feet, Meters, Inches, Centimeters}
	
	/**
	 * Default unit of measure used by updateAll()
	 */
	private unitOfMeasure mDefaultMeasure;
	
	/**
	 * Constructs a SnobotPosition object
	 * @param aPositionX Snobot's initial position on the X-axis
	 * @param aPositionY Snobot's initial position on the Y-axis
	 * @param aRadianRotation Snobot's initial orientation
	 * @param aGryroSensor Gyro object to calculate orientation
	 * @param aDefaultMeasure Measure that is to be used by default
	 */
	public SnobotPosition(double aPositionX, double aPositionY, double aRadianRotation, 
			Gyro aGyroSensor, unitOfMeasure aDefaultMeasure) {
		mPositionX=aPositionX;
		mPositionY=aPositionY;
		mRadianRotation=aRadianRotation;
		
		// TODO Add Gyro declaration to com.snobot.Snobot and port to com.snobot.ConfigurationNames (0-1 only)
		mGyroSensor=aGyroSensor;
		
		mDefaultMeasure=aDefaultMeasure;
	}

	/**
	 * Calculates Snobot's X-position with the opposite
	 * side of the field being north
	 * @return Snobot's X-position
	 */
	public double getSnobotX() {
		return Math.sin(mRadianRotation)*mDistanceTraveled+mPositionX;
	}

	/**
	 * Calculates Snobot's Y-position with the opposite
	 * side of the field being north
	 * @return Snobot's Y-position
	 */
	public double getSnobotY() {
		return Math.cos(mRadianRotation)*mDistanceTraveled;
	}

	/**
	 * Calculates the direction Snobot is facing
	 * @return The direction Snobot is facing in radians
	 * between 2PI and -2PI
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
	 * Calculates the distance traveled by Snobot as the
	 * average of the left/right distances
	 * @return The distance traveled by Snobot
	 */
	public double getDistanceTraveled(unitOfMeasure aMeasure) {
		double distanceRight;
		double distanceLeft;
		
		// TODO Code for getting wheel rotations goes here  (encoders needed)
		// Forward/backward must be considered
		// TODO Add different sets of calculations for specific units of measure
		// TODO distanceLeft/distanceRight are also instantiated here
		
		return (distanceRight+distanceLeft)/2;
	}
	
	/**
	 * Updates member variables with numbers calculated
	 * via object methods
	 */
	public void updateAll(){
		this.mRadianRotation=this.getSnobotDirection();
		this.mDistanceTraveled=this.getDistanceTraveled(mDefaultMeasure);
		this.mPositionX=this.getSnobotX();
		this.mPositionY=this.getSnobotY();
	}
	
	/**
	 * Changes the default unit of measure to
	 * the one provided
	 * @param aNewMeasure The desired default measure
	 */
	public void setNewDefaultMeasure(unitOfMeasure aNewMeasure){
		mDefaultMeasure=aNewMeasure;
	}
	
	/**
	 * Calculates the value of mRadianRotation in degrees
	 * @return mRadianRotation in human-friendly degrees
	 */
	public double getSnobotDegrees(){
		return Math.toDegrees(mRadianRotation);
	}
	
	//TODO Add logger method(s)

}
