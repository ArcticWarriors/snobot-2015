package com.snobot.simulator;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class KeyboardJoystick implements IMockJoystick 
{
	private boolean[] mButtonStates = new boolean[10];

	//Custom dispatcher
	class KeyDispatcher implements KeyEventDispatcher 
	{
	    public boolean dispatchKeyEvent(KeyEvent e) 
	    {
        	if(e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9)
        	{
        		int index = e.getKeyChar() - KeyEvent.VK_0;
        		mButtonStates[index] = e.getID() == KeyEvent.KEY_TYPED;
        	}
	 
	        //Allow the event to be redispatched
	        return false;
	    }
	}
	
	public KeyboardJoystick() {
		KeyboardFocusManager manager =
		         KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher( new KeyDispatcher() );
	}

	@Override
	public boolean getRawButton(int aIndex) {
		if(aIndex >= 0 && aIndex < 10)
		{
			return mButtonStates[aIndex];
		}
		return false;
	}

	@Override
	public double getRawAxis(int aIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
