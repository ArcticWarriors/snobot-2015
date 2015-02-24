package com.snobot.commands;

import java.util.List;

import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.Utilities;
import com.snobot.xlib.simple_trajectory.SimplePathFollower;
import com.snobot.xlib.simple_trajectory.SimplePathPoint;

import edu.wpi.first.wpilibj.command.Command;

public class SimplePathDriveCommand extends Command
{

    private final SimplePathFollower pathFollower;

    private IDriveTrain mDrivetrain;
    private SnobotPosition mPositioner;
    private double mKTurn;
    private double mStartDistance;

    public SimplePathDriveCommand(List<SimplePathPoint> aPath, IDriveTrain aDrivetrain, SnobotPosition aPositioner)
    {
        pathFollower = new SimplePathFollower();
        mDrivetrain = aDrivetrain;
        mPositioner = aPositioner;

        // TODO make config
        double kP = 0;
        double kI = 0;
        double kD = 0;
        double kVelocity = .12;
        double kAccel = 0;
        mKTurn = 0;

        pathFollower.configure(kP, kI, kD, kVelocity, kAccel);

        pathFollower.reset();

        pathFollower.setPath(aPath);
    }

    @Override
    protected void initialize()
    {
        mStartDistance = mPositioner.getTotalDistance();
    }

    @Override
    protected void execute()
    {
        double distance_travelled = mPositioner.getTotalDistance() - mStartDistance;
        double speed = pathFollower.calculate(distance_travelled / 12.0);

        double goalHeading = 0; // The simple path always drive straight
        double observedHeading = mPositioner.getSnobotRadians();

        double angleDiffRads = Utilities.getDifferenceInAngleRadians(observedHeading, goalHeading);
        double angleDiff = Math.toDegrees(angleDiffRads);

        double turn = mKTurn * angleDiff;

        double out_left = speed + turn;
        double out_right = speed - turn;

        mDrivetrain.setMotorSpeed(out_left, out_right);
    }

    @Override
    protected boolean isFinished()
    {
        return pathFollower.isFinishedTrajectory();
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
