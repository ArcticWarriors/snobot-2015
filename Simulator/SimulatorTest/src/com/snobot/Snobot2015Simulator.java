package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.ISimulatorContainer;
import com.snobot.simulator.sim.LinearEncoderCalculator;
import com.snobot.simulator.sim.TankDriveGyroSimulator;

public class Snobot2015Simulator implements ISimulatorContainer  {

    private LinearEncoderCalculator mRightDriveEnc;
    private LinearEncoderCalculator mLeftDriveEnc;
    private StackerSimulator mStackerSimulator;
    
    private TankDriveGyroSimulator mGyroSim;
    
	public Snobot2015Simulator()
	{
        EncoderWrapper rightEncoder = SensorActuatorRegistry.get().getEncoder(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_A, 1), 
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sRIGHT_DRIVE_ENC_B, 1));
        
        EncoderWrapper leftEncoder = SensorActuatorRegistry.get().getEncoder(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_A, 1), 
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLEFT_DRIVE_ENC_B, 1));
        
        EncoderWrapper stackerEncoder = SensorActuatorRegistry.get().getEncoder(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_ENCODER_A, 1),
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_ENCODER_B, 1));

        SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_RIGHT_1, 1));
        
        SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sDRIVE_MOTOR_LEFT_1, 1));
        
        SpeedControllerWrapper stackerMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_MOTOR, 1));

        AnalogWrapper gyroChannel = SensorActuatorRegistry.get().getAnalog().get(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sGYRO_SENSOR, 1));
        
        DigitalSourceWrapper lowerStackerLimit = SensorActuatorRegistry.get().getDigitalSources().get(
        		ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_LOWER_LIMIT_SWITCH, 1));
        
        DigitalSourceWrapper upperStackerLimit = SensorActuatorRegistry.get().getDigitalSources().get(
        		ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_UPPER_LIMIT_SWITCH, 2));
	   

        mRightDriveEnc = new LinearEncoderCalculator(rightDriveMotor, rightEncoder);
        mLeftDriveEnc = new LinearEncoderCalculator(leftDriveMotor, leftEncoder);
        mStackerSimulator = new StackerSimulator (stackerMotor, stackerEncoder,
        		upperStackerLimit, lowerStackerLimit);

        
        stackerEncoder.setDistancePerTick(ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_ENCODER_DPT, .4));
        
        mLeftDriveEnc.setSimulatorParams(.01);
        mRightDriveEnc.setSimulatorParams(-.01);        
        mStackerSimulator.setSimulatorParams(1000);
        
        
        mGyroSim = new TankDriveGyroSimulator(leftEncoder, rightEncoder, gyroChannel);
	}

	@Override
	public void looped() {
        mRightDriveEnc.update();
        mLeftDriveEnc.update();
        mGyroSim.update();
        mStackerSimulator.update();
	}

	@Override
	public void setConfigFile(String simulator_config) {
		//Not implemented...
	}
}
