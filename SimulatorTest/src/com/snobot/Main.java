package com.snobot;

import javax.swing.SwingUtilities;

import org.usfirst.frc.team174.robot.Robot;

import com.snobot.simulator.gui.SimulatorFrame;

public class Main {

    public static void main(String[] args)
    {
        Robot iter = new Robot();

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
