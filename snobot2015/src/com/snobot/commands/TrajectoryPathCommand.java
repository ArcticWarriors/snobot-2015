package com.snobot.commands;

import java.text.DecimalFormat;

import com.snobot.ConfigurationNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.SnobotPosition;
import com.snobot.xlib.Utilities;
import com.snobot.xlib.path.spline.TrajectoryFollower;
import com.team254.lib.trajectory.Path;

import edu.wpi.first.wpilibj.command.Command;

public class TrajectoryPathCommand extends Command
{
    private IDriveTrain mDrivetrain;
    private SnobotPosition mSnobotPosition;
    private TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    private TrajectoryFollower followerRight = new TrajectoryFollower("right");
    private double mStartingLeftDistance;
    private double mStartingRightDistance;
    private double mKTurn;

    public TrajectoryPathCommand(IDriveTrain aDrivetrain, SnobotPosition aSnobotPosition, Path aPath)
    {
        mDrivetrain = aDrivetrain;
        mSnobotPosition = aSnobotPosition;

        double kP = ConfigurationNames.sDRIVE_PATH_KP.getValue();
        double kD = ConfigurationNames.sDRIVE_PATH_KD.getValue();
        double kVelocity = ConfigurationNames.sDRIVE_PATH_KV.getValue();
        double kAccel = ConfigurationNames.sDRIVE_PATH_KA.getValue();

        mKTurn = ConfigurationNames.sSPLINE_K_TURN.getValue();

        followerLeft.configure(kP, 0, kD, kVelocity, kAccel);
        followerRight.configure(kP, 0, kD, kVelocity, kAccel);

        followerLeft.reset();
        followerRight.reset();

        followerLeft.setTrajectory(aPath.getLeftWheelTrajectory());
        followerRight.setTrajectory(aPath.getRightWheelTrajectory());
    }

    @Override
    protected void initialize()
    {
        mStartingLeftDistance = mDrivetrain.calculateDistanceLeft();
        mStartingRightDistance = mDrivetrain.calculateDistanceRight();
    }

    @Override
    protected void execute()
    {

        double distanceL = mDrivetrain.calculateDistanceLeft() - mStartingLeftDistance;
        double distanceR = mDrivetrain.calculateDistanceRight() - mStartingRightDistance;

        double speedLeft = followerLeft.calculate(distanceL);
        double speedRight = followerRight.calculate(distanceR);

        double goalHeading = Math.toDegrees(followerLeft.getHeading());
        double observedHeading = mSnobotPosition.getSnobotDegrees();

        double angleDiff = Utilities.getDifferenceInAngleDegrees(observedHeading, goalHeading);

        double turn = mKTurn * angleDiff;

        DecimalFormat df = new DecimalFormat("#.000");
        System.out
                .println("Turn - Current : " + df.format(observedHeading) + ", desired: " + df.format(goalHeading) + ", output: " + df.format(turn));
        System.out.println();

        mDrivetrain.setMotorSpeed(speedLeft + turn, speedRight - turn);

    }

    @Override
    protected boolean isFinished()
    {
        return followerLeft.isFinishedTrajectory();
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
