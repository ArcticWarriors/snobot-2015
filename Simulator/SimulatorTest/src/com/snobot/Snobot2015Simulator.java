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
                ConfigurationNames.sRIGHT_DRIVE_ENC_A.getValue(), 
                ConfigurationNames.sRIGHT_DRIVE_ENC_B.getValue());
        
        EncoderWrapper leftEncoder = SensorActuatorRegistry.get().getEncoder(
                ConfigurationNames.sLEFT_DRIVE_ENC_A.getValue(), 
                ConfigurationNames.sLEFT_DRIVE_ENC_B.getValue());
        
        AnalogWrapper stackerPot = SensorActuatorRegistry.get().getAnalog().get(
                ConfigurationNames.sSTACKER_POT.getValue());

        SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                ConfigurationNames.sDRIVE_MOTOR_RIGHT_1.getValue());
        
        SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                ConfigurationNames.sDRIVE_MOTOR_LEFT_1.getValue());
        
        SpeedControllerWrapper stackerMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                ConfigurationNames.sSTACKER_MOTOR.getValue());

        AnalogWrapper gyroChannel = SensorActuatorRegistry.get().getAnalog().get(
                ConfigurationNames.sGYRO_SENSOR.getValue());
        
        DigitalSourceWrapper lowerStackerLimit = SensorActuatorRegistry.get().getDigitalSources().get(
                ConfigurationNames.sSTACKER_LOWER_LIMIT_SWITCH.getValue());
        
        DigitalSourceWrapper upperStackerLimit = SensorActuatorRegistry.get().getDigitalSources().get(
                ConfigurationNames.sSTACKER_UPPER_LIMIT_SWITCH.getValue());
       

        mRightDriveEnc = new LinearEncoderCalculator(rightDriveMotor, rightEncoder);
        mLeftDriveEnc = new LinearEncoderCalculator(leftDriveMotor, leftEncoder);

        DistanceCalculator pot_wrapper = new StackerPotSim(stackerPot);
        mStackerSimulator = new StackerSimulator (
                stackerMotor, upperStackerLimit, lowerStackerLimit, pot_wrapper);

        double inches_per_sec = 7 * 12;
        double driveKp = -inches_per_sec / 124.125;
        
        mLeftDriveEnc.setSimulatorParams(driveKp);
        mRightDriveEnc.setSimulatorParams(driveKp);
        mStackerSimulator.setSimulatorParams(-1);
        
        
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
