package com.snobot.commands;

import java.util.List;

import com.snobot.Properties2015;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.path.SimplePathPoint;
import com.snobot.xlib.path.simple.SimplePathFollower;

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

        double kP = Properties2015.sDRIVE_PATH_KP.getValue();
        double kD = Properties2015.sDRIVE_PATH_KD.getValue();
        double kVelocity = Properties2015.sDRIVE_PATH_KV.getValue();
        double kAccel = Properties2015.sDRIVE_PATH_KA.getValue();

        mSimplePathFollower = new SimplePathFollower(mListPoints, kP, kD, kVelocity, kAccel);
    }
    
    @Override
    protected void initialize()
    {
        mSimplePathFollower.init();
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
