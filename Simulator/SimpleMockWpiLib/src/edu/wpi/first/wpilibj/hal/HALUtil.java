package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.snobot.simulator.RobotStateSingleton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class HALUtil extends JNIWrapper {
	public static final int NULL_PARAMETER = -1005;
	public static final int SAMPLE_RATE_TOO_HIGH = 1001;
	public static final int VOLTAGE_OUT_OF_RANGE = 1002;
	public static final int LOOP_TIMING_ERROR = 1004;
	public static final int INCOMPATIBLE_STATE = 1015;
	public static final int ANALOG_TRIGGER_PULSE_OUTPUT_ERROR = -1011;
	public static final int NO_AVAILABLE_RESOURCES = -104;
	public static final int PARAMETER_OUT_OF_RANGE = -1028;

	//public static final int SEMAPHORE_WAIT_FOREVER = -1;
	//public static final int SEMAPHORE_Q_PRIORITY = 0x01;
	

    private static final double __sWAIT_TIME = .02;
    private static float __sMatchTime = 0;

	public static ByteBuffer initializeMutexNormal()
    {
    	return null;
    }
	public static void deleteMutex(ByteBuffer sem)
    {

    }
	public static byte takeMutex(ByteBuffer sem)
    {
    	return 0;
    }
	public static ByteBuffer initializeMultiWait()
    {
		return null;
    }
	public static void deleteMultiWait(ByteBuffer sem)
    {

    }
	public static byte takeMultiWait(ByteBuffer sem, ByteBuffer m, int timeOut)
    {
    	Timer.delay(__sWAIT_TIME);
    	__sMatchTime += __sWAIT_TIME;
    	
    	RobotStateSingleton.get().updateLoopListeners();
		
    	return 0;
    }
	public static short getFPGAVersion(IntBuffer status)
    {
    	return 0;
    }
	public static int getFPGARevision(IntBuffer status)
    {
    	return 0;
    }
	public static long getFPGATime(IntBuffer status)
    {
    	return (long) (__sMatchTime * 1e6);
    }
	public static boolean getFPGAButton(IntBuffer status)
    {
		return false;
    }

	public static String getHALErrorMessage(int code)
    {
		return "";
    }
	
	public static int getHALErrno()
    {
    	return 0;
    }
	public static String getHALstrerror(int errno)
    {
		return "";
    }
	public static String getHALstrerror(){
		return getHALstrerror(getHALErrno());
	}

	public static void checkStatus(IntBuffer status)
	{
		int s = status.get(0);
		if (s < 0)
		{
			String message = getHALErrorMessage(s);
			throw new RuntimeException(" Code: " + s + ". " + message);
		} else if (s > 0) {
			String message = getHALErrorMessage(s);
			DriverStation.reportError(message, true);
		}
	}

}
