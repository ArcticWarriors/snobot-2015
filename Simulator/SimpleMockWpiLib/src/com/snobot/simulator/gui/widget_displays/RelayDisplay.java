package com.snobot.simulator.gui.widget_displays;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import edu.wpi.first.wpilibj.Relay;


public class RelayDisplay extends BaseWidgetDisplay<Relay>
{
	public RelayDisplay(Map<Integer, Relay> aMap) {
		super(aMap);
		
		setBorder(new TitledBorder("Digital IO"));
	}

	public void update(Map<Integer, Relay> aMap)
	{
		for(Entry<Integer, Relay> pair : aMap.entrySet())
		{
			((JLabel)mWidgetMap.get(pair.getKey())).setText("" + pair.getValue().get());
		}
	}

	@Override
	protected JLabel createWidget(Entry<Integer, Relay> pair) {
		return new JLabel("NA");
	}
}