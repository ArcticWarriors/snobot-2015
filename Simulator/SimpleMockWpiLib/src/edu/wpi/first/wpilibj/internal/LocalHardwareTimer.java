package edu.wpi.first.wpilibj.internal;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Timer.Interface;

public class LocalHardwareTimer implements Timer.StaticInterface {
    /**
     * Pause the thread for a specified time. Pause the execution of the
     * thread for a specified period of time given in seconds. Motors will
     * continue to run at their last assigned values, and sensors will continue
     * to update. Only the task containing the wait will pause until the wait
     * time is expired.
     *
     * @param seconds Length of time to pause
     */
	@Override
	public void delay(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1e3));
        } catch (final InterruptedException e) {
        }
	}

    /**
     * Return the system clock time in seconds. Return the time from the
     * FPGA hardware clock in seconds since the FPGA started.
     *
     * @return Robot running time in seconds.
     */
	@Override
	public double getFPGATimestamp() {
		return System.currentTimeMillis() / 1.0e3;
	}

	@Override
	public double getMatchTime() {
		return DriverStation.getInstance().getMatchTime();
	}

	@Override
	public Interface newTimer() {
		// TODO Auto-generated method stub
		return null;
	}
}
