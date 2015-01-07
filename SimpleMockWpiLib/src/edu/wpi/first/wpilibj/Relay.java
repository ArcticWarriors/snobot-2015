/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.snobot.simulator.SensorActuatorRegistry;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * Class for VEX Robotics Spike style relay outputs. Relays are intended to be
 * connected to Spikes or similar relays. The relay channels controls a pair of
 * pins that are either both off, one on, the other on, or both on. This
 * translates into two Spike outputs at 0v, one at 12v and one at 0v, one at 0v
 * and the other at 12v, or two Spike outputs at 12V. This allows off, full
 * forward, or full reverse control of motors without variable speed. It also
 * allows the two channels (forward and reverse) to be used independently for
 * something that does not care about voltage polarity (like a solenoid).
 */
public class Relay extends SensorBase implements LiveWindowSendable {

	/**
	 * This class represents errors in trying to set relay values contradictory
	 * to the direction to which the relay is set.
	 */
	public class InvalidValueException extends RuntimeException {

		/**
		 * Create a new exception with the given message
		 *
		 * @param message
		 *            the message to pass with the exception
		 */
		public InvalidValueException(String message) {
			super(message);
		}
	}

	/**
	 * The state to drive a Relay to.
	 */
	public static enum Value {
		/**
		 * value: off
		 */
		kOff(0),
		/**
		 * value: on for relays with defined direction
		 */
		kOn(1),
		/**
		 * value: forward
		 */
		kForward(2),
		/**
		 * value: reverse
		 */
		kReverse(3);

		/**
		 * The integer value representing this enumeration
		 */
		public final int value;

		private Value(int value) {
			this.value = value;
		}
	}

	/**
	 * The Direction(s) that a relay is configured to operate in.
	 */
	public static enum Direction {
		/**
		 * direction: both directions are valid
		 */

		kBoth(0),
		/**
		 * direction: Only forward is valid
		 */
		kForward(1),
		/**
		 * direction: only reverse is valid
		 */
		kReverse(2);

		/**
		 * The integer value representing this enumeration
		 */
		public final int value;

		private Direction(int value) {
			this.value = value;
		}

	}

	private final int m_channel;

	private Direction m_direction;
	private Value mState;

	/**
	 * Common relay initialization method. This code is common to all Relay
	 * constructors and initializes the relay and reserves all resources that
	 * need to be locked. Initially the relay is set to both lines at 0v.
	 */
	private void initRelay() {
		
		SensorBase.checkRelayChannel(m_channel);
		

		if(!SensorActuatorRegistry.get().register(this, m_channel))
		{
			throw new AllocationException("Digital port " + m_channel
					+ " is already allocated");
		}
	}

	/**
	 * Relay constructor given a channel.
	 *
	 * @param channel
	 *            The channel number for this relay.
	 * @param direction
	 *            The direction that the Relay object will control.
	 */
	public Relay(final int channel, Direction direction) {
		if (direction == null)
			throw new NullPointerException("Null Direction was given");
		m_channel = channel;
		m_direction = direction;
		initRelay();
		set(Value.kOff);
	}

	/**
	 * Relay constructor given a channel, allowing both directions.
	 *
	 * @param channel
	 *            The channel number for this relay.
	 */
	public Relay(final int channel) {
		this(channel, Direction.kBoth);
	}

	@Override
	public void free() {
	}

	/**
	 * Set the relay state.
	 *
	 * Valid values depend on which directions of the relay are controlled by
	 * the object.
	 *
	 * When set to kBothDirections, the relay can be set to any of the four
	 * states: 0v-0v, 12v-0v, 0v-12v, 12v-12v
	 *
	 * When set to kForwardOnly or kReverseOnly, you can specify the constant
	 * for the direction or you can simply specify kOff_val and kOn_val. Using
	 * only kOff_val and kOn_val is recommended.
	 *
	 * @param value
	 *            The state to set the relay.
	 */
	public void set(Value value) {
		ByteBuffer status = ByteBuffer.allocateDirect(4);
		status.order(ByteOrder.LITTLE_ENDIAN);

		mState = value;

		switch (value) {
		case kForward:
			if (m_direction == Direction.kReverse)
				throw new InvalidValueException(
						"A relay configured for reverse cannot be set to forward");
			break;
		case kReverse:
			if (m_direction == Direction.kForward)
				throw new InvalidValueException(
						"A relay configured for forward cannot be set to reverse");
			break;
		default:
			break;
		}
	}

	/**
	 * Get the Relay State
	 *
	 * Gets the current state of the relay.
	 *
	 * When set to kForwardOnly or kReverseOnly, value is returned as kOn/kOff
	 * not kForward/kReverse (per the recommendation in Set)
	 *
	 * @return The current state of the relay as a Relay::Value
	 */
	public Value get() {
		return mState;
	}

	/**
	 * Set the Relay Direction
	 *
	 * Changes which values the relay can be set to depending on which direction
	 * is used
	 *
	 * Valid inputs are kBothDirections, kForwardOnly, and kReverseOnly
	 *
	 * @param direction
	 *            The direction for the relay to operate in
	 */
	public void setDirection(Direction direction) {
		if (direction == null)
			throw new NullPointerException("Null Direction was given");
		if (m_direction == direction) {
			return;
		}

		free();

		m_direction = direction;

		initRelay();
	}

	/*
	 * Live Window code, only does anything if live window is activated.
	 */
	@Override
	public String getSmartDashboardType() {
		return "Relay";
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
		if (m_table != null) {
			if (get() == Value.kOn) {
				m_table.putString("Value", "On");
			} else if (get() == Value.kForward) {
				m_table.putString("Value", "Forward");
			} else if (get() == Value.kReverse) {
				m_table.putString("Value", "Reverse");
			} else {
				m_table.putString("Value", "Off");
			}
		}
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
				String val = ((String) value);
				if (val.equals("Off")) {
					set(Value.kOff);
				} else if (val.equals("On")) {
					set(Value.kOn);
				} else if (val.equals("Forward")) {
					set(Value.kForward);
				} else if (val.equals("Reverse")) {
					set(Value.kReverse);
				}
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
