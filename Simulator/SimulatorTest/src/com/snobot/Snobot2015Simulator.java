package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.DistanceCalculator;
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
        
        AnalogWrapper stackerPot = SensorActuatorRegistry.get().getAnalog().get(
                ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sSTACKER_POT, 1));

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

        // mStackerSimulator = new StackerSimulator (
        // stackerMotor, stackerPot, upperStackerLimit, lowerStackerLimit,
        // ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_POT_MIN_VOLTS, 0),
        // ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_POT_VOLTS_PER_INCH, 1));

        // TODO update with non-hardcoded values

        // double pot_voltage = mStackerPotentiometer.getVoltage();
        // double pot_min_top = 3.596190;
        // double pot_max_bot = 4.9731405;
        // double pot_diff = (pot_max_bot - pot_min_top);
        // double stacker_height = 24;
        DistanceCalculator pot_wrapper = new StackerPotSim(stackerPot);
        mStackerSimulator = new StackerSimulator (
                stackerMotor, upperStackerLimit, lowerStackerLimit, pot_wrapper);

        
        // stackerPot.setDistancePerTick(ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sSTACKER_POT_VOLTS_PER_INCH,
        // .4));
        
        mLeftDriveEnc.setSimulatorParams(-.8);
        mRightDriveEnc.setSimulatorParams(-.8);
        mStackerSimulator.setSimulatorParams(-1);
        // mStackerSimulator.setSimulatorParams(1);
        
        
        mGyroSim = new TankDriveGyroSimulator(leftEncoder, rightEncoder, gyroChannel);

        mGyroSim.setIsReverse(true, false);
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
