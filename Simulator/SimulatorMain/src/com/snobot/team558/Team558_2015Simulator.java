package com.snobot.team558;

import org.usfirst.frc.team558.robot.RobotMap;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SolenoidWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.ISimulatorContainer;

public class Team558_2015Simulator implements ISimulatorContainer
{
    private Team558ElevatorSim mElevatorSim;

    public Team558_2015Simulator()
    {
        // Drive Motors
        SpeedControllerWrapper rightDriveMotorFront = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightFrontMotorChannel);
        SpeedControllerWrapper rightDriveMotorRear = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightRearChannel);
        SpeedControllerWrapper leftDriveMotorFront = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftFrontMotorChannel);
        SpeedControllerWrapper leftDriveMotorRear = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftRearMotorChannel);

        rightDriveMotorFront.setName("Right Front Drive");
        rightDriveMotorRear.setName("Right Rear Drive");
        leftDriveMotorFront.setName("Left Front Drive");
        leftDriveMotorRear.setName("Left Rear Drive");

        // ElevatorMotors
        SpeedControllerWrapper leftElevatorMotor = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftElevatorMotorChannel);
        SpeedControllerWrapper rightElevatorMotor = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightElevatorMotorChannel);

        leftElevatorMotor.setName("Left Elevator");
        rightElevatorMotor.setName("Right Elevator");

        // Solenoids
        SolenoidWrapper gripperSolenoid = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.gripperSolenoidChannel);
        SolenoidWrapper toteGripperSolenoidA = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.toteGripperSolenoidChannel1);
        SolenoidWrapper toteGripperSolenoidB = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.toteGripperSolenoidChannel2);
        SolenoidWrapper whaleTailArmSolA = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.whaleTailArmSolenoidChannel1);
        SolenoidWrapper whaleTailArmSolB = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.whaleTailArmSolenoidChannel2);
        SolenoidWrapper whaleTailFinSolA = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.whaletailFinsSolenoidChannel1);
        SolenoidWrapper whaleTailFinSolB = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.whaletailFinsSolenoidChannel2);

        gripperSolenoid.setName("gripperSolenoid");
        toteGripperSolenoidA.setName("toteGripperSolenoidA");
        toteGripperSolenoidB.setName("toteGripperSolenoidB");
        whaleTailArmSolA.setName("whaleTailArmSolA");
        whaleTailArmSolB.setName("whaleTailArmSolB");
        whaleTailFinSolA.setName("whaleTailFinSolA");
        whaleTailFinSolB.setName("whaleTailFinSolB");

        // Limit Switches
        DigitalSourceWrapper topLimitSwitch = SensorActuatorRegistry.get().getDigitalSources().get(RobotMap.topLimitSwitchChannel);
        DigitalSourceWrapper botLimitSwitch = SensorActuatorRegistry.get().getDigitalSources().get(RobotMap.bottomLimitSwitchChannel);

        topLimitSwitch.setName("Top Limit Switch");
        botLimitSwitch.setName("Bottom Limit Switch");

        // Encoders
        EncoderWrapper elevatorEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.elevatorEncoderAChannel, RobotMap.elevatorEncoderBChannel);
        EncoderWrapper leftDriveEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.leftDriveEncoderAChannel,
                RobotMap.leftDriveEncoderBChannel);
        EncoderWrapper rightDriveEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.rightDriveEncoderAChannel,
                RobotMap.rightDriveEncoderBChannel);

        elevatorEncoder.setName("Elevator Encoder");
        leftDriveEncoder.setName("Left Drive Encoder");
        rightDriveEncoder.setName("Right Drive Encoder");

        // Analog Input
        // AnalogWrapper loPressureSensor = SensorActuatorRegistry.get().getAnalog().get(RobotMap.loPressureSensorChannel);
        AnalogWrapper highPressureSensor = SensorActuatorRegistry.get().getAnalog().get(RobotMap.highPressureSensorChannel);

        // loPressureSensor.setName("Low Pressure Sensor");
        highPressureSensor.setName("High Pressure Sensor");

        // Simulation parameters
        double drivetrainSpeed = -3.8 * 12;
        rightDriveMotorFront.setMotorParameters(drivetrainSpeed);
        rightDriveMotorRear.setMotorParameters(drivetrainSpeed);
        leftDriveMotorFront.setMotorParameters(drivetrainSpeed);
        leftDriveMotorRear.setMotorParameters(drivetrainSpeed);

        double elevatorSpeed = 3.8 * 12;
        leftElevatorMotor.setMotorParameters(elevatorSpeed);
        rightElevatorMotor.setMotorParameters(elevatorSpeed);

        elevatorEncoder.setSpeedController(leftElevatorMotor);
        leftDriveEncoder.setSpeedController(leftDriveMotorFront);
        rightDriveEncoder.setSpeedController(rightDriveMotorFront);

        leftDriveEncoder.setDistancePerTick(RobotMap.driveEncoderConversion);
        rightDriveEncoder.setDistancePerTick(RobotMap.driveEncoderConversion);
        elevatorEncoder.setDistancePerTick(RobotMap.elevatorEncoderConversion);

        mElevatorSim = new Team558ElevatorSim(elevatorEncoder, botLimitSwitch, topLimitSwitch);

        highPressureSensor.setVoltage(3.7);
    }

    @Override
    public void looped()
    {
        mElevatorSim.update();
    }

    @Override
    public void setConfigFile(String simulator_config)
    {
        // Not implemented...
    }
}
