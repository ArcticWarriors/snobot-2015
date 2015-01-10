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
 * DoubleSolenoid class for running 2 channels of high voltage Digital Output.
 *
 * The DoubleSolenoid class is typically used for pneumatics solenoids that
 * have two positions controlled by two separate channels.
 */
public class DoubleSolenoid extends SolenoidBase implements LiveWindowSendable {

    /**
     * Possible values for a DoubleSolenoid
     */
    public static class Value {

        public final int value;
        public static final int kOff_val = 0;
        public static final int kForward_val = 1;
        public static final int kReverse_val = 2;
        public static final Value kOff = new Value(kOff_val);
        public static final Value kForward = new Value(kForward_val);
        public static final Value kReverse = new Value(kReverse_val);

        private Value(int value) {
            this.value = value;
        }
    }

    private Solenoid fowardSolenoid;
    private Solenoid reverseSolenoid;

    /**
     * Common function to implement constructor behavior.
     * @param reverseChannel 
     * @param forwardChannel 
     */
    private synchronized void initSolenoid(int forwardChannel, int reverseChannel) {
        checkSolenoidModule(m_moduleNumber);
        checkSolenoidChannel(forwardChannel);
        checkSolenoidChannel(reverseChannel);

    	fowardSolenoid = new Solenoid(forwardChannel);
    	reverseSolenoid = new Solenoid(reverseChannel);
    }

    /**
     * Constructor.
     * Uses the default PCM ID of 0
     * @param forwardChannel The forward channel number on the PCM.
     * @param reverseChannel The reverse channel number on the PCM.
     */
    public DoubleSolenoid(final int forwardChannel, final int reverseChannel) {
        super(getDefaultSolenoidModule());
        initSolenoid(forwardChannel, reverseChannel);
    }

    /**
     * Destructor.
     */
    public synchronized void free() {
    }

    /**
     * Set the value of a solenoid.
     *
     * @param value The value to set (Off, Forward, Reverse)
     */
    public void set(final Value value) {

        switch (value.value) {
        case Value.kOff_val:
        	fowardSolenoid.set(false);
        	reverseSolenoid.set(false);
            break;
        case Value.kForward_val:
        	fowardSolenoid.set(true);
        	reverseSolenoid.set(false);
            break;
        case Value.kReverse_val:
        	fowardSolenoid.set(false);
        	reverseSolenoid.set(true);
            break;
        }
    }

    /**
     * Read the current value of the solenoid.
     *
     * @return The current value of the solenoid.
     */
    public Value get() {
    	if(fowardSolenoid.get() == false && reverseSolenoid.get() == false)
    	{
    		return new Value(Value.kOff_val);
    	}
    	else if(fowardSolenoid.get() == true && reverseSolenoid.get() == false)
    	{
    		return new Value(Value.kForward_val);
    	}
    	else if(fowardSolenoid.get() == false && reverseSolenoid.get() == true)
    	{
    		return new Value(Value.kReverse_val);
    	}
        return new Value(Value.kOff_val);
    }
	/**
	 * Check if the forward solenoid is blacklisted.
	 *		If a solenoid is shorted, it is added to the blacklist and
	 *		disabled until power cycle, or until faults are cleared.
	 *		@see #clearAllPCMStickyFaults()
	 *
	 * @return If solenoid is disabled due to short.
	 */
	public boolean isFwdSolenoidBlackListed() {
		return false;
	}
	/**
	 * Check if the reverse solenoid is blacklisted.
	 *		If a solenoid is shorted, it is added to the blacklist and
	 *		disabled until power cycle, or until faults are cleared.
	 *		@see #clearAllPCMStickyFaults()
	 *
	 * @return If solenoid is disabled due to short.
	 */
	public boolean isRevSolenoidBlackListed() {
		return false;
	}

    /*
     * Live Window code, only does anything if live window is activated.
     */
    public String getSmartDashboardType() {
        return "Double Solenoid";
    }
    private ITable m_table;
    private ITableListener m_table_listener;

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
            //TODO: this is bad
            m_table.putString("Value", (get() == Value.kForward ? "Forward" : (get() == Value.kReverse ? "Reverse" : "Off")));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void startLiveWindowMode() {
        set(Value.kOff); // Stop for safety
        m_table_listener = new ITableListener() {
            public void valueChanged(ITable itable, String key, Object value, boolean bln) {
                //TODO: this is bad also
                if (value.toString().equals("Reverse"))
                    set(Value.kReverse);
                else if (value.toString().equals("Forward"))
                    set(Value.kForward);
                else
                    set(Value.kOff);
            }
        };
        m_table.addTableListener("Value", m_table_listener, true);
    }

    /**
     * {@inheritDoc}
     */
    public void stopLiveWindowMode() {
        set(Value.kOff); // Stop for safety
        // TODO: Broken, should only remove the listener from "Value" only.
        m_table.removeTableListener(m_table_listener);
    }
    
}
