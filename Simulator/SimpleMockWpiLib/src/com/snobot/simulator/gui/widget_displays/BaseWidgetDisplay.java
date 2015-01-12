package com.snobot.simulator.gui.widget_displays;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class BaseWidgetDisplay<ItemType> extends JPanel
{

	protected Map<Integer, Container> mWidgetMap = new HashMap<>();
	
	public BaseWidgetDisplay(Map<Integer, ItemType> aMap) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for(Entry<Integer, ItemType> pair : aMap.entrySet())
		{
			Container panelPair = createWidget(pair);
			mWidgetMap.put(pair.getKey(), panelPair);

			JPanel panel = new JPanel();
			panel.add(new JLabel("" + pair.getKey()));
			panel.add(panelPair);
			
			add(panel);
		}
	}
	
	protected abstract Container createWidget(Entry<Integer, ItemType> pair);
	
	public abstract void update(Map<Integer, ItemType> aMap);
}
