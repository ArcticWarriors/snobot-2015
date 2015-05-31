package com.snobot.simulator.gui.advanced_widgets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.gui.Util;

import edu.wpi.first.wpilibj.SpeedController;

public class SpeedControllerGraphicDisplay extends BaseWidgetDisplay<SpeedControllerWrapper>
{
	private class MotorDisplay extends JPanel
	{
		private static final int sDOT_SIZE = 30;
		
		private double mMotorSpeed;
		
		public MotorDisplay()
		{
			setPreferredSize(new Dimension(sDOT_SIZE, sDOT_SIZE));
		}
		
		public void updateDisplay(double aValue)
		{
			mMotorSpeed = aValue;
			repaint();
		}
		
		public void paint(Graphics g)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			g.setColor(Util.getMotorColor(mMotorSpeed));
			g.fillOval(0, 0, sDOT_SIZE, sDOT_SIZE);
		}
	}
	
	public SpeedControllerGraphicDisplay(Map<Integer, SpeedControllerWrapper> aMap) {
		super(aMap);
		setBorder(new TitledBorder("Speed Controllers"));
	}

	public void update(Map<Integer, SpeedControllerWrapper> aMap)
	{
		for(Entry<Integer, SpeedControllerWrapper> pair : aMap.entrySet())
		{
			((MotorDisplay)mWidgetMap.get(pair.getKey())).updateDisplay(pair.getValue().get());
		}
	}

	@Override
	protected MotorDisplay createWidget(Entry<Integer, SpeedControllerWrapper> pair) {
		return new MotorDisplay();
	}
}
