package com.snobot.simulator.gui.widget_displays;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.SolenoidWrapper;

public class SolenoidDisplay extends BaseWidgetDisplay<SolenoidWrapper>
{
	public SolenoidDisplay(Map<Integer, SolenoidWrapper> aMap) {
		super(aMap);
		
		setBorder(new TitledBorder("Solenoids"));
	}

	public void update(Map<Integer, SolenoidWrapper> aMap)
	{
		for(Entry<Integer, SolenoidWrapper> pair : aMap.entrySet())
		{
			((JCheckBox)mWidgetMap.get(pair.getKey())).setSelected(pair.getValue().get());
		}
	}

	@Override
	protected JCheckBox createWidget(Entry<Integer, SolenoidWrapper> pair) {
		return new JCheckBox();
	}
}