package com.snobot;

import javax.swing.SwingUtilities;

import com.snobot.simulator.gui.SimulatorFrame;
import com.snobot.simulator.sim.ISimulatorContainer;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {

//    	String class_name = "org.usfirst.frc.team174.robot.Snobot";
    	String class_name = "edu.wpi.first.wpilibj.templates.RobotDowneyJr";
    	
    	String simulator_classname = "com.snobot.Team558Simulator";
    	
        NetworkTable.setIPAddress("127.0.0.1");
        Preferences.__SetFileName(class_name + "_preferences.ini");
    	
    	RobotBase iter = (RobotBase) Class.forName(class_name).newInstance();
    	
    	if(simulator_classname != null)
    	{
    		ISimulatorContainer simulator = (ISimulatorContainer) Class.forName(simulator_classname).newInstance();
    		simulator.setRobot(iter);
    	}

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
