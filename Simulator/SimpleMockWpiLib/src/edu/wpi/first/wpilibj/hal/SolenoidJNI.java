package edu.wpi.first.wpilibj.hal;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SolenoidWrapper;
import com.snobot.simulator.SpeedControllerWrapper;

public class SolenoidJNI extends JNIWrapper {
	
	public static ByteBuffer initializeSolenoidPort(ByteBuffer portPointer, IntBuffer status)
    {
		int pin = portPointer.get(0);
		System.out.println("Allocating Solenoid : " + pin);
		SolenoidWrapper wrapper = new SolenoidWrapper();
		SensorActuatorRegistry.get().register(wrapper, pin);
		
		return portPointer;
    }
	public static void setSolenoid(ByteBuffer port, byte on, IntBuffer status)
    {

    }
	public static byte getSolenoid(ByteBuffer port, IntBuffer status)
    {
		return 0;
    }

	public static byte getPCMSolenoidBlackList(ByteBuffer pcm_pointer, IntBuffer status)
    {
		return 0;
    }
	public static boolean getPCMSolenoidVoltageStickyFault(ByteBuffer pcm_pointer, IntBuffer status)
    {
		return false;
    }
	public static boolean getPCMSolenoidVoltageFault(ByteBuffer pcm_pointer, IntBuffer status)
    {
		return false;
    }
	public static void clearAllPCMStickyFaults(ByteBuffer pcm_pointer, IntBuffer status)
    {

    }

	private static SolenoidWrapper getWrapperFromBuffer(ByteBuffer digital_port_pointer)
	{
		int port = digital_port_pointer.get(0);
		return SensorActuatorRegistry.get().getSolenoids().get(port);
	}
	
	public static void __setSolenoid(ByteBuffer m_solenoid_port, boolean on) {
		getWrapperFromBuffer(m_solenoid_port).set(on);
	}
	
	public static boolean __getSolenoid(ByteBuffer m_solenoid_port) {
		return getWrapperFromBuffer(m_solenoid_port).get();
	}
}
