package com.snobot.commands.path;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.snobot.Properties2015;
import com.snobot.SmartDashboardNames;
import com.snobot.drivetrain.IDriveTrain;
import com.snobot.position.IPositioner;
import com.snobot.xlib.Utilities;
import com.snobot.xlib.path.spline.TrajectoryFollower;
import com.team254.lib.trajectory.IdealSplineSerializer;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.SplineSegment;
import com.team254.lib.trajectory.Trajectory;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TrajectoryPathCommand extends Command
{
    private IDriveTrain mDrivetrain;
    private IPositioner mPositioner;
    private TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    private TrajectoryFollower followerRight = new TrajectoryFollower("right");
    private Path mPath;
    private double mStartingLeftDistance;
    private double mStartingRightDistance;
    private double mKTurn;

    private double mLastLeftDistance;
    private double mLastRightDistance;

    public TrajectoryPathCommand(IDriveTrain aDrivetrain, IPositioner aPositioner, Path aPath)
    {
        mDrivetrain = aDrivetrain;
        mPositioner = aPositioner;
        mPath = aPath;

        double kP = Properties2015.sDRIVE_PATH_KP.getValue();
        double kD = Properties2015.sDRIVE_PATH_KD.getValue();
        double kVelocity = Properties2015.sDRIVE_PATH_KV.getValue();
        double kAccel = Properties2015.sDRIVE_PATH_KA.getValue();

        mKTurn = Properties2015.sSPLINE_K_TURN.getValue();

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

        sendIdealPath();
    }

    @Override
    protected void execute()
    {

        double distanceL = mDrivetrain.calculateDistanceLeft() - mStartingLeftDistance;
        double distanceR = mDrivetrain.calculateDistanceRight() - mStartingRightDistance;

        double speedLeft = followerLeft.calculate(distanceL);
        double speedRight = followerRight.calculate(distanceR);

        double goalHeading = Math.toDegrees(followerLeft.getHeading());
        double observedHeading = mPositioner.getSnobotDegrees();

        double angleDiff = Utilities.getDifferenceInAngleDegrees(observedHeading, goalHeading);

        double turn = mKTurn * angleDiff;

        DecimalFormat df = new DecimalFormat("#.000");
        System.out
                .println("Turn - Current : " + df.format(observedHeading) + ", desired: " + df.format(goalHeading) + ", output: " + df.format(turn));
        System.out.println();

        mDrivetrain.setMotorSpeed(speedLeft + turn, speedRight - turn);

        SplineSegment segment = new SplineSegment();
        segment.left_pos = distanceL;
        segment.left_vel = (distanceL - mLastLeftDistance) / .02;
        segment.right_pos = distanceR;
        segment.right_vel = (distanceR - mLastRightDistance) / .02;
        segment.heading = observedHeading;

        String point_info = followerLeft.getCurrentSegment() + "," + IdealSplineSerializer.serializePathPoint(segment);

        SmartDashboard.putString(SmartDashboardNames.sSPLINE_POINT, point_info);

        mLastLeftDistance = distanceL;
        mLastRightDistance = distanceR;
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

    private void sendIdealPath()
    {
        List<SplineSegment> segments = new ArrayList<SplineSegment>();

        Trajectory left = mPath.getLeftWheelTrajectory();
        Trajectory right = mPath.getRightWheelTrajectory();

        for (int i = 0; i < left.size(); ++i)
        {
            SplineSegment segment = new SplineSegment();
            segment.left_pos = left.get(i).pos;
            segment.left_vel = left.get(i).vel;
            segment.right_pos = right.get(i).pos;
            segment.right_vel = right.get(i).vel;
            segment.heading = right.get(i).heading;

            segments.add(segment);
        }

        SmartDashboard.putString(SmartDashboardNames.sSPINE_IDEAL, IdealSplineSerializer.serializePath(segments));
    }

}
