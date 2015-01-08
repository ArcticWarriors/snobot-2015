package com.snobot.simulator.gui.widgetDisplays;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidDisplay extends BaseWidgetDisplay<Solenoid>
{
	public SolenoidDisplay(Map<Integer, Solenoid> aMap) {
		super(aMap);
		
		setBorder(new TitledBorder("Solenoids"));
	}

	public void update(Map<Integer, Solenoid> aMap)
	{
		for(Entry<Integer, Solenoid> pair : aMap.entrySet())
		{
			((JCheckBox)mWidgetMap.get(pair.getKey())).setSelected(pair.getValue().get());
		}
	}

	@Override
	protected JCheckBox createWidget(Entry<Integer, Solenoid> pair) {
		return new JCheckBox();
	}
}