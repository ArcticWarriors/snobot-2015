package com.snobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import edu.wpi.first.wpilibj.internal.HardwareHLUsageReporting;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Main
{

    private static final String sPROPERTIES_FILE = "simulator_config.properties";
    private static final String sNETWORK_ERROR_MSG = "NetworkTable could not be initialized: java.net.BindException: Address already in use: JVM_Bind: Address already in use: JVM_Bind";

    private String class_name = "com.snobot.Snobot";
    private String simulator_classname = "";
    private String simulator_config = "";
    private RobotBase simulated_robot;

    private void loadConfigFile()
    {
        try
        {
            Properties p = new Properties();
            p.load(new FileInputStream(new File(sPROPERTIES_FILE)));

            class_name = p.getProperty("robot_class", class_name);
            simulator_classname = p.getProperty("simulator_class", simulator_classname);
            simulator_config = p.getProperty("simulator_config", simulator_config);

        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not read properties file, will use defaults and will overwrite the file if it exists");

            try
            {
                Files.copy(Paths.get("_default_properties.properties"), Paths.get(sPROPERTIES_FILE));
                Properties p = new Properties();
                p.load(new FileInputStream(new File(sPROPERTIES_FILE)));

                class_name = p.getProperty("robot_class", class_name);
                simulator_classname = p.getProperty("simulator_class", simulator_classname);
                simulator_config = p.getProperty("simulator_config", simulator_config);
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Could not read properties file");
        }
    }

    private void startSimulator() throws Exception
    {
        try
        {
            NetworkTable.setIPAddress("127.0.0.1");
            Preferences.__SetFileName(class_name + "_preferences.ini");
            simulated_robot = (RobotBase) Class.forName(class_name).newInstance();

            System.out.println("Simulator : " + simulator_classname);

            if (simulator_classname != null && !simulator_classname.isEmpty())
            {
                final ISimulatorContainer simulator = (ISimulatorContainer) Class.forName(simulator_classname).newInstance();

                RobotStateSingleton.get().addLoopListener(new RobotStateSingleton.LoopListener()
                {

                    @Override
                    public void looped()
                    {
                        simulator.looped();
                    }
                });

                simulator.setConfigFile(simulator_config);
            }
            else
            {
                System.out.println("**********************************************************************************");
                System.out.println("WARNING: Simulator class name was not set up.  Will use default, simple simulation");
                System.out.println("**********************************************************************************");
            }
        }
        catch (RuntimeException e)
        {
            if (e.getMessage() != null && e.getMessage().equals(sNETWORK_ERROR_MSG))
            {
                throw new Exception("Could not start the NetworkTables, check if you have two simulator instances open");
            }
            else
            {
                System.out.println("Unexpected error.  The real code would say \"Robots don't quit\"");
                throw e;
            }
        }
        catch (UnsatisfiedLinkError e)
        {
            throw new Exception("Linking error, this is probably PJ's fault.  Come find me and yell at me. Error message:\n    " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception
    {

        Timer.SetImplementation(new HardwareTimer());
        HLUsageReporting.SetImplementation(new HardwareHLUsageReporting());
        RobotState.SetImplementation(DriverStation.getInstance());

        try
        {
            final Main main = new Main();
            main.loadConfigFile();
            main.startSimulator();

            SwingUtilities.invokeLater(new Runnable()
            {

                @Override
                public void run()
                {

                    try
                    {
                        SimulatorFrame frame = new SimulatorFrame();
                        frame.pack();
                        frame.setVisible(true);

                        frame.start(main.simulated_robot);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
