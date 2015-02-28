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
        mSimplePathFollower = new SimplePathFollower(mListPoints, ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sKP, .1), ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sKFF, .5));
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
