package edu.wpi.first.wpilibj.internal;

import edu.wpi.first.wpilibj.HLUsageReporting;

public class LocalHLUsageReporting implements HLUsageReporting.Interface
{	
	@Override
	public void reportSmartDashboard() {
		System.out.println("Starting smart dashboard");
	}
	
	@Override
	public void reportScheduler() {
		System.out.println("Starting scheduler");
	}
	
	@Override
	public void reportPIDController(int num) {
		System.out.println("Starting PID Controller : " + num);
	}
}
