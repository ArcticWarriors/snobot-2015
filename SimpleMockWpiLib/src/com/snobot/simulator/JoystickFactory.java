package com.snobot.simulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class JoystickFactory {
	
	private static final String sJOYSTICK_CONFIG_FILE = "joystick_config.properties";
	private static final int sNUMBER_OF_STICKS = 6;
	private static final String sKEY = "Joystick_";
	
	private Map<Integer, IMockJoystick> mJoystickMap;
	
	public JoystickFactory() {
		
		mJoystickMap = new HashMap<Integer, IMockJoystick>();
		try
		{
			InputStream input_stream = new FileInputStream(sJOYSTICK_CONFIG_FILE);
			Properties properties = new Properties();
			properties.load(input_stream);
			input_stream.close();
			
			loadSticks(properties);
			
		}
		catch(Exception e)
		{
			System.err.println("Could not read joystick properties file... will use default joystick");
			e.printStackTrace();
			
			updateToDefaultMap();
			writeJoystickFile();
		}
	}
	
	private void loadSticks(Properties properties) throws Exception
	{
	    ClassLoader classLoader = JoystickFactory.class.getClassLoader();
	    
		for(Entry<Object, Object> i : properties.entrySet())
		{
			int number = Integer.parseInt(i.getKey().toString().substring(sKEY.length()));

	        Class aClass = classLoader.loadClass(i.getValue().toString());
	        
	        if(IMockJoystick.class.isAssignableFrom(aClass))
	        {
	        	IMockJoystick joystick = (IMockJoystick) aClass.newInstance();
	        	System.out.println("Creating joystick : " + joystick);
	        	mJoystickMap.put(number, joystick);
	        }
		}
	}
	
	private void writeJoystickFile()
	{
		try 
		{

			Properties p = new Properties();
			for(int i = 0; i < sNUMBER_OF_STICKS; ++i)
			{
				p.put(sKEY + i, mJoystickMap.get(i).getClass().getName());
			}
			
			FileOutputStream stream = new FileOutputStream(sJOYSTICK_CONFIG_FILE);

			p.store(stream, "");
			System.err.println("Wrote joystick config file to " + new File(sJOYSTICK_CONFIG_FILE).getAbsolutePath());
			stream.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void updateToDefaultMap()
	{
		
		for(int i = 0; i < sNUMBER_OF_STICKS; ++i)
		{
			if(!mJoystickMap.containsKey(i))
			{
				mJoystickMap.put(i, new NullJoystick());
			}
		}
	}

	public IMockJoystick create(int aJoystickIndex) {
		return mJoystickMap.get(aJoystickIndex);
//		return new Ps4Joystick(aJoystickIndex);
	}

}
