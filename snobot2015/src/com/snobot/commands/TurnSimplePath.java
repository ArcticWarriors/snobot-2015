package com.snobot.commands;

import java.util.List;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.simplePath.SimplePathFollower;
import com.snobot.xlib.simplePath.SimplePathPoint;

import edu.wpi.first.wpilibj.command.Command;

public class TurnSimplePath extends Command
{
    private IDriveTrain mDrivetrain;
    private SnobotPosition mSnobotPosition;
    private List<SimplePathPoint> mListPoints;
    private SimplePathFollower mSimplePathFollower;
    private double mStartingDegrees;
    
    public TurnSimplePath(IDriveTrain aDrivetrain, SnobotPosition aSnobotPosition, List<SimplePathPoint> aListPoints)
    {
        mDrivetrain = aDrivetrain;
        mSnobotPosition = aSnobotPosition;
        mListPoints = aListPoints;
        mSimplePathFollower = new SimplePathFollower(mListPoints, ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sKP, .1), ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sKFF, .5));
    }
    
    @Override
    protected void initialize()
    {
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
