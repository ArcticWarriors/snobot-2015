package com.snobot.simulator.sim;

import edu.wpi.first.wpilibj.RobotBase;

public interface ISimulatorContainer {

	public abstract void setRobot(RobotBase aRobot);

	public abstract void setConfigFile(String simulator_config);
}
