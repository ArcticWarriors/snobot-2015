package com.snobot.sd.text_area;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.snobot.sd.config.WidgetConfiguration;

public class SDTextArea {

    private JTextArea mTextArea;
    private JScrollPane mScrollPane;
    
    public SDTextArea()
    {
        mTextArea=new JTextArea(WidgetConfiguration.TEXT_AREA_ROWS, WidgetConfiguration.TEXT_AREA_COLUMNS);
        mScrollPane=new JScrollPane(mTextArea);
    }
    
    public JScrollPane getScrollPane()
    {
        return this.mScrollPane;
    }
}
