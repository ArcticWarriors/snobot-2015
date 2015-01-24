package com.snobot;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.sim.ISimulatorContainer;
import com.snobot.simulator.sim.LinearEncoderCalculator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.templates.RobotMap;

public class Team558Simulator implements ISimulatorContainer 
{

	private LinearEncoderCalculator right_encoder_pair;
	private LinearEncoderCalculator left_encoder_pair;
	
	public Team558Simulator()
	{
    	right_encoder_pair = new LinearEncoderCalculator(
    			SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightFrontMotorChannel), 
    			SensorActuatorRegistry.get().getEncoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB));
    	
    	left_encoder_pair = new LinearEncoderCalculator(
    			SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftFrontMotorChannel), 
    			SensorActuatorRegistry.get().getEncoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB));

    	double kp = .1;
    	right_encoder_pair.setSimulatorParams(kp);
    	left_encoder_pair.setSimulatorParams(-kp);
	}

	@Override
	public void looped() {
		
		right_encoder_pair.update();
		left_encoder_pair.update();
	}

	@Override
	public void setConfigFile(String simulator_config) {
		//Not implemented...
	}
}
