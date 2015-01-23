package edu.wpi.first.wpilibj.internal;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Timer.Interface;

public class LocalHardwareTimer implements Timer.StaticInterface
{

	@Override
	public Interface newTimer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMatchTime() {
		return DriverStation.getInstance().getMatchTime();
	}
	
	@Override
	public double getFPGATimestamp() {
		return System.currentTimeMillis() / 1.0e3;
	}
	
	@Override
	public void delay(double seconds) {
        try 
        {
            Thread.sleep((long) (seconds * 1e3));
        } 
        catch (final InterruptedException e) {
        }
	}
}
