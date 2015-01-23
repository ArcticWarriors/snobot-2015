/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.snobot.sd.robotwidget;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Calvin Do
 */
public class RobotContainer2014 extends JPanel{

	private static final long serialVersionUID = -7375477217091956849L;
	
	protected RobotDrawer2014 mDrawer;
    
        public RobotContainer2014(){
            setLayout(new BorderLayout());
            setBorder(new LineBorder(Color.black));
            
            mDrawer=new RobotDrawer2014();
            add(mDrawer);
        }
       	public void updateSize() 
	{
		mDrawer.updateSize();
	}
}
