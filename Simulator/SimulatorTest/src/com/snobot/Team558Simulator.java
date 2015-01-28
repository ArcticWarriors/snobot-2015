package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.ISimulatorContainer;
import com.snobot.simulator.sim.LinearEncoderCalculator;
import com.snobot.simulator.sim.TankDriveGyroSimulator;

import edu.wpi.first.wpilibj.templates.RobotMap;

public class Team558Simulator implements ISimulatorContainer 
{

	private LinearEncoderCalculator right_encoder_pair;
	private LinearEncoderCalculator left_encoder_pair;
	private TankDriveGyroSimulator gyro_simulator;
	
	public Team558Simulator()
	{
		EncoderWrapper rightEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
		EncoderWrapper leftEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB);

		SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightFrontMotorChannel);
		SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftFrontMotorChannel);

		AnalogWrapper gyroChannel = SensorActuatorRegistry.get().getAnalog().get(RobotMap.gyroPort);
		
    	right_encoder_pair = new LinearEncoderCalculator(rightDriveMotor, rightEncoder);
    	left_encoder_pair = new LinearEncoderCalculator(leftDriveMotor, leftEncoder);
    	
    	gyro_simulator = new TankDriveGyroSimulator(
    			leftEncoder, 
    			rightEncoder, 
    			gyroChannel);

    	double kp = .1;
    	right_encoder_pair.setSimulatorParams(kp);
    	left_encoder_pair.setSimulatorParams(-kp);
	}

	@Override
	public void looped() {
		
		right_encoder_pair.update();
		left_encoder_pair.update();
		gyro_simulator.update();
	}

	@Override
	public void setConfigFile(String simulator_config) {
		//Not implemented...
	}
}
