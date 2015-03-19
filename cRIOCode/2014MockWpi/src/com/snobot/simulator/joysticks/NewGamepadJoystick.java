package com.snobot.simulator.joysticks;

import java.util.Arrays;
import java.util.List;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;

public class NewGamepadJoystick extends BaseJoystick {

	
	private final static List<Type> acceptableTypes = Arrays.asList(Type.GAMEPAD, Type.STICK);

	
	public NewGamepadJoystick() 
	{
		super(acceptableTypes.toString());

		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment(); 

		Controller[] cs = ce.getControllers(); 
		for (int i = 0; i < cs.length; i++) 
		{
			if(acceptableTypes.contains(cs[i].getType()))
			{
				mController = cs[i];
			}
		}
		
		if(mController != null)
		{
            Component[] components = mController.getComponents();
            
            for(int j = 0; j < components.length; j++)
            {
                if (components[j].isAnalog()) 
                {
                	mAxis.add(components[j].getIdentifier());
                } 
                else 
                {
                	mButtons.add(components[j].getIdentifier());
                }
            }
		}

        mAxisValues = new byte[mAxis.size()];
        mPovValues = new byte[0];
	}
}
