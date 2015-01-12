package com.snobot.simulator;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardJoystick implements IMockJoystick 
{
	private static interface IKeyholder
	{

		public boolean isValidButton(char aKey);
		public void setButtonState(char keyChar, boolean isSet) ;
		public void trySetAxisButton(char keyChar, boolean isPressed);
		
		public boolean getRawButton(int aIndex);
		public double getRawAxis(int aIndex);
	}
	
	private static class KeyHolder implements IKeyholder
	{
		private boolean[] mButtonStates = new boolean[10];
		private double[] mAxisStates = new double[6];

		private Map<Integer, Integer> charToButtonMap = new HashMap<Integer, Integer>();

		private char fowardKey;
		private char reverseKey;
		
		public boolean isValidButton(char aKey)
		{
			boolean output = charToButtonMap.containsKey((int)aKey);
			
			return output;
		}
		private int indexFromButton(char aKey)
		{
			int key = (int) aKey;
			return charToButtonMap.get(key);
		}
		public void setButtonState(char keyChar, boolean isSet) {
			mButtonStates[indexFromButton(keyChar)] = isSet;
		}
		public void trySetAxisButton(char keyChar, boolean isPressed) {
			
			if(isPressed)
			{
				if(keyChar == fowardKey)
				{
					mAxisStates[0] = 1.0;
					mAxisStates[1] = 1.0;
				}
				else if(keyChar == reverseKey)
				{
					mAxisStates[0] = -1.0;
					mAxisStates[1] = -1.0;
				}
			}
			else
			{
				mAxisStates[0] = 0;
				mAxisStates[1] = 0;
			}
		}
		@Override
		public boolean getRawButton(int aIndex) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public double getRawAxis(int aIndex) {
			return mAxisStates[aIndex];
		}
	}
	
	class NullKeyholder implements IKeyholder 
	{

		@Override
		public boolean isValidButton(char aKey) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setButtonState(char keyChar, boolean isSet) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trySetAxisButton(char keyChar, boolean isPressed) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean getRawButton(int aIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public double getRawAxis(int aIndex) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	private IKeyholder keyHolder;
	
	private static Map<Integer, KeyHolder> joystickNumberToKeyHolder = new HashMap<Integer, KeyboardJoystick.KeyHolder>();
	
	static
	{
		KeyHolder zeroithKeyHolder = new KeyHolder();
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_0, 0);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_1, 1);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_2, 2);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_3, 3);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_4, 4);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_5, 5);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_6, 6);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_7, 7);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_8, 8);
		zeroithKeyHolder.charToButtonMap.put(KeyEvent.VK_9, 9);

		zeroithKeyHolder.fowardKey = KeyEvent.VK_Z + 32;
		zeroithKeyHolder.reverseKey = KeyEvent.VK_X + 32;

		KeyHolder firstKeyHolder = new KeyHolder();
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_Q + 32, 0);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_W + 32, 1);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_E + 32, 2);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_R + 32, 3);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_T + 32, 4);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_Y + 32, 5);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_U + 32, 6);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_I + 32, 7);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_O + 32, 8);
		firstKeyHolder.charToButtonMap.put(KeyEvent.VK_P + 32, 9);

		firstKeyHolder.fowardKey = KeyEvent.VK_C + 32;
		firstKeyHolder.reverseKey = KeyEvent.VK_V + 32;

		KeyHolder secondKeyHolder = new KeyHolder();
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_A + 32, 0);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_S + 32, 1);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_D + 32, 2);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_F + 32, 3);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_G + 32, 4);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_H + 32, 5);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_J + 32, 6);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_K + 32, 7);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_L + 32, 8);
		secondKeyHolder.charToButtonMap.put(KeyEvent.VK_CANCEL, 9);

		secondKeyHolder.fowardKey = KeyEvent.VK_B + 32;
		secondKeyHolder.reverseKey = KeyEvent.VK_N + 32;
//
		KeyHolder thirdKeyHolder = new KeyHolder();
//		
		joystickNumberToKeyHolder.put(1, zeroithKeyHolder );
		joystickNumberToKeyHolder.put(2, firstKeyHolder );
		joystickNumberToKeyHolder.put(3, secondKeyHolder );
		joystickNumberToKeyHolder.put(4, thirdKeyHolder );
	}

	//Custom dispatcher
	class KeyDispatcher implements KeyEventDispatcher 
	{
	    public boolean dispatchKeyEvent(KeyEvent e) 
	    {
	    	if(keyHolder.isValidButton(e.getKeyChar()))
	    	{
	    		keyHolder.setButtonState(e.getKeyChar(), e.getID() == KeyEvent.KEY_TYPED);
	    	}
	    	else
	    	{
	    		keyHolder.trySetAxisButton(e.getKeyChar(), e.getID() == KeyEvent.KEY_TYPED);
	    	}
	 
	        //Allow the event to be redispatched
	        return false;
	    }
	}
	
	public KeyboardJoystick(int aIndex) {
		KeyboardFocusManager manager =
		         KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher( new KeyDispatcher() );
		
		if(joystickNumberToKeyHolder.containsKey(aIndex))
		{
			keyHolder = joystickNumberToKeyHolder.get(aIndex);
		}
		else
		{
			keyHolder = new NullKeyholder();
		}
	}

	@Override
	public boolean getRawButton(int aIndex) {
		if(aIndex >= 0 && aIndex < 10)
		{
			return keyHolder.getRawButton(aIndex);
		}
		return false;
	}

	@Override
	public double getRawAxis(int aIndex) {
		return keyHolder.getRawAxis(aIndex);
	}

	@Override
	public double getX() {
		return getRawAxis(0);
	}

	@Override
	public double getY() {
		return getRawAxis(1);
	}

}
