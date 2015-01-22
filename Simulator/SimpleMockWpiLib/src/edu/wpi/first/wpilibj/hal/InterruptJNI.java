package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class InterruptJNI extends JNIWrapper {
	public interface InterruptJNIHandlerFunction {
		void apply(int interruptAssertedMask, Object param);
	};
	public static void initializeInterruptJVM(IntBuffer status)
    {

    }
	public static ByteBuffer initializeInterrupts(int interruptIndex, byte watcher, IntBuffer status)
    {
		return null;
    }
	public static void cleanInterrupts(ByteBuffer interrupt_pointer, IntBuffer status)
    {

    }
	public static int waitForInterrupt(ByteBuffer interrupt_pointer, double timeout, boolean ignorePrevious, IntBuffer status)
    {
		return 0;
    }
	public static void enableInterrupts(ByteBuffer interrupt_pointer, IntBuffer status)
    {

    }
	public static void disableInterrupts(ByteBuffer interrupt_pointer, IntBuffer status)
    {

    }
	public static double readRisingTimestamp(ByteBuffer interrupt_pointer, IntBuffer status)
    {
		return 0;
    }
	public static double readFallingTimestamp(ByteBuffer interrupt_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void requestInterrupts(ByteBuffer interrupt_pointer, byte routing_module, int routing_pin, byte routing_analog_trigger, IntBuffer status)
    {

    }
	public static void attachInterruptHandler(ByteBuffer interrupt_pointer, InterruptJNIHandlerFunction handler, Object param, IntBuffer status)
    {

    }
	public static void setInterruptUpSourceEdge(ByteBuffer interrupt_pointer, byte risingEdge, byte fallingEdge, IntBuffer status)
    {

    }
}
