package com.snobot.position;

import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.logger.Logger;

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
	 * Snobot's drive-train that SnobotPosition uses to get distance
	 */
	private SnobotDriveTrain mDriveTrain;
	
	/**
	 * Logger for recording data
	 */
	private Logger mLogger;
	
	/**
	 * Constructs a SnobotPosition object
	 * @param aPositionX Snobot's initial position on the X-axis
	 * @param aPositionY Snobot's initial position on the Y-axis
	 * @param aRadianRotation Snobot's initial orientation
	 * @param aGryroSensor Gyro object to calculate orientation
	 * @param aDefaultMeasure Measure that is to be used by default
	 */
	public SnobotPosition(double aPositionX, double aPositionY, double aRadianRotation, 
			Gyro aGyroSensor, SnobotDriveTrain aDriveTrain, Logger aLogger) {
		this.mPositionX=aPositionX;
		this.mPositionY=aPositionY;
		this.mRadianRotation=aRadianRotation;
		
		// TODO Add Gyro declaration to com.snobot.Snobot and port to com.snobot.ConfigurationNames (ports 0-1 only)
		this.mGyroSensor=aGyroSensor;
		this.mDriveTrain=aDriveTrain;
		this.mLogger=aLogger;
	}

	/**
	 * Calculates Snobot's X-position with the opposite
	 * side of the field being north
	 * @return Snobot's X-position
	 */
	private double calculateX() {
		return Math.sin(this.mRadianRotation)*this.mDistanceTraveled+this.mPositionX;
	}

	/**
	 * Calculates Snobot's Y-position with the opposite
	 * side of the field being north
	 * @return Snobot's Y-position
	 */
	private double calculateY() {
		
		return Math.cos(this.mRadianRotation)*this.mDistanceTraveled+this.mPositionY;
	}

	/**
	 * Calculates the direction Snobot is facing
	 * @return The direction Snobot is facing in radians
	 * between 2PI and -2PI
	 */
	private double calculateDirection() {
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
	 * @return The distance traveled by Snobot since last update
	 */
	private double calculateDistanceTraveled() {
		double distanceRight=this.mDriveTrain.calculateDistanceRight();
		double distanceLeft=this.mDriveTrain.calculateDistanceLeft();
		
		return (distanceRight+distanceLeft)/2;
	}
	
	/**
	 * Updates member variables with numbers calculated
	 * via object methods
	 */
	public void updateAll(){
		this.mRadianRotation=this.calculateDirection();
		this.mDistanceTraveled=this.calculateDistanceTraveled();
		this.mPositionX=this.calculateX();
		this.mPositionY=this.calculateY();
	}
	
	/**
	 * Calculates the value of mRadianRotation in degrees
	 * @return mRadianRotation in human-friendly degrees
	 */
	public double getSnobotDegrees(){
		return Math.toDegrees(this.mRadianRotation);
	}
	
	/**
	 * Gets Snobot's X-position from mPositionX
	 * @return Snobot's X-position
	 */
	public double getSnobotX(){
		return this.mPositionX;
	}
	
	/**
	 * Gets Snobot's Y-position from mPositionY
	 * @return Snobot's Y-position
	 */
	public double getSnobotY(){
		return this.mPositionY;
	}
	
	/**
	 * Gets Snobot's radian rotation from mRadianRotation
	 * @return Snobot's rotation in radians
	 */
	public double getSnobotRadians(){
		return this.mRadianRotation;
	}
	
	/**
	 * Gets the distance traveled from mDistanceTraveled
	 * @return The distance traveled by Snobot since last update
	 */
	public double getSnobotDistance(){
		return this.mDistanceTraveled;
	}
	/**
	 * Gives Logger headers for entries: X-position, Y-position, Degrees, Distance
	 */
	public void giveHeaders(){
		this.mLogger.addHeader("Snobot X-position");
		this.mLogger.addHeader("Snobot's Y-position");
		this.mLogger.addHeader("Snobot's orientation in degrees");
		this.mLogger.addHeader("Distance traveled since last update");
		
	}
	/**
	 * Gives Logger entries: X-position, Y-position, Degrees, Distance
	 */
	public void giveEntry(){
		this.mLogger.updateLogger(mPositionX);
		this.mLogger.updateLogger(mPositionY);
		this.mLogger.updateLogger(this.getSnobotDegrees());
		this.mLogger.updateLogger(this.mDistanceTraveled);
	}
	
	

}
