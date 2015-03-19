
package fake_java.util;

import java.util.Vector;

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
