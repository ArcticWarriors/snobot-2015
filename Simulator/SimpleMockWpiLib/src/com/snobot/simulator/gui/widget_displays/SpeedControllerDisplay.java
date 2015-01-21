package com.snobot.simulator.gui.widget_displays;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.SpeedControllerWrapper;

import edu.wpi.first.wpilibj.SpeedController;


public class SpeedControllerDisplay extends BaseWidgetDisplay<SpeedControllerWrapper>
{
	public SpeedControllerDisplay(Map<Integer, SpeedControllerWrapper> aMap) {
		super(aMap);
		setBorder(new TitledBorder("Speed Controllers"));
	}

	public void update(Map<Integer, SpeedControllerWrapper> aMap)
	{
		for(Entry<Integer, SpeedControllerWrapper> pair : aMap.entrySet())
		{
			((JLabel)mWidgetMap.get(pair.getKey())).setText("" + pair.getValue().get());
		}
	}

	@Override
	protected JLabel createWidget(Entry<Integer, SpeedControllerWrapper> pair) {
		return new JLabel("" + pair.getValue().get());
	}
}