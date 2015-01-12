/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Use a rate gyro to return the robots heading relative to a starting position.
 * The Gyro class tracks the robots heading based on the starting position. As
 * the robot rotates the new heading is computed by integrating the rate of
 * rotation returned by the sensor. When the class is instantiated, it does a
 * short calibration routine where it samples the gyro while at rest to
 * determine the default offset. This is subtracted from each sample to
 * determine the heading.
 */
public class Gyro extends SensorBase implements LiveWindowSendable {


	private AnalogInput m_analog;

	/**
	 * Initialize the gyro. Calibrate the gyro by running for a number of
	 * samples and computing the center value. Then use the center
	 * value as the Accumulator center value for subsequent measurements. It's
	 * important to make sure that the robot is not moving while the centering
	 * calculations are in progress, this is typically done when the robot is
	 * first turned on while it's sitting at rest before the competition starts.
	 */
	public void initGyro() {
		if (m_analog == null) {
			System.out.println("Null m_analog");
		}

		m_analog.setAverageBits(0);
		m_analog.setOversampleBits(0);
		AnalogInput.setGlobalSampleRate(0);
		Timer.delay(1.0);

		m_analog.initAccumulator();
		m_analog.resetAccumulator();

//		Timer.delay(kCalibrationSampleTime);

		m_analog.setAccumulatorCenter(0);
		m_analog.resetAccumulator();

		setDeadband(0.0);

//		setPIDSourceParameter(PIDSourceParameter.kAngle);
//
//		UsageReporting.report(tResourceType.kResourceType_Gyro, m_analog.getChannel());
//		LiveWindow.addSensor("Gyro", m_analog.getChannel(), this);
	}
	
	/**
	 * Gyro constructor using the channel number
	 *
	 * @param channel
	 *            The analog channel the gyro is connected to. Gyros can only 
                  be used on on-board channels 0-1.
	 */
	public Gyro(int channel) {
		this(new AnalogInput(channel));
	}

	/**
	 * Gyro constructor with a precreated analog channel object. Use this
	 * constructor when the analog channel needs to be shared.
	 *
	 * @param channel
	 *            The AnalogInput object that the gyro is connected to. Gyros 
	              can only be used on on-board channels 0-1.
	 */
	public Gyro(AnalogInput channel) {
		m_analog = channel;
		if (m_analog == null) {
			throw new NullPointerException("AnalogInput supplied to Gyro constructor is null");
		}
		initGyro();
	}

	/**
	 * Reset the gyro. Resets the gyro to a heading of zero. This can be used if
	 * there is significant drift in the gyro and it needs to be recalibrated
	 * after it has been running.
	 */
	public void reset() {
		if (m_analog != null) {
			m_analog.resetAccumulator();
		}
	}

	/**
	 * Delete (free) the accumulator and the analog components used for the
	 * gyro.
	 */
	@Override
	public void free() {
	}

	/**
	 * Return the actual angle in degrees that the robot is currently facing.
	 *
	 * The angle is based on the current accumulator value corrected by the
	 * oversampling rate, the gyro type and the A/D calibration values. The
	 * angle is continuous, that is it will continue from 360 to 361 degrees. This allows
	 * algorithms that wouldn't want to see a discontinuity in the gyro output
	 * as it sweeps past from 360 to 0 on the second time around.
	 *
	 * @return the current heading of the robot in degrees. This heading is
	 *         based on integration of the returned rate from the gyro.
	 */
	public double getAngle() {
		if (m_analog == null) {
			return 0.0;
		} else {

			return m_analog.getAverageValue();
		}
	}

	/**
	 * Return the rate of rotation of the gyro
	 *
	 * The rate is based on the most recent reading of the gyro analog value
	 *
	 * @return the current rate in degrees per second
	 */
	public double getRate() {
		if (m_analog == null) {
			return 0.0;
		} else {
			return m_analog.getAverageValue();
		}
	}

	/**
	 * Set the gyro sensitivity. This takes the number of
	 * volts/degree/second sensitivity of the gyro and uses it in subsequent
	 * calculations to allow the code to work with multiple gyros. This value
	 * is typically found in the gyro datasheet.
	 *
	 * @param voltsPerDegreePerSecond
	 *            The sensitivity in Volts/degree/second.
	 */
	public void setSensitivity(double voltsPerDegreePerSecond) {
	}

	/**
	 * Set the size of the neutral zone.  Any voltage from the gyro less than
	 * this amount from the center is considered stationary.  Setting a
	 * deadband will decrease the amount of drift when the gyro isn't rotating,
	 * but will make it less accurate.
	 *
	 * @param volts The size of the deadband in volts
	 */
	void setDeadband(double volts) {
	}


	/*
	 * Live Window code, only does anything if live window is activated.
	 */
	@Override
	public String getSmartDashboardType() {
		return "Gyro";
	}

	private ITable m_table;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITable getTable() {
		return m_table;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Value", getAngle());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startLiveWindowMode() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopLiveWindowMode() {
	}
}
