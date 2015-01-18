package com.snobot;

import com.snobot.simulator.sim.ISimulatorContainer;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotBase.LoopListener;

public class Snobot2015Simulator implements ISimulatorContainer  {

	public Snobot2015Simulator()
	{
		
	}

	@Override
	public void setRobot(RobotBase aRobot) {

    	aRobot.addLoopListener(new LoopListener() {
			
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
