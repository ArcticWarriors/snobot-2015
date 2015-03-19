package com.snobot.sd.drivetrain;

import javax.swing.JFrame;

public class Main
{

    DrivetrainContainer mPanel = new DrivetrainContainer();

    public Main()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mPanel);

        frame.setVisible(true);
        frame.setSize(500, 350);

        rotate();
    }

    public final void rotate()
    {
        double angle = -45;
        double robotAngle = 45;
        while (true)
        {
            try
            {
                Thread.sleep(20);
                angle += 1;
                robotAngle += 1;
                double speed = (angle % 200) / 100.0 - 1;

                mPanel.updateWheelAngles(angle, angle, angle, angle);
                mPanel.updateSteeringSpeed(speed, speed, speed, speed);
                mPanel.updateRobotSpeed(angle % 100, angle % 100);
                mPanel.updateRobotAngle(robotAngle);
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
