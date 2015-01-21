package com.snobot.simulator.gui.advanced_widgets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.SolenoidWrapper;
import com.snobot.simulator.gui.widget_displays.BaseWidgetDisplay;

import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidGraphicDisplay extends BaseWidgetDisplay<SolenoidWrapper>
{
	private class SolenoidDisplay extends JPanel
	{
		private static final int sWIDTH = 80;
		private static final int sHEIGHT = 15;
		private static final int sCYLINDER_WIDTH = sWIDTH / 2;
		
		private static final int sPOLE_SIZE = 2;
		private static final int sPLUNGER_SIZE = 5;
		private static final int sSHORT_POLE_WIDTH = 5;
		
		private boolean mState;
		
		public SolenoidDisplay()
		{
			setPreferredSize(new Dimension(sWIDTH, sHEIGHT));
		}
		
		public void updateDisplay(boolean aValue)
		{
			mState = aValue;
			repaint();
		}
		
		public void paint(Graphics g)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			
			g.fillRect(0, 0, sCYLINDER_WIDTH, sHEIGHT);
			
			if(mState)
			{
				g.fillRect(sCYLINDER_WIDTH,        sHEIGHT/2, sWIDTH,        sPOLE_SIZE);
				g.fillRect(sWIDTH - sPLUNGER_SIZE, 0,         sPLUNGER_SIZE, sHEIGHT);
			}
			else
			{
				g.fillRect(sCYLINDER_WIDTH,                     sHEIGHT/2, sSHORT_POLE_WIDTH, sPOLE_SIZE);
				g.fillRect(sCYLINDER_WIDTH + sSHORT_POLE_WIDTH, 0,         sPLUNGER_SIZE,     sHEIGHT);
			}
			
//			g.setColor(Util.getMotorColor(mMotorSpeed));
//			g.fillOval(0, 0, sDOT_SIZE, sDOT_SIZE);
		}
	}
	
	public SolenoidGraphicDisplay(Map<Integer, SolenoidWrapper> aMap) {
		super(aMap);
		setBorder(new TitledBorder("Solenoid"));
	}

	public void update(Map<Integer, SolenoidWrapper> aMap)
	{
		for(Entry<Integer, SolenoidWrapper> pair : aMap.entrySet())
		{
			((SolenoidDisplay)mWidgetMap.get(pair.getKey())).updateDisplay(pair.getValue().get());
		}
	}

	@Override
	protected SolenoidDisplay createWidget(Entry<Integer, SolenoidWrapper> pair) {
		return new SolenoidDisplay();
	}
}
