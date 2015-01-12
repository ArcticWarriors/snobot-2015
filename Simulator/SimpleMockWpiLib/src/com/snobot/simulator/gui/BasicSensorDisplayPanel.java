package com.snobot.simulator.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.gui.widget_displays.DigitalSourceDisplay;
import com.snobot.simulator.gui.widget_displays.RelayDisplay;
import com.snobot.simulator.gui.widget_displays.SolenoidDisplay;
import com.snobot.simulator.gui.widget_displays.SpeedControllerDisplay;

public class BasicSensorDisplayPanel extends JPanel 
{
	public BasicSensorDisplayPanel()
	{
		create();
	}
	
	
	private SpeedControllerDisplay mSpeedControllerPanel;
	private SolenoidDisplay mSolenoidPanel;
	private DigitalSourceDisplay mDigitalSourcePanel;
	private RelayDisplay mRelayPanel;
	
	public void create()
	{
		SensorActuatorRegistry reg = SensorActuatorRegistry.get();
		
		mSpeedControllerPanel = new SpeedControllerDisplay(reg.getSpeedControllers());
		mSolenoidPanel = new SolenoidDisplay(reg.getSolenoids());
		mDigitalSourcePanel = new DigitalSourceDisplay(reg.getDigitalSources());
		mRelayPanel = new RelayDisplay(reg.getRelays());

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(mSpeedControllerPanel);
		add(mSolenoidPanel);
		add(mDigitalSourcePanel);
		add(mRelayPanel);
	}

	public void update() 
	{
		SensorActuatorRegistry reg = SensorActuatorRegistry.get();

		mSpeedControllerPanel.update(reg.getSpeedControllers());
		mSolenoidPanel.update(reg.getSolenoids());
		mDigitalSourcePanel.update(reg.getDigitalSources());
		mRelayPanel.update(reg.getRelays());
	}
}
