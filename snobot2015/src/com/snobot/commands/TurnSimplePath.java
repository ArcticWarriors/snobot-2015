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

        double kP = ConfigurationNames.sTURN_PATH_KP.getValue();
        double kD = ConfigurationNames.sTURN_PATH_KD.getValue();
        double kVelocity = ConfigurationNames.sTURN_PATH_KV.getValue();
        double kAccel = ConfigurationNames.sTURN_PATH_KA.getValue();

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
