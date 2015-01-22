package com.snobot;

import com.snobot.simulator.sim.ISimulatorContainer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;

public class Snobot2015Simulator implements ISimulatorContainer  {

	public Snobot2015Simulator()
	{
		
	}

	@Override
	public void setRobot(RobotBase aRobot) {

    	DriverStation.getInstance().addLoopListener(new DriverStation.LoopListener() {
			
			@Override
			public void looped() {
			}
		});
	}

	@Override
	public void setConfigFile(String simulator_config) {
		//Not implemented...
	}
}
