package com.snobot.simulator.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.snobot.simulator.RobotStateSingleton;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;


public class SimulatorFrame extends JFrame 
{

	private GraphicalSensorDisplayPanel mBasicPanel;
	private EnablePanel mEnablePanel;
	
	public SimulatorFrame()
	{
		initComponenents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initComponenents()
	{
		mBasicPanel = new GraphicalSensorDisplayPanel();
		mEnablePanel = new EnablePanel();
		
		mEnablePanel.addStateChangedListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RobotStateSingleton.get().setDisabled(!mEnablePanel.isEnabled());
				RobotStateSingleton.get().setAutonomous(mEnablePanel.isAuton());
			}
		});

		add(mBasicPanel);
		add(mEnablePanel, BorderLayout.NORTH);

		RobotStateSingleton.get().setDisabled(false);
		RobotStateSingleton.get().setAutonomous(false);
		
		mEnablePanel.setRobotEnabled(true);
		RobotStateSingleton.get().setDisabled(false);
	}
	
	public void start(final RobotBase aRobot)
	{
		final Scheduler scheduler = Scheduler.getInstance();
		RobotStateSingleton.get().addLoopListener(new RobotStateSingleton.LoopListener() {
			
			@Override
			public void looped() {
				scheduler.run();
				mBasicPanel.update();
				mEnablePanel.setTime(Timer.getMatchTime());
			}
		});
		
        Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try
				{
					aRobot.startCompetition();
				}
				catch(Exception e)
				{
					System.out.println("Unexpected exception, shutting down simulator");
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
        t.start();
	}
}
