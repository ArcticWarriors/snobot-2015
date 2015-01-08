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

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SpeedController;

public class RelayGraphicDisplay extends BaseWidgetDisplay<Relay>
{
	private class RelayDisplay extends JPanel
	{
		private static final int sWIDTH = 80;
		private static final int sHEIGHT = 15;
		
		private Value mValue;
		
		public RelayDisplay()
		{
			setPreferredSize(new Dimension(sWIDTH, sHEIGHT));
		}
		
		public void updateDisplay(Value value)
		{
			mValue = value;
			repaint();
		}
		
		public void paint(Graphics g)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawRect(0, 0, getWidth(), getHeight());
			
			System.out.println(mValue);
			
			switch(mValue)
			{
			case kOff:
				break;
			case kOn:
				g.setColor(Color.green);
				g.fillRect(0, 0, sWIDTH / 2, sHEIGHT);
				
				g.setColor(Color.red);
				g.fillRect(sWIDTH / 2, 0, sWIDTH, sHEIGHT);
				break;
			case kForward:
				g.setColor(Color.green);
				g.fillRect(0, 0, sWIDTH, sHEIGHT);
				break;
			case kReverse:
				g.setColor(Color.red);
				g.fillRect(0, 0, sWIDTH, sHEIGHT);
				break;
			}
		}
	}
	
	public RelayGraphicDisplay(Map<Integer, Relay> aMap) {
		super(aMap);
		setBorder(new TitledBorder("Relay"));
	}

	public void update(Map<Integer, Relay> aMap)
	{
		for(Entry<Integer, Relay> pair : aMap.entrySet())
		{
			((RelayDisplay)mWidgetMap.get(pair.getKey())).updateDisplay(pair.getValue().get());
		}
	}

	@Override
	protected RelayDisplay createWidget(Entry<Integer, Relay> pair) {
		return new RelayDisplay();
	}
}
