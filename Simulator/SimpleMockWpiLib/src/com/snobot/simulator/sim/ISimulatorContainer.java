package com.snobot.simulator.sim;

public interface ISimulatorContainer {

	public abstract void looped();

	public abstract void setConfigFile(String simulator_config);
}
