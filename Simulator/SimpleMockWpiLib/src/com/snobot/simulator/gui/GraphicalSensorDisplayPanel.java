package com.snobot.simulator.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.gui.advanced_widgets.AnalogOutputDisplay;
import com.snobot.simulator.gui.advanced_widgets.DigitalSourceGraphicDisplay;
import com.snobot.simulator.gui.advanced_widgets.RelayGraphicDisplay;
import com.snobot.simulator.gui.advanced_widgets.SolenoidGraphicDisplay;
import com.snobot.simulator.gui.advanced_widgets.SpeedControllerGraphicDisplay;

public class GraphicalSensorDisplayPanel extends JPanel 
{
	public GraphicalSensorDisplayPanel()
	{
		create();
	}
	
	
	private SpeedControllerGraphicDisplay mSpeedControllerPanel;
	private SolenoidGraphicDisplay mSolenoidPanel;
	private DigitalSourceGraphicDisplay mDigitalSourcePanel;
	private RelayGraphicDisplay mRelayPanel;
	private AnalogOutputDisplay mAnalogPanel;
	
	public void create()
	{
		SensorActuatorRegistry reg = SensorActuatorRegistry.get();
		
		mSpeedControllerPanel = new SpeedControllerGraphicDisplay(reg.getSpeedControllers());
		mSolenoidPanel = new SolenoidGraphicDisplay(reg.getSolenoids());
		mDigitalSourcePanel = new DigitalSourceGraphicDisplay(reg.getDigitalSources());
		mRelayPanel = new RelayGraphicDisplay(reg.getRelays());
		mAnalogPanel = new AnalogOutputDisplay(reg.getAnalog());

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if(!mSpeedControllerPanel.isEmpty())
		{
			add(mSpeedControllerPanel);
		}
		if(!mSolenoidPanel.isEmpty())
		{
			add(mSolenoidPanel);
		}
		if(!mDigitalSourcePanel.isEmpty())
		{
			add(mDigitalSourcePanel);
		}
		if(!mRelayPanel.isEmpty())
		{
			add(mRelayPanel);
		}
		if(!mAnalogPanel.isEmpty())
		{
			add(mAnalogPanel);
		}
	}

	public void update() 
	{
		SensorActuatorRegistry reg = SensorActuatorRegistry.get();

		mSpeedControllerPanel.update(reg.getSpeedControllers());
		mSolenoidPanel.update(reg.getSolenoids());
		mDigitalSourcePanel.update(reg.getDigitalSources());
		mRelayPanel.update(reg.getRelays());
		mAnalogPanel.update(reg.getAnalog());
	}
}
