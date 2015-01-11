package com.snobot.simulator;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class PrintJinputThings {

	//http://www.java-gaming.org/index.php?topic=16866.0
	public static void printAll()
	{
		Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		System.out.println("Joysticks:");

        for(int i =0;i<ca.length;i++){
        	
        	if(
        			ca[i].getType().toString().equals("Keyboard") || 
        			ca[i].getType().toString().equals("Mouse") || 
        			ca[i].getType().toString().equals("USB Receiver") || 
        			ca[i].getType().toString().equals("Unknown"))
        	{
        		continue;
        	}
            Component[] components = ca[i].getComponents();
        	
            /* Get the name of the controller */
            System.out.println("  " + ca[i].getName() + ", Type: "+ca[i].getType().toString());
            System.out.println("  Component Count: "+components.length);
            
            for(int j=0;j<components.length;j++){
                
                /* Get the components name */
                System.out.print("  Component " + j + ":  \t"+components[j].getName());
                System.out.print("\tIdentifier: "+ components[j].getIdentifier().getName() + "\tComponentType:\t");
                if (components[j].isRelative()) {
                    System.out.print("(Relative");
                } else {
                    System.out.print("(Absolute");
                }
                if (components[j].isAnalog()) {
                    System.out.print(" Analog)");
                } else {
                    System.out.print(" Digital)");
                }
                
                System.out.println();
            }
            
            System.out.println("\n\n");
        }


	}
	

	public static void printAxis(Controller aController)
	{
        Component[] components = aController.getComponents();
        
        for(int j=0;j<components.length;j++){
        	
        	if (components[j].isAnalog()) {
        		System.out.println("Analog Value [" + j + "] = " + components[j].getPollData());
            }
        }
	}
}
