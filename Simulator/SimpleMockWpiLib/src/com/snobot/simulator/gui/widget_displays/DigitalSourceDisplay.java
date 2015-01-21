package com.snobot.simulator.gui.widget_displays;

import java.awt.Container;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.DigitalSourceWrapper;

import edu.wpi.first.wpilibj.DigitalSource;


public class DigitalSourceDisplay extends BaseWidgetDisplay<DigitalSourceWrapper>
{
	public DigitalSourceDisplay(Map<Integer, DigitalSourceWrapper> aMap) {
		super(aMap);
		
		setBorder(new TitledBorder("Digital IO"));
	}

	public void update(Map<Integer, DigitalSourceWrapper> aMap)
	{
		for(Entry<Integer, DigitalSourceWrapper> pair : aMap.entrySet())
		{
//			((JCheckBox)mWidgetMap.get(pair.getKey())).setSelected(pair.getValue().get());
		}
	}

	@Override
	protected Container createWidget(Entry<Integer, DigitalSourceWrapper> pair) {
		return new JCheckBox();
	}
}