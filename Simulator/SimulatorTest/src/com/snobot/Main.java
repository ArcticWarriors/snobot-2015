package com.snobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.SwingUtilities;

import com.snobot.simulator.RobotStateSingleton;
import com.snobot.simulator.gui.SimulatorFrame;
import com.snobot.simulator.sim.ISimulatorContainer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Timer.Interface;
import edu.wpi.first.wpilibj.internal.LocalHLUsageReporting;
import edu.wpi.first.wpilibj.internal.LocalHardwareTimer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Main {
	
	private static final String sPROPERTIES_FILE = "simulator_config.properties";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
    	String class_name = "com.snobot.Snobot";
    	String simulator_classname = "com.snobot.Snobot2015Simulator";
    	String simulator_config = "";

		Timer.SetImplementation(new LocalHardwareTimer());
		HLUsageReporting.SetImplementation(new LocalHLUsageReporting());
		RobotState.SetImplementation(DriverStation.getInstance());
		
    	try
    	{    		
    		Properties p = new Properties();
    		p.load(new FileInputStream(new File(sPROPERTIES_FILE)));
    		
    		class_name = p.getProperty("robot_class", class_name);
    		simulator_classname = p.getProperty("simulator_class", simulator_classname);
    		simulator_config = p.getProperty("simulator_config", simulator_config);
    		
    	}
    	catch(FileNotFoundException e)
    	{
    		System.err.println("Could not read properties file, will use defaults and will overwrite the file if it exists");

    		Properties p = new Properties();
    		
    		p.setProperty("robot_class", class_name);
    		p.setProperty("simulator_class", simulator_classname);
    		p.setProperty("simulator_config", simulator_config);
    		
    		try {
				p.store(new FileOutputStream(new File(sPROPERTIES_FILE)), "Creating default file");
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.err.println("Could not read properties file");
    	}
    	
    	
        NetworkTable.setIPAddress("127.0.0.1");
        Preferences.__SetFileName(class_name + "_preferences.ini");
    	
    	final RobotBase simulated_robot = (RobotBase) Class.forName(class_name).newInstance();
    	
    	if(simulator_classname != null && !simulator_classname.isEmpty())
    	{
    		final ISimulatorContainer simulator = (ISimulatorContainer) Class.forName(simulator_classname).newInstance();

        	RobotStateSingleton.get().addLoopListener(new RobotStateSingleton.LoopListener() {
    			
    			@Override
    			public void looped() {
    				simulator.looped();
    			}
    		});
    		
    		simulator.setConfigFile(simulator_config);
    	}

        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
		        SimulatorFrame frame = new SimulatorFrame();
		        frame.pack();
		        frame.setVisible(true);
		        
		        frame.start(simulated_robot);
			}
		});
    }
}
