package com.snobot.commands;

import java.util.List;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.simplePath.SimplePathFollower;
import com.snobot.xlib.simplePath.SimplePathPoint;

import edu.wpi.first.wpilibj.command.Command;

public class StraightSimplePath extends Command
{
    private IDriveTrain mDrivetrain;
    private SnobotPosition mSnobotPosition;
    private List<SimplePathPoint> mListPoints;
    private SimplePathFollower mSimplePathFollower;
    private double mStartingDistance;
    
    public StraightSimplePath(IDriveTrain aDrivetrain, SnobotPosition aSnobotPosition, List<SimplePathPoint> aListPoints)
    {
        mDrivetrain = aDrivetrain;
        mSnobotPosition = aSnobotPosition;
        mListPoints = aListPoints;

        double kP = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_PATH_KP, 0);
        double kD = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_PATH_KD, 0);
        double kVelocity = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_PATH_KV, .012);
        double kAccel = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sDRIVE_PATH_KA, 0);

        mSimplePathFollower = new SimplePathFollower(mListPoints, kP, kD, kVelocity, kAccel);
    }
    
    @Override
    protected void initialize()
    {
        mStartingDistance = mSnobotPosition.getTotalDistance();
        
    }

    @Override
    protected void execute()
    {
        double motorPower = mSimplePathFollower.calculate(mSnobotPosition.getTotalDistance() - mStartingDistance);
        mDrivetrain.setMotorSpeed(motorPower, motorPower);
        
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
