package edu.wpi.first.wpilibj.hal;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SolenoidWrapper;

public class SolenoidJNI extends JNIWrapper {

	public static ByteBuffer initializeSolenoidPort(ByteBuffer portPointer, IntBuffer status)
    {
		int pin = portPointer.get(0);
		
		SolenoidWrapper wrapper = new SolenoidWrapper();
		SensorActuatorRegistry.get().register(wrapper, pin);
		
		return portPointer;
    }
	
	
	public static ByteBuffer __initializeConstructorSolenoidPort(ByteBuffer port, IntBuffer status) {
		
		return port;
	}
	
	
	public static void setSolenoid(ByteBuffer port, byte on, IntBuffer status)
    {
		getWrapperFromBuffer(port).set(on > 0);
    }
	public static byte getSolenoid(ByteBuffer port, IntBuffer status)
    {
		return (byte) (getWrapperFromBuffer(port).get() ? 1 : 0);
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
}
