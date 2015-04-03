package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SolenoidWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.ISimulatorContainer;
import com.snobot.simulator.sim.TankDriveGyroSimulator;

public class Snobot2015Simulator implements ISimulatorContainer  {

    private StackerPotSim mPotWrapper;
    
    private TankDriveGyroSimulator mGyroSim;
    
    public Snobot2015Simulator()
    {
        EncoderWrapper rightEncoder = SensorActuatorRegistry.get().getEncoder(
                Properties2015.sRIGHT_DRIVE_ENC_A.getValue(), 
                Properties2015.sRIGHT_DRIVE_ENC_B.getValue());
        
        EncoderWrapper leftEncoder = SensorActuatorRegistry.get().getEncoder(
                Properties2015.sLEFT_DRIVE_ENC_A.getValue(), 
                Properties2015.sLEFT_DRIVE_ENC_B.getValue());
        
        AnalogWrapper stackerPot = SensorActuatorRegistry.get().getAnalog().get(
                Properties2015.sSTACKER_POT.getValue());

        AnalogWrapper tansducer = SensorActuatorRegistry.get().getAnalog().get(
                Properties2015.sTRANSDUCER.getValue());

        SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                Properties2015.sDRIVE_MOTOR_RIGHT_1.getValue());
        
        SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                Properties2015.sDRIVE_MOTOR_LEFT_1.getValue());
        
        SpeedControllerWrapper stackerMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                Properties2015.sSTACKER_MOTOR.getValue());

        SpeedControllerWrapper rakeMotor = SensorActuatorRegistry.get().getSpeedControllers().get(
                Properties2015.sRAKE_MOTOR.getValue());

        AnalogWrapper gyroChannel = SensorActuatorRegistry.get().getAnalog().get(
                Properties2015.sGYRO_SENSOR.getValue());
        
        DigitalSourceWrapper lowerStackerLimit = SensorActuatorRegistry.get().getDigitalSources().get(
                Properties2015.sSTACKER_LOWER_LIMIT_SWITCH.getValue());
        
        DigitalSourceWrapper upperStackerLimit = SensorActuatorRegistry.get().getDigitalSources().get(
                Properties2015.sSTACKER_UPPER_LIMIT_SWITCH.getValue());

        SolenoidWrapper armSolenoid = SensorActuatorRegistry.get().getSolenoids().get(
                Properties2015.sCLAW_ARM_SOLENOID.getValue());

        SolenoidWrapper clawSolenoid = SensorActuatorRegistry.get().getSolenoids().get(
                Properties2015.sCLAW_HAND_SOLENOID.getValue());

        armSolenoid.setName("Arm");
        clawSolenoid.setName("Claw");
        tansducer.setName("Transducer");
        leftEncoder.setName("Left Drive Encoder");
        rightEncoder.setName("Right Drive Encoder");
        stackerPot.setName("Stacker Pot");
        rightDriveMotor.setName("LeftDrive");
        leftDriveMotor.setName("RightDrive");
        stackerMotor.setName("Stacker");
        rakeMotor.setName("Rake");
        gyroChannel.setName("Gyro");

        double drivetrainSpeed = -2.9 * 12;
        
        rightDriveMotor.setMotorParameters(drivetrainSpeed);
        leftDriveMotor.setMotorParameters(drivetrainSpeed);
        stackerMotor.setMotorParameters(-6 * 12);

        rightEncoder.setSpeedController(rightDriveMotor);
        leftEncoder.setSpeedController(leftDriveMotor);

        upperStackerLimit.set(true);
        lowerStackerLimit.set(true);

        mPotWrapper = new StackerPotSim(stackerPot, stackerMotor);
        mPotWrapper.setParameters(
                Properties2015.sSTACKER_MAX_HEIGHT.getValue(),
                Properties2015.sPOT_TOP_MIN_VOLT.getValue(),
                Properties2015.sPOT_BOT_MAX_VOLT.getValue());
        
        mGyroSim = new TankDriveGyroSimulator(leftEncoder, rightEncoder, gyroChannel);

        mGyroSim.setIsReverse(true, false);
    }

    @Override
    public void looped() {
        // mRightDriveEnc.update();
        // mLeftDriveEnc.update();
        mGyroSim.update();
        mPotWrapper.update();
    }

    @Override
    public void setConfigFile(String simulator_config) {
        //Not implemented...
    }
}
