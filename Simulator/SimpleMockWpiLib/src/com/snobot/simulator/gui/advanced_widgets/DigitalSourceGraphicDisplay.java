package com.snobot.simulator.gui.advanced_widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.DigitalSourceWrapper;

public class DigitalSourceGraphicDisplay extends BaseWidgetDisplay<DigitalSourceWrapper>
{
    private class DigitalSourceWrapperDisplay extends JPanel
    {
        private static final int sDOT_SIZE = 30;

        private boolean mState;

        public DigitalSourceWrapperDisplay()
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

    public DigitalSourceGraphicDisplay(Map<Integer, DigitalSourceWrapper> aMap)
    {
        super(aMap);
        setBorder(new TitledBorder("Digital IO"));
    }

    public void update(Map<Integer, DigitalSourceWrapper> aMap)
    {
        for (Entry<Integer, DigitalSourceWrapper> pair : aMap.entrySet())
        {
            ((DigitalSourceWrapperDisplay) mWidgetMap.get(pair.getKey())).updateDisplay(pair.getValue().get());
        }
    }

    @Override
    protected DigitalSourceWrapperDisplay createWidget(Entry<Integer, DigitalSourceWrapper> pair)
    {
        return new DigitalSourceWrapperDisplay();
    }
}
