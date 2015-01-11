package com.snobot;

import javax.swing.SwingUtilities;

import org.usfirst.frc.team174.robot.Snobot;
import org.usfirst.frc.team174.robot.TestRobot;

import com.snobot.simulator.gui.SimulatorFrame;

import edu.wpi.first.wpilibj.templates.RobotDowneyJr;

public class Main {

    public static void main(String[] args)
    {
//    	RobotDowneyJr iter = new RobotDowneyJr();
//        Robot iter = new Robot();
    	
    	Snobot iter = new Snobot();

        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
		        SimulatorFrame frame = new SimulatorFrame();
		        frame.pack();
		        frame.setVisible(true);
		        
		        frame.start(iter);
			}
		});
    }
}
