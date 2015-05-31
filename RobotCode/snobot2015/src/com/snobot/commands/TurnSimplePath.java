package com.snobot.commands;

import java.util.List;

import com.snobot.Properties2015;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.path.SimplePathPoint;
import com.snobot.xlib.path.simple.SimplePathFollower;
import com.snobot.xlib.path.simple.SimplePathGenerator;

import edu.wpi.first.wpilibj.command.Command;

public class TurnSimplePath extends Command
{
    private IDriveTrain mDrivetrain;
    private SnobotPosition mSnobotPosition;
    private List<SimplePathPoint> mListPoints;
    private SimplePathFollower mSimplePathFollower;
    private double mStartingDegrees;
    private double mHackFactor;

    public TurnSimplePath(IDriveTrain aDrivetrain, SnobotPosition aSnobotPosition, double aMaxVelocity, double aMaxAccel, double aPosition, double aDt)
    {
        this(aDrivetrain, aSnobotPosition, new SimplePathGenerator().generate(aMaxVelocity, aMaxAccel, aPosition, aDt), 1);
    }

    public TurnSimplePath(IDriveTrain aDrivetrain, SnobotPosition aSnobotPosition, List<SimplePathPoint> aListPoints, double aHackFactor)
    {
        mDrivetrain = aDrivetrain;
        mSnobotPosition = aSnobotPosition;
        mListPoints = aListPoints;
        mHackFactor = aHackFactor;

        double kP = Properties2015.sTURN_PATH_KP.getValue();
        double kD = Properties2015.sTURN_PATH_KD.getValue();
        double kVelocity = Properties2015.sTURN_PATH_KV.getValue();
        double kAccel = Properties2015.sTURN_PATH_KA.getValue();

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
        mDrivetrain.setMotorSpeed(motorPower, -motorPower);

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
