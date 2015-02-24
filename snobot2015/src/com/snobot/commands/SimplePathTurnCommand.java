package com.snobot.commands;

import java.util.List;

import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.simple_trajectory.SimplePathFollower;
import com.snobot.xlib.simple_trajectory.SimplePathPoint;

import edu.wpi.first.wpilibj.command.Command;

public class SimplePathTurnCommand extends Command
{

    private final SimplePathFollower pathFollower;

    private IDriveTrain mDrivetrain;
    private SnobotPosition mPositioner;

    public SimplePathTurnCommand(List<SimplePathPoint> aPath, IDriveTrain aDrivetrain, SnobotPosition aPositioner)
    {
        pathFollower = new SimplePathFollower();
        mDrivetrain = aDrivetrain;
        mPositioner = aPositioner;

        // TODO make config
        double kP = .01;
        double kI = 0;
        double kD = 0;
        double kVelocity = .00162;
        double kAccel = 0;

        pathFollower.configure(kP, kI, kD, kVelocity, kAccel);

        pathFollower.reset();

        pathFollower.setPath(aPath);
    }

    @Override
    protected void initialize()
    {
    }

    @Override
    protected void execute()
    {
        double speed = pathFollower.calculate(mPositioner.getSnobotDegrees());

        double out_left = speed;
        double out_right = -speed;

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
