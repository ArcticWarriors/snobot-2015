package com.snobot.sd2015.spline_plotter;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SplinePlotterPanel extends JPanel
{
    private SplineWheelPlotter mLeftWheelPlotter;
    private SplineWheelPlotter mRightWheelPlotter;
    private HeadingPlotter mHeadingPlotter;

    public SplinePlotterPanel()
    {
        setLayout(new GridBagLayout());
        
        mLeftWheelPlotter = new SplineWheelPlotter("Left Wheel");
        mRightWheelPlotter = new SplineWheelPlotter("Right Wheel");
        mHeadingPlotter = new HeadingPlotter("Heading");
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mLeftWheelPlotter, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(mRightWheelPlotter, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(mHeadingPlotter, gbc);
        
    }

    public void setPath(List<SplineSegment> path_points)
    {
        List<Double> leftPos = new ArrayList<Double>();
        List<Double> leftVel = new ArrayList<Double>();
        List<Double> rightPos = new ArrayList<Double>();
        List<Double> rightVel = new ArrayList<Double>();
        List<Double> heading = new ArrayList<Double>();

        for (int i = 0; i < path_points.size(); ++i)
        {
            leftPos.add(path_points.get(i).left_pos);
            leftVel.add(path_points.get(i).left_vel);
            rightPos.add(path_points.get(i).right_pos);
            rightVel.add(path_points.get(i).right_vel);
            heading.add(path_points.get(i).heading);
        }

        mLeftWheelPlotter.setPath(leftPos, leftVel);
        mRightWheelPlotter.setPath(rightPos, rightVel);
        mHeadingPlotter.setPath(heading);
    }

    public void clearActuals()
    {
        mLeftWheelPlotter.clearActuals();
        mRightWheelPlotter.clearActuals();
        mHeadingPlotter.clearActuals();
    }

    public void setPoint(int index, SplineSegment splineSegment)
    {
        mLeftWheelPlotter.setPoint(index, splineSegment.left_pos, splineSegment.left_vel);
        mRightWheelPlotter.setPoint(index, splineSegment.right_pos, splineSegment.right_vel);
        mHeadingPlotter.setPoint(index, splineSegment.heading);
    }

}
