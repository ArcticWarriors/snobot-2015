/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * Class to write digital outputs. This class will write digital outputs. Other
 * devices that are implemented elsewhere will automatically allocate digital
 * inputs and outputs as required.
 */
public class DigitalOutput extends DigitalSource implements LiveWindowSendable {

	/**
	 * Create an instance of a digital output. Create an instance of a digital
	 * output given a channel.
     *
	 * @param channel
	 *            the DIO channel to use for the digital output. 0-9 are on-board, 10-25 are on the MXP
	 */
	public DigitalOutput(int channel) {
		initDigitalPort(channel, false);
	}

	/**
	 * Set the value of a digital output.
	 *
	 * @param value
	 *            true is on, off is false
	 */
	public void set(boolean value) {
	}

	/**
	 * @return The GPIO channel number that this object represents.
	 */
	public int getChannel() {
		return m_channel;
	}

	/**
	 * Generate a single pulse. Write a pulse to the specified digital output
	 * channel. There can only be a single pulse going at any time.
	 *
	 * @param channel
	 *            The channel to pulse.
	 * @param pulseLength
	 *            The length of the pulse.
	 */
	public void pulse(final int channel, final float pulseLength) {
	}

	/**
	 * @deprecated Generate a single pulse. Write a pulse to the specified
	 *             digital output channel. There can only be a single pulse
	 *             going at any time.
	 *
	 * @param channel
	 *            The channel to pulse.
	 * @param pulseLength
	 *            The length of the pulse.
	 */
	@Deprecated
	public void pulse(final int channel, final int pulseLength) {
	}

	/**
	 * Determine if the pulse is still going. Determine if a previously started
	 * pulse is still going.
	 *
	 * @return true if pulsing
	 */
	public boolean isPulsing() {
		return false;
	}

	/**
	 * Change the PWM frequency of the PWM output on a Digital Output line.
	 *
	 * The valid range is from 0.6 Hz to 19 kHz. The frequency resolution is
	 * logarithmic.
	 *
	 * There is only one PWM frequency for all channnels.
	 *
	 * @param rate The frequency to output all digital output PWM signals.
	 */
	public void setPWMRate(double rate) {
	}

	/**
	 * Enable a PWM Output on this line.
	 *
	 * Allocate one of the 6 DO PWM generator resources.
	 *
	 * Supply the initial duty-cycle to output so as to avoid a glitch when
	 * first starting.
	 *
	 * The resolution of the duty cycle is 8-bit for low frequencies (1kHz or
	 * less) but is reduced the higher the frequency of the PWM signal is.
	 *
	 * @param initialDutyCycle
	 *            The duty-cycle to start generating. [0..1]
	 */
	public void enablePWM(double initialDutyCycle) {
	}

	/**
	 * Change this line from a PWM output back to a static Digital Output line.
	 *
	 * Free up one of the 6 DO PWM generator resources that were in use.
	 */
	public void disablePWM() {
	}

	/**
	 * Change the duty-cycle that is being generated on the line.
	 *
	 * The resolution of the duty cycle is 8-bit for low frequencies (1kHz or
	 * less) but is reduced the higher the frequency of the PWM signal is.
	 *
	 * @param dutyCycle
	 *            The duty-cycle to change to. [0..1]
	 */
	public void updateDutyCycle(double dutyCycle) {
	}


	/*
	 * Live Window code, only does anything if live window is activated.
	 */
	@Override
	public String getSmartDashboardType() {
		return "Digital Output";
	}

	private ITable m_table;
	private ITableListener m_table_listener;

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
		// TODO: Put current value.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startLiveWindowMode() {
		m_table_listener = new ITableListener() {
			@Override
			public void valueChanged(ITable itable, String key, Object value,
					boolean bln) {
				set(((Boolean) value).booleanValue());
			}
		};
		m_table.addTableListener("Value", m_table_listener, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopLiveWindowMode() {
		// TODO: Broken, should only remove the listener from "Value" only.
		m_table.removeTableListener(m_table_listener);
	}
}
