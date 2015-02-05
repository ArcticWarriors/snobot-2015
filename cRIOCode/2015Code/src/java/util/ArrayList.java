
package java.util;

import com.snobot.ISubsystem;
import com.snobot.operatorjoystick.SnobotOperatorJoystick;

public class ArrayList implements List
{
   
   private Vector mVector = new Vector();

   public void add(Object o)
   {
      mVector.addElement(o);
   }

   public int size()
   {
      return mVector.size();
   }

   public Object get(int i)
   {
      return mVector.elementAt(i);
   }

}
