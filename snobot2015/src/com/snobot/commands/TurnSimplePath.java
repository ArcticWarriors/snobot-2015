package com.snobot.commands;

import java.util.List;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.path.SimplePathPoint;
import com.snobot.xlib.path.simple.SimplePathFollower;

import edu.wpi.first.wpilibj.command.Command;

public class TurnSimplePath extends Command
{
    private IDriveTrain mDrivetrain;
    private SnobotPosition mSnobotPosition;
    private List<SimplePathPoint> mListPoints;
    private SimplePathFollower mSimplePathFollower;
    private double mStartingDegrees;
    private double mHackFactor;
    
    public TurnSimplePath(IDriveTrain aDrivetrain, SnobotPosition aSnobotPosition, List<SimplePathPoint> aListPoints, double aHackFactor)
    {
        mDrivetrain = aDrivetrain;
        mSnobotPosition = aSnobotPosition;
        mListPoints = aListPoints;
        mHackFactor = aHackFactor;

        double kP = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sTURN_PATH_KP, 0.005);
        double kD = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sTURN_PATH_KD, 0);
        double kVelocity = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sTURN_PATH_KV, 0.0053);
        double kAccel = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sTURN_PATH_KA, 0.00174);

        kVelocity *= mHackFactor;

        mSimplePathFollower = new SimplePathFollower(mListPoints, kP, kD, kVelocity, kAccel);
    }
    
    @Override
    protected void initialize()
    {
        mSimplePathFollower.init();
        mStartingDegrees = mSnobotPosition.getSnobotDegrees();
        
    }

    @Override
    protected void execute()
    {
        double motorPower = mSimplePathFollower.calculate(mSnobotPosition.getSnobotDegrees() - mStartingDegrees);
        mDrivetrain.setMotorSpeed(motorPower,-motorPower);
        
    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return mSimplePathFollower.isFinished();
    }

    @Override
    protected void end()
    {
        mDrivetrain.stop();
        
    }

    @Override
    protected void interrupted()
    {

    }
    
}
