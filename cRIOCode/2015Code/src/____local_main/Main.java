package ____local_main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.SwingUtilities;

import com.snobot.simulator.gui.SimulatorFrame;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Main {
	
	private static final String sPROPERTIES_FILE = "simulator_config.properties";
	private static final String sNETWORK_ERROR_MSG = "NetworkTable could not be initialized: java.net.BindException: Address already in use: JVM_Bind: Address already in use: JVM_Bind";

	private String class_name = "com.snobot.Snobot";
	private String simulator_classname = "com.snobot.Snobot2015Simulator";
	private RobotBase simulated_robot;
	
	
	private void startSimulator() throws Exception
	{
		try
		{
		    System.out.println("Creating " + class_name);
	        NetworkTable.setIPAddress("127.0.0.1");
	        Preferences.__SetFileName(class_name + "_preferences.ini");
	    	simulated_robot = (RobotBase) Class.forName(class_name).newInstance();
	    	
	    	if(simulator_classname != null && !simulator_classname.isEmpty())
	    	{	    	
	    	}
		}
		catch(RuntimeException e)
		{
			if(e.getMessage() != null && e.getMessage().equals(sNETWORK_ERROR_MSG))
			{
				throw new Exception("Could not start the NetworkTables, check if you have two simulator instances open");
			}
			else
			{
				System.out.println("Unexpected error.  The real code would say \"Robots don't quit\"");
				throw e;
			}
		}
		catch(UnsatisfiedLinkError e)
		{
			throw new Exception("Linking error, this is probably PJ's fault.  Come find me and yell at me. Error message:\n    " + e.getMessage());
		}
	}

    public static void main(String[] args) throws Exception
    {

//		Timer.SetImplementation(new HardwareTimer());
//		HLUsageReporting.SetImplementation(new HardwareHLUsageReporting());
//		RobotState.SetImplementation(DriverStation.getInstance());
		

		try
		{
			final Main main = new Main();
			main.startSimulator();
	    	
	        SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					
			        try
			        {
				        SimulatorFrame frame = new SimulatorFrame();
				        frame.pack();
				        frame.setVisible(true);
			        
			        	frame.start(main.simulated_robot);
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
						System.exit(-1);
			        }
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
    }
}
