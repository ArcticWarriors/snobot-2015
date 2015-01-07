package edu.wpi.first.wpilibj;

import com.snobot.simulator.SensorActuatorRegistry;

import edu.wpi.first.wpilibj.util.AllocationException;

public class DigitalSource extends SensorBase {
	protected int m_channel;


	protected void initDigitalPort(int channel, boolean input) {

		m_channel = channel;

		checkDigitalChannel(channel);
		if(!SensorActuatorRegistry.get().register(this, channel))
		{
			throw new AllocationException("Digital port " + m_channel
					+ " is already allocated");
		}
	}


	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}
}
