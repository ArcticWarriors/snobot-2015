package com.snobot.simulator.gui.widgetDisplays;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import edu.wpi.first.wpilibj.SpeedController;


public class SpeedControllerDisplay extends BaseWidgetDisplay<SpeedController>
{
	public SpeedControllerDisplay(Map<Integer, SpeedController> aMap) {
		super(aMap);
		setBorder(new TitledBorder("Speed Controllers"));
	}

	public void update(Map<Integer, SpeedController> aMap)
	{
		for(Entry<Integer, SpeedController> pair : aMap.entrySet())
		{
			((JLabel)mWidgetMap.get(pair.getKey())).setText("" + pair.getValue().get());
		}
	}

	@Override
	protected JLabel createWidget(Entry<Integer, SpeedController> pair) {
		return new JLabel("" + pair.getValue().get());
	}
}