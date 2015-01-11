/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

import com.snobot.simulator.EncoderPair;
import com.snobot.simulator.SensorActuatorRegistry;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Class to read quad encoders. Quadrature encoders are devices that count shaft
 * rotation and can sense direction. The output of the QuadEncoder class is an
 * integer that can count either up or down, and can go negative for reverse
 * direction counting. When creating QuadEncoders, a direction is supplied that
 * changes the sense of the output to make code more readable if the encoder is
 * mounted such that forward movement generates negative values. Quadrature
 * encoders have two digital outputs, an A Channel and a B Channel that are out
 * of phase with each other to allow the FPGA to do direction sensing.
 *
 * All encoders will immediately start counting - reset() them if you need them
 * to be zeroed before use.
 */
public class Encoder extends SensorBase implements LiveWindowSendable {

	protected DigitalSource m_aSource; // the A phase of the quad encoder
	protected DigitalSource m_bSource; // the B phase of the quad encoder
	private double m_distancePerPulse;
	
	private double __mDistance;
	
	public DigitalSource __getASource()
	{
		return m_aSource;
	}
	public DigitalSource __getBSource()
	{
		return m_bSource;
	}


	/**
	 * Encoder constructor. Construct a Encoder given a and b channels.
	 *
	 * The encoder will start counting immediately.
	 *
	 * @param aChannel
	 *            The a channel DIO channel. 0-9 are on-board, 10-25 are on the MXP port
	 * @param bChannel
	 *            The b channel DIO channel. 0-9 are on-board, 10-25 are on the MXP port
	 * @param reverseDirection
	 *            represents the orientation of the encoder and inverts the
	 *            output values if necessary so forward represents positive
	 *            values.
	 */
	public Encoder(final int aChannel, final int bChannel,
			boolean reverseDirection) {
		
		//This will do the allocation test
		m_aSource = new DigitalInput(aChannel);
		m_bSource = new DigitalInput(bChannel);
		
		SensorActuatorRegistry.get().register(this, new EncoderPair(aChannel, bChannel));
	}

	/**
	 * Encoder constructor. Construct a Encoder given a and b channels.
	 *
	 * The encoder will start counting immediately.
	 *
	 * @param aChannel
	 *            The a channel digital input channel.
	 * @param bChannel
	 *            The b channel digital input channel.
	 */
	public Encoder(final int aChannel, final int bChannel) {
		this(aChannel, bChannel, false);
	}


	/**
	 * Encoder constructor. Construct a Encoder given a and b channels as
	 * digital inputs. This is used in the case where the digital inputs are
	 * shared. The Encoder class will not allocate the digital inputs and assume
	 * that they already are counted.
	 *
	 * The encoder will start counting immediately.
	 *
	 * @param aSource
	 *            The source that should be used for the a channel.
	 * @param bSource
	 *            the source that should be used for the b channel.
	 * @param reverseDirection
	 *            represents the orientation of the encoder and inverts the
	 *            output values if necessary so forward represents positive
	 *            values.
	 */
	public Encoder(DigitalSource aSource, DigitalSource bSource,
			boolean reverseDirection) {
		if (aSource == null)
			throw new NullPointerException("Digital Source A was null");
		m_aSource = aSource;
		if (bSource == null)
			throw new NullPointerException("Digital Source B was null");
		m_bSource = bSource;
	}

	/**
	 * Encoder constructor. Construct a Encoder given a and b channels as
	 * digital inputs. This is used in the case where the digital inputs are
	 * shared. The Encoder class will not allocate the digital inputs and assume
	 * that they already are counted.
	 *
	 * The encoder will start counting immediately.
	 *
	 * @param aSource
	 *            The source that should be used for the a channel.
	 * @param bSource
	 *            the source that should be used for the b channel.
	 */
	public Encoder(DigitalSource aSource, DigitalSource bSource) {
		this(aSource, bSource, false);
	}
	/**
	 * Encoder constructor. Construct a Encoder given a and b channels as
	 * digital inputs. This is used in the case where the digital inputs are
	 * shared. The Encoder class will not allocate the digital inputs and assume
	 * that they already are counted.
	 *
	 * The encoder will start counting immediately.
	 *
	 * @param aSource
	 *            The source that should be used for the a channel.
	 * @param bSource
	 *            the source that should be used for the b channel.
	 * @param indexSource
	 *            the source that should be used for the index channel.
	 * @param reverseDirection
	 *            represents the orientation of the encoder and inverts the
	 *            output values if necessary so forward represents positive
	 *            values.
	 */
	public Encoder(DigitalSource aSource, DigitalSource bSource,
			DigitalSource indexSource, boolean reverseDirection) {
		if (aSource == null)
			throw new NullPointerException("Digital Source A was null");
		m_aSource = aSource;
		if (bSource == null)
			throw new NullPointerException("Digital Source B was null");
		m_aSource = aSource;
		m_bSource = bSource;
	}

	/**
	 * Encoder constructor. Construct a Encoder given a and b channels as
	 * digital inputs. This is used in the case where the digital inputs are
	 * shared. The Encoder class will not allocate the digital inputs and assume
	 * that they already are counted.
	 *
	 * The encoder will start counting immediately.
	 *
	 * @param aSource
	 *            The source that should be used for the a channel.
	 * @param bSource
	 *            the source that should be used for the b channel.
	 * @param indexSource
	 *            the source that should be used for the index channel.
	 */
	public Encoder(DigitalSource aSource, DigitalSource bSource,
			DigitalSource indexSource) {
		this(aSource, bSource, indexSource, false);
	}

	/**
	 * @return the encoding scale factor 1x, 2x, or 4x, per the requested
	 *   encodingType. Used to divide raw edge counts down to spec'd counts.
	 */
	public int getEncodingScale() {
		return 0;
	}

	public void free() {
	}

	/**
	 * Gets the raw value from the encoder. The raw value is the actual count
	 * unscaled by the 1x, 2x, or 4x scale factor.
	 *
	 * @return Current raw count from the encoder
	 */
	public int getRaw() {
		return (int) (__mDistance * m_distancePerPulse);
	}

	/**
	 * Gets the current count. Returns the current count on the Encoder. This
	 * method compensates for the decoding type.
	 *
	 * @return Current count from the Encoder adjusted for the 1x, 2x, or 4x
	 *         scale factor.
	 */
	public int get() {
		return (int) (getRaw() * decodingScaleFactor());
	}

	/**
	 * Reset the Encoder distance to zero. Resets the current count to zero on
	 * the encoder.
	 */
	public void reset() {
		__mDistance = 0;
	}

	/**
	 * Returns the period of the most recent pulse. Returns the period of the
	 * most recent Encoder pulse in seconds. This method compensates for the
	 * decoding type.
	 *
	 * @deprecated Use getRate() in favor of this method. This returns unscaled
	 *             periods and getRate() scales using value from
	 *             setDistancePerPulse().
	 *
	 * @return Period in seconds of the most recent pulse.
	 */
	public double getPeriod() {
		return 0;
	}

	/**
	 * Sets the maximum period for stopped detection. Sets the value that
	 * represents the maximum period of the Encoder before it will assume that
	 * the attached device is stopped. This timeout allows users to determine if
	 * the wheels or other shaft has stopped rotating. This method compensates
	 * for the decoding type.
	 *
	 *
	 * @param maxPeriod
	 *            The maximum time between rising and falling edges before the
	 *            FPGA will report the device stopped. This is expressed in
	 *            seconds.
	 */
	public void setMaxPeriod(double maxPeriod) {
	}

	/**
	 * Determine if the encoder is stopped. Using the MaxPeriod value, a boolean
	 * is returned that is true if the encoder is considered stopped and false
	 * if it is still moving. A stopped encoder is one where the most recent
	 * pulse width exceeds the MaxPeriod.
	 *
	 * @return True if the encoder is considered stopped.
	 */
	public boolean getStopped() {
		return false;
	}

	/**
	 * The last direction the encoder value changed.
	 *
	 * @return The last direction the encoder value changed.
	 */
	public boolean getDirection() {
		return false;
	}

	/**
	 * The scale needed to convert a raw counter value into a number of encoder
	 * pulses.
	 */
	private double decodingScaleFactor() {
		return 1;
	}

	/**
	 * Get the distance the robot has driven since the last reset.
	 *
	 * @return The distance driven since the last reset as scaled by the value
	 *         from setDistancePerPulse().
	 */
	public double getDistance() {
		return __mDistance;
	}

	/**
	 * Get the current rate of the encoder. Units are distance per second as
	 * scaled by the value from setDistancePerPulse().
	 *
	 * @return The current rate of the encoder.
	 */
	public double getRate() {
		return m_distancePerPulse / getPeriod();
	}

	/**
	 * Set the minimum rate of the device before the hardware reports it
	 * stopped.
	 *
	 * @param minRate
	 *            The minimum rate. The units are in distance per second as
	 *            scaled by the value from setDistancePerPulse().
	 */
	public void setMinRate(double minRate) {
		setMaxPeriod(m_distancePerPulse / minRate);
	}

	/**
	 * Set the distance per pulse for this encoder. This sets the multiplier
	 * used to determine the distance driven based on the count value from the
	 * encoder. Do not include the decoding type in this scale. The library
	 * already compensates for the decoding type. Set this value based on the
	 * encoder's rated Pulses per Revolution and factor in gearing reductions
	 * following the encoder shaft. This distance can be in any units you like,
	 * linear or angular.
	 *
	 * @param distancePerPulse
	 *            The scale factor that will be used to convert pulses to useful
	 *            units.
	 */
	public void setDistancePerPulse(double distancePerPulse) {
		m_distancePerPulse = distancePerPulse;
	}

	/**
	 * Set the direction sensing for this encoder. This sets the direction
	 * sensing on the encoder so that it could count in the correct software
	 * direction regardless of the mounting.
	 *
	 * @param reverseDirection
	 *            true if the encoder direction should be reversed
	 */
	public void setReverseDirection(boolean reverseDirection) {
	}

	/**
	 * Set the Samples to Average which specifies the number of samples of the
	 * timer to average when calculating the period. Perform averaging to
	 * account for mechanical imperfections or as oversampling to increase
	 * resolution.
	 *
	 * TODO: Should this throw a checked exception, so that the user has to deal
	 * with giving an incorrect value?
	 *
	 * @param samplesToAverage
	 *            The number of samples to average from 1 to 127.
	 */
	public void setSamplesToAverage(int samplesToAverage) {
	}

	/**
	 * Get the Samples to Average which specifies the number of samples of the
	 * timer to average when calculating the period. Perform averaging to
	 * account for mechanical imperfections or as oversampling to increase
	 * resolution.
	 *
	 * @return SamplesToAverage The number of samples being averaged (from 1 to
	 *         127)
	 */
	public int getSamplesToAverage() {
		return 1;
	}

	/**
	 * Implement the PIDSource interface.
	 *
	 * @return The current value of the selected source parameter.
	 */
	public double pidGet() {
		return 0;
	}

	/*
	 * Live Window code, only does anything if live window is activated.
	 */
	public String getSmartDashboardType() {
		return "Encoder";
	}

	private ITable m_table;

	/**
	 * {@inheritDoc}
	 */
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}

	/**
	 * {@inheritDoc}
	 */
	public ITable getTable() {
		return m_table;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Speed", getRate());
			m_table.putNumber("Distance", getDistance());
			m_table.putNumber("Distance per Tick", m_distancePerPulse);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void startLiveWindowMode() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void stopLiveWindowMode() {
	}

	public void __addDistanceDelta(double distance_travelled) {
		__mDistance += distance_travelled;
	}
}
