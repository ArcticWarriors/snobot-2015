package com.snobot.team558;
// package com.snobot;
//
// import com.snobot.simulator.AnalogWrapper;
// import com.snobot.simulator.EncoderWrapper;
// import com.snobot.simulator.SensorActuatorRegistry;
// import com.snobot.simulator.SolenoidWrapper;
// import com.snobot.simulator.SpeedControllerWrapper;
// import com.snobot.simulator.sim.ISimulatorContainer;
// import com.snobot.simulator.sim.TankDriveGyroSimulator;
//
// import edu.wpi.first.wpilibj.templates.RobotMap;
//
// public class Team558Simulator implements ISimulatorContainer
// {
//
// private TankDriveGyroSimulator gyro_simulator;
//
// public Team558Simulator()
// {
// EncoderWrapper rightEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
// EncoderWrapper leftEncoder = SensorActuatorRegistry.get().getEncoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB);
//
// SpeedControllerWrapper rightDriveMotorFront = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightFrontMotorChannel);
// SpeedControllerWrapper rightDriveMotorRear = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightRearChannel);
// SpeedControllerWrapper leftDriveMotorFront = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftFrontMotorChannel);
// SpeedControllerWrapper leftDriveMotorRear = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftRearMotorChannel);
// SpeedControllerWrapper leftIntakeMotor = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.leftIntakeMotorChannel);
// SpeedControllerWrapper rightIntakeMotor = SensorActuatorRegistry.get().getSpeedControllers().get(RobotMap.rightIntakeMotorChannel);
//
// // Solenoids
// // public static int catcherSolenoidChannel1 = 0;
// // public static int catcherSolenoidChannel2 = 1;
// // public static int intakeSolenoidChannel1 = 2;
// // public static int intakeSolenoidChannel2 = 3;
// // public static int shooterSolenoid1Channel = 4;
// // public static int shooterSolenoid2Channel = 5;
// // public static int latchSolenoidChannel1 = 6;
// // public static int latchSolenoidChannel2 = 7;
//
// SolenoidWrapper catch_sol1 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.catcherSolenoidChannel1);
// SolenoidWrapper catch_sol2 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.catcherSolenoidChannel2);
// SolenoidWrapper intake_sol1 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.intakeSolenoidChannel1);
// SolenoidWrapper intake_sol2 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.intakeSolenoidChannel2);
// SolenoidWrapper shooter_sol1 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.shooterSolenoid1Channel);
// SolenoidWrapper shooter_sol2 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.shooterSolenoid2Channel);
// SolenoidWrapper latch_sol1 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.latchSolenoidChannel1);
// SolenoidWrapper latch_sol2 = SensorActuatorRegistry.get().getSolenoids().get(RobotMap.latchSolenoidChannel2);
//
// AnalogWrapper gyroChannel = SensorActuatorRegistry.get().getAnalog().get(RobotMap.gyroPort);
//
// leftEncoder.setName("Left Drive Encoder");
// rightEncoder.setName("Right Drive Encoder");
//
// rightDriveMotorFront.setName("Right Front Drive");
// rightDriveMotorRear.setName("Right Rear Drive");
// leftDriveMotorFront.setName("Left Front Drive");
// leftDriveMotorRear.setName("Left Rear Drive");
// rightIntakeMotor.setName("Right Intake");
// leftIntakeMotor.setName("Left Intake");
//
// catch_sol1.setName("Catch A");
// catch_sol2.setName("Catch B");
// intake_sol1.setName("Intake A");
// intake_sol2.setName("Intake B");
// shooter_sol1.setName("Shooter A");
// shooter_sol2.setName("Shooter B");
// latch_sol1.setName("Latch A");
// latch_sol2.setName("Latch B");
//
// gyroChannel.setName("Gyro");
//
// rightEncoder.setSpeedController(rightDriveMotorFront);
// leftEncoder.setSpeedController(leftDriveMotorFront);
//
// gyro_simulator = new TankDriveGyroSimulator(
// leftEncoder,
// rightEncoder,
// gyroChannel);
//
// double drivetrainSpeed = 3.8 * 12;
// rightDriveMotorFront.setMotorParameters(drivetrainSpeed);
// leftDriveMotorFront.setMotorParameters(-drivetrainSpeed);
// }
//
// @Override
// public void looped()
// {
// gyro_simulator.update();
// }
//
// @Override
// public void setConfigFile(String simulator_config)
// {
// // Not implemented...
// }
// }
