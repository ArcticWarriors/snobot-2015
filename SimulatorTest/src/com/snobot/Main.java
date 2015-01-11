package com.snobot;

import javax.swing.SwingUtilities;

import com.snobot.simulator.gui.SimulatorFrame;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.templates.RobotDowneyJr;

public class Main {

    public static void main(String[] args)
    {
//    	RobotDowneyJr iter = new RobotDowneyJr();
//        Robot iter = new Robot();
    	
//    	Snobot iter = new Snobot();
    	RobotDowneyJr iter = new RobotDowneyJr();
//        Robot iter = new Robot();
//        Snobot iter = new Snobot();
    	
        
        NetworkTable.setIPAddress("127.0.0.1");

    	new Team558Simulator(iter);

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
