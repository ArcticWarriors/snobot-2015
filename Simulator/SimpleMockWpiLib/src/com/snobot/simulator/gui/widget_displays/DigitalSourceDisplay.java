package com.snobot.simulator.gui.widget_displays;

import java.awt.Container;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import edu.wpi.first.wpilibj.DigitalSource;


public class DigitalSourceDisplay extends BaseWidgetDisplay<DigitalSource>
{
	public DigitalSourceDisplay(Map<Integer, DigitalSource> aMap) {
		super(aMap);
		
		setBorder(new TitledBorder("Digital IO"));
	}

	public void update(Map<Integer, DigitalSource> aMap)
	{
		for(Entry<Integer, DigitalSource> pair : aMap.entrySet())
		{
			((JCheckBox)mWidgetMap.get(pair.getKey())).setSelected(pair.getValue().get());
		}
	}

	@Override
	protected Container createWidget(Entry<Integer, DigitalSource> pair) {
		return new JCheckBox();
	}
}