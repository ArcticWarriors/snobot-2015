package com.snobot.simulator.gui.widget_displays;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.RelayWrapper;


public class RelayDisplay extends BaseWidgetDisplay<RelayWrapper>
{
	public RelayDisplay(Map<Integer, RelayWrapper> aMap) {
		super(aMap);
		
		setBorder(new TitledBorder("Digital IO"));
	}

	public void update(Map<Integer, RelayWrapper> aMap)
	{
		for(Entry<Integer, RelayWrapper> pair : aMap.entrySet())
		{
			((JLabel)mWidgetMap.get(pair.getKey())).setText("" + pair.getValue().get());
		}
	}

	@Override
	protected JLabel createWidget(Entry<Integer, RelayWrapper> pair) {
		return new JLabel("NA");
	}
}