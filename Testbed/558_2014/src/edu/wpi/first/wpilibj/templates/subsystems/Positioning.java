package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Positioning {

	
	private double mX;
	private double mY;
	private double mAngle;
	private double mInitAngle;

	private double mLastRight;
	private double mLastLeft;
    private double wheelDiff = 0;
	
	private DrivetrainSubsystem mDrivetrain;
	private Gyro mGyro;
	
	public Positioning(DrivetrainSubsystem aDrivetrain, Gyro aGyro)
	{
		mX = mY = mInitAngle = 0;
		mDrivetrain = aDrivetrain;
		mGyro = aGyro;
	}
	
	public void setPosition(double x, double y, double angle)
	{
		mX = x;
		mY = y;
		mInitAngle = angle;
		mGyro.reset();
	}


    public void reset()
    {
        mDrivetrain.rightEncoder.reset();
        mDrivetrain.leftEncoder.reset();
        setPosition(0, 0, 0);
    }
	
	
	public void update()
	{

//		double d_right = mDrivetrain.rightEncoder.getDistance();
//		double d_left = mDrivetrain.leftEncoder.getDistance();
//		
//
//		mLastRight = mDrivetrain.rightEncoder.getDistance();
//		mLastLeft = mDrivetrain.leftEncoder.getDistance();

        //Current Distance
        double rightDist = mDrivetrain.rightEncoder.getDistance();
        double leftDist  = mDrivetrain.leftEncoder.getDistance();

        //Delta difference between measurements
        double dRight = rightDist - mLastRight;
        double dLeft = leftDist - mLastLeft;

        double dAvg = (dRight + dLeft)/2;
        


        mAngle = mInitAngle + (double)(leftDist - rightDist + wheelDiff)/(double)(3.14159*22.0/12.0)*(180.0);

        double radAng = Math.toRadians(mAngle);

        double cosA = Math.cos(radAng);
        double sinA = Math.sin(radAng);

        mX += sinA * dAvg;
        mY += cosA * dAvg;

        mLastRight = rightDist;
        mLastLeft = leftDist;
	}

    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber("Robot X", mX);
        SmartDashboard.putNumber("Robot Y", mY);
        SmartDashboard.putNumber("Angle", mAngle);
        SmartDashboard.putNumber("Angle2", mGyro.getAngle());
    }
}
