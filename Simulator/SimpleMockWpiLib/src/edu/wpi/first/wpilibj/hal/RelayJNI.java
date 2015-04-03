package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Map;

import com.snobot.simulator.RelayWrapper;
import com.snobot.simulator.SensorActuatorRegistry;

public class RelayJNI extends DIOJNI {
	

	private static RelayWrapper getWrapperFromBuffer(ByteBuffer digital_port_pointer)
	{
		int port = digital_port_pointer.get(0);
		
		Map<Integer, RelayWrapper> relays = SensorActuatorRegistry.get().getRelays();
		
		if(!relays.containsKey(port))
		{
            relays.put(port, new RelayWrapper(port));
		}
		
		return relays.get(port);
	}
	
	public static void setRelayForward(ByteBuffer digital_port_pointer, byte on, IntBuffer status)
    {
		getWrapperFromBuffer(digital_port_pointer).setRelayForwards(on == 1);
    }
	public static void setRelayReverse(ByteBuffer digital_port_pointer, byte on, IntBuffer status)
    {
		getWrapperFromBuffer(digital_port_pointer).setRelayReverse(on == 1);
    }
	public static byte getRelayForward(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static byte getRelayReverse(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		return 0;
    }
}
