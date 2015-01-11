package com.snobot.simulator;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class SensorActuatorRegistry 
{
	
	private static SensorActuatorRegistry mInstance = new SensorActuatorRegistry();

	private Map<Integer, SpeedController> mSpeedControllerMap = new HashMap<Integer, SpeedController>();
	private Map<Integer, Solenoid> mSolenoidMap = new HashMap<Integer, Solenoid>();
	private Map<Integer, Relay> mRelayMap = new HashMap<Integer, Relay>();
	private Map<Integer, DigitalSource> mDigitalInputMap = new HashMap<Integer, DigitalSource>();
	private Map<EncoderPair, Encoder> mEncoderMap = new HashMap<EncoderPair, Encoder>();
	
	private SensorActuatorRegistry()
	{
		
	}
	
	public static SensorActuatorRegistry get()
	{
		return mInstance;
	}
	
	public <ItemType> boolean registerItem(ItemType aItem, int aPort, Map<Integer, ItemType> aMap, String aMessage)
	{
		if(aMap.containsKey(aPort) || aPort < 0)
		{
			return false;
		}
		aMap.put(aPort, aItem);
		
		return true;
	}

	public boolean register(SpeedController aActuator, int aPort)
	{
		return registerItem(aActuator, aPort, mSpeedControllerMap, "Speed Controller");
	}

	public boolean register(DigitalSource aSensor, int aPort) {
		return registerItem(aSensor, aPort, mDigitalInputMap, "Digital IO");
	}
	
	public boolean register(Solenoid aActuator, int aPort)
	{
		return registerItem(aActuator, aPort, mSolenoidMap, "Solenoid");
	}
	
	public boolean register(Relay aActuator, int aPort)
	{
		return registerItem(aActuator, aPort, mRelayMap, "Relay");
	}
	
	public boolean register(Encoder aEncoder, EncoderPair aPorts)
	{

		if(mEncoderMap.containsKey(aPorts))
		{
			return false;
		}
		mEncoderMap.put(aPorts, aEncoder);
		
		return true;
	}

	public Map<Integer, SpeedController> getSpeedControllers() {
		return mSpeedControllerMap;
	}

	public Map<Integer, Solenoid> getSolenoids() {
		return mSolenoidMap;
	}

	public Map<Integer, DigitalSource> getDigitalSources() {
		return mDigitalInputMap;
	}

	public Map<Integer, Relay> getRelays() {
		return mRelayMap;
	}

	public Encoder getEncoder(int aPortA, int aPortB) {
		System.out.println(mEncoderMap);
		return mEncoderMap.get(new EncoderPair(aPortA, aPortB));
	}

}
