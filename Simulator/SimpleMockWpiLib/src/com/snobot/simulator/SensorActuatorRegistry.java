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

	private Map<Integer, SpeedControllerWrapper> mSpeedControllerMap = new HashMap<Integer, SpeedControllerWrapper>();
	private Map<Integer, SolenoidWrapper> mSolenoidMap = new HashMap<Integer, SolenoidWrapper>();
	private Map<Integer, RelayWrapper> mRelayMap = new HashMap<Integer, RelayWrapper>();
	private Map<Integer, DigitalSourceWrapper> mDigitalInputMap = new HashMap<Integer, DigitalSourceWrapper>();
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

	public boolean register(SpeedControllerWrapper aActuator, int aPort)
	{
		return registerItem(aActuator, aPort, mSpeedControllerMap, "Speed Controller");
	}

	public boolean register(DigitalSourceWrapper aSensor, int aPort) {
		return registerItem(aSensor, aPort, mDigitalInputMap, "Digital IO");
	}
	
	public boolean register(SolenoidWrapper aActuator, int aPort)
	{
		return registerItem(aActuator, aPort, mSolenoidMap, "Solenoid");
	}
	
	public boolean register(RelayWrapper aActuator, int aPort)
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

	public Map<Integer, SpeedControllerWrapper> getSpeedControllers() {
		return mSpeedControllerMap;
	}

	public Map<Integer, SolenoidWrapper> getSolenoids() {
		return mSolenoidMap;
	}

	public Map<Integer, DigitalSourceWrapper> getDigitalSources() {
		return mDigitalInputMap;
	}

	public Map<Integer, RelayWrapper> getRelays() {
		return mRelayMap;
	}

	public Encoder getEncoder(int aPortA, int aPortB) {
		return mEncoderMap.get(new EncoderPair(aPortA, aPortB));
	}

}
