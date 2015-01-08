package com.snobot.simulator.gui.advanced_widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.gui.Util;
import com.snobot.simulator.gui.widgetDisplays.BaseWidgetDisplay;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.SpeedController;

public class DigitalSourceGraphicDisplay extends BaseWidgetDisplay<DigitalSource>
{
	private class DigitalSourceDisplay extends JPanel
	{
		private static final int sDOT_SIZE = 30;
		
		private boolean mState;
		
		public DigitalSourceDisplay()
		{
			setPreferredSize(new Dimension(sDOT_SIZE, sDOT_SIZE));
		}
		
		public void updateDisplay(boolean aValue)
		{
			mState = aValue;
			repaint();
		}
		
		public void paint(Graphics g)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			g.setColor(mState ? Color.green : Color.red);
			g.fillOval(0, 0, sDOT_SIZE, sDOT_SIZE);
		}
	}
	
	public DigitalSourceGraphicDisplay(Map<Integer, DigitalSource> aMap) {
		super(aMap);
		setBorder(new TitledBorder("Digital IO"));
	}

	public void update(Map<Integer, DigitalSource> aMap)
	{
		for(Entry<Integer, DigitalSource> pair : aMap.entrySet())
		{
			((DigitalSourceDisplay)mWidgetMap.get(pair.getKey())).updateDisplay(pair.getValue().get());
		}
	}

	@Override
	protected DigitalSourceDisplay createWidget(Entry<Integer, DigitalSource> pair) {
		return new DigitalSourceDisplay();
	}
}
