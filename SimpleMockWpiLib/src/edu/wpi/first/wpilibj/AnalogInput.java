/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.						*/
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.															   */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;



//import com.sun.jna.Pointer;


import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * Analog channel class.
 *
 * Each analog channel is read from hardware as a 12-bit number representing
 * 0V to 5V.
 *
 * Connected to each analog channel is an averaging and oversampling engine.
 * This engine accumulates the specified ( by setAverageBits() and
 * setOversampleBits() ) number of samples before returning a new value. This is
 * not a sliding window average. The only difference between the oversampled
 * samples and the averaged samples is that the oversampled samples are simply
 * accumulated effectively increasing the resolution, while the averaged samples
 * are divided by the number of samples to retain the resolution, but get more
 * stable values.
 */
public class AnalogInput extends SensorBase implements LiveWindowSendable {

	private static final int kAccumulatorSlot = 1;
	private int m_channel;
	private static final int[] kAccumulatorChannels = { 0, 1 };
	@SuppressWarnings("unused")
	private long m_accumulatorOffset;

	/**
	 * Construct an analog channel.
	 *
	 * @param channel The channel number to represent. 0-3 are on-board 4-7 are on the MXP port.
	 */
	public AnalogInput(final int channel) {
		m_channel = channel;
		
		checkAnalogInputChannel(m_channel);

//		if (AnalogJNI.checkAnalogInputChannel(channel) == 0) {
//			throw new AllocationException("Analog input channel " + m_channel
//					+ " cannot be allocated. Channel is not present.");
//		}
//		try {
//			channels.allocate(channel);
//		} catch (CheckedAllocationException e) {
//			throw new AllocationException("Analog input channel " + m_channel
//					 + " is already allocated");
//		}
//
//		ByteBuffer port_pointer = AnalogJNI.getPort((byte) channel);
//		ByteBuffer status = ByteBuffer.allocateDirect(4);
//		// set the byte order
//		status.order(ByteOrder.LITTLE_ENDIAN);
//		m_port = AnalogJNI.initializeAnalogInputPort(port_pointer, status.asIntBuffer());
//		HALUtil.checkStatus(status.asIntBuffer());
//
//		LiveWindow.addSensor("AnalogInput", channel, this);
//		UsageReporting.report(tResourceType.kResourceType_AnalogChannel, channel);
	}

	/**
	 * Channel destructor.
	 */
	public void free() {
	}

	/**
	 * Get a sample straight from this channel. The sample is a 12-bit value
	 * representing the 0V to 5V range of the A/D converter. The units are in
	 * A/D converter codes. Use GetVoltage() to get the analog value in
	 * calibrated units.
	 *
	 * @return A sample straight from this channel.
	 */
	public int getValue() {
		return 0;
	}

	/**
	 * Get a sample from the output of the oversample and average engine for
	 * this channel. The sample is 12-bit + the bits configured in
	 * SetOversampleBits(). The value configured in setAverageBits() will cause
	 * this value to be averaged 2^bits number of samples. This is not a
	 * sliding window. The sample will not change until 2^(OversampleBits +
	 * AverageBits) samples have been acquired from this channel. Use
	 * getAverageVoltage() to get the analog value in calibrated units.
	 *
	 * @return A sample from the oversample and average engine for this channel.
	 */
	public int getAverageValue() {
		return 0;
	}

	/**
	 * Get a scaled sample straight from this channel. The value is scaled to
	 * units of Volts using the calibrated scaling data from getLSBWeight() and
	 * getOffset().
	 *
	 * @return A scaled sample straight from this channel.
	 */
	public double getVoltage() {
		return 0;
	}

	/**
	 * Get a scaled sample from the output of the oversample and average engine
	 * for this channel. The value is scaled to units of Volts using the
	 * calibrated scaling data from getLSBWeight() and getOffset(). Using
	 * oversampling will cause this value to be higher resolution, but it will
	 * update more slowly. Using averaging will cause this value to be more
	 * stable, but it will update more slowly.
	 *
	 * @return A scaled sample from the output of the oversample and average
	 *		 engine for this channel.
	 */
	public double getAverageVoltage() {
		return 0;
	}

	/**
	 * Get the factory scaling least significant bit weight constant. The least
	 * significant bit weight constant for the channel that was calibrated in
	 * manufacturing and stored in an eeprom.
	 *
	 * Volts = ((LSB_Weight * 1e-9) * raw) - (Offset * 1e-9)
	 *
	 * @return Least significant bit weight.
	 */
	public long getLSBWeight() {
		return 0;
	}

	/**
	 * Get the factory scaling offset constant. The offset constant for the
	 * channel that was calibrated in manufacturing and stored in an eeprom.
	 *
	 * Volts = ((LSB_Weight * 1e-9) * raw) - (Offset * 1e-9)
	 *
	 * @return Offset constant.
	 */
	public int getOffset() {
		return 0;
	}

	/**
	 * Get the channel number.
	 *
	 * @return The channel number.
	 */
	public int getChannel() {
		return m_channel;
	}

	/**
	 * Set the number of averaging bits. This sets the number of averaging bits.
	 * The actual number of averaged samples is 2^bits. The averaging is done
	 * automatically in the FPGA.
	 *
	 * @param bits
	 *			The number of averaging bits.
	 */
	public void setAverageBits(final int bits) {
	}

	/**
	 * Get the number of averaging bits. This gets the number of averaging bits
	 * from the FPGA. The actual number of averaged samples is 2^bits. The
	 * averaging is done automatically in the FPGA.
	 *
	 * @return The number of averaging bits.
	 */
	public int getAverageBits() {
		return 0;
	}

	/**
	 * Set the number of oversample bits. This sets the number of oversample
	 * bits. The actual number of oversampled values is 2^bits. The
	 * oversampling is done automatically in the FPGA.
	 *
	 * @param bits
	 *			The number of oversample bits.
	 */
	public void setOversampleBits(final int bits) {
	}

	/**
	 * Get the number of oversample bits. This gets the number of oversample
	 * bits from the FPGA. The actual number of oversampled values is 2^bits.
	 * The oversampling is done automatically in the FPGA.
	 *
	 * @return The number of oversample bits.
	 */
	public int getOversampleBits() {
		return 0;
	}

	/**
	 * Initialize the accumulator.
	 */
	public void initAccumulator() {
		if (!isAccumulatorChannel()) {
			throw new AllocationException(
					"Accumulators are only available on slot "
							+ kAccumulatorSlot + " on channels "
							+ kAccumulatorChannels[0] + ","
							+ kAccumulatorChannels[1]);
		}
		m_accumulatorOffset = 0;
	}

	/**
	 * Set an initial value for the accumulator.
	 *
	 * This will be added to all values returned to the user.
	 *
	 * @param initialValue
	 *			The value that the accumulator should start from when reset.
	 */
	public void setAccumulatorInitialValue(long initialValue) {
		m_accumulatorOffset = initialValue;
	}

	/**
	 * Resets the accumulator to the initial value.
	 */
	public void resetAccumulator() {

	}

	/**
	 * Set the center value of the accumulator.
	 *
	 * The center value is subtracted from each A/D value before it is added to
	 * the accumulator. This is used for the center value of devices like gyros
	 * and accelerometers to take the device offset
	 * into account when integrating.
	 *
	 * This center value is based on the output of the oversampled and averaged
	 * source the accumulator channel. Because of this, any non-zero oversample bits will
	 * affect the size of the value for this field.
	 */
	public void setAccumulatorCenter(int center) {
	}

	/**
	 * Set the accumulator's deadband.
	 * @param deadband The deadband size in ADC codes (12-bit value)
	 */
	public void setAccumulatorDeadband(int deadband) {
	}

	/**
	 * Read the accumulated value.
	 *
	 * Read the value that has been accumulating. The accumulator
	 * is attached after the oversample and average engine.
	 *
	 * @return The 64-bit value accumulated since the last Reset().
	 */
	public long getAccumulatorValue() {
		return 0;
	}

	/**
	 * Read the number of accumulated values.
	 *
	 * Read the count of the accumulated values since the accumulator was last
	 * Reset().
	 *
	 * @return The number of times samples from the channel were accumulated.
	 */
	public long getAccumulatorCount() {
		return 0;
	}

	/**
	 * Is the channel attached to an accumulator.
	 *
	 * @return The analog channel is attached to an accumulator.
	 */
	public boolean isAccumulatorChannel() {
		for (int i = 0; i < kAccumulatorChannels.length; i++) {
			if (m_channel == kAccumulatorChannels[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Set the sample rate per channel.
	 *
	 * This is a global setting for all channels.
	 * The maximum rate is 500kS/s divided by the number of channels in use.
	 * This is 62500 samples/s per channel if all 8 channels are used.
	 * @param samplesPerSecond The number of samples per second.
	 */
	public static void setGlobalSampleRate(final double samplesPerSecond) {
	}

	/**
	 * Get the current sample rate.
	 *
	 * This assumes one entry in the scan list. This is a global setting for
	 * all channels.
	 *
	 * @return Sample rate.
	 */
	public static double getGlobalSampleRate() {
		return 0;
	}

	/**
	 * Get the average voltage for use with PIDController
	 *
	 * @return the average voltage
	 */
	public double pidGet() {
		return getAverageVoltage();
	}

	/**
	 * Live Window code, only does anything if live window is activated.
	 */
	public String getSmartDashboardType() {
		return "Analog Input";
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
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Value", getAverageVoltage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ITable getTable() {
		return m_table;
	}

	/**
	 * Analog Channels don't have to do anything special when entering the
	 * LiveWindow. {@inheritDoc}
	 */
	public void startLiveWindowMode() {
	}

	/**
	 * Analog Channels don't have to do anything special when exiting the
	 * LiveWindow. {@inheritDoc}
	 */
	public void stopLiveWindowMode() {
	}
}
