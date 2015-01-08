package com.snobot.simulator.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotBase.LoopListener;
import edu.wpi.first.wpilibj.Timer;


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
				DriverStation.getInstance().InDisabled(!mEnablePanel.isEnabled());
				DriverStation.getInstance().InAutonomous(mEnablePanel.isAuton());
			}
		});

		add(mBasicPanel);
		add(mEnablePanel, BorderLayout.NORTH);

		DriverStation.getInstance().InDisabled(true);
		DriverStation.getInstance().InAutonomous(false);
	}
	
	public void start(final RobotBase aRobot)
	{
		aRobot.setLoopListener(new LoopListener() {
			
			@Override
			public void looped() {
				mBasicPanel.update();
				mEnablePanel.setTime(Timer.getMatchTime());
			}
		});
		
        Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				aRobot.startCompetition();
			}
		});
        t.start();
	}
}
