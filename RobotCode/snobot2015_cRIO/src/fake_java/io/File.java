
package fake_java.io;

import java.io.IOException;

import javax.microedition.io.Connector;

import com.snobot.ConfigurationNames;
import com.sun.squawk.microedition.io.FileConnection;

public class File 
{
    private String mFilePath;
   
    public File(String aFilePath)
   {
        mFilePath = aFilePath;
   }

   public boolean exists()
   {
      return true;
   }

   public void mkdirs()
   {
   }

    public boolean isDirectory()
    {
       boolean isDir = false;
       try
       {
          isDir = ((FileConnection) Connector.open(mFilePath, Connector.READ_WRITE)).isDirectory();
       }
       catch (IOException ex)
       {
          ex.printStackTrace();
       }
       
       return isDir;
    }

    public File[] listFiles()
    {
       if(mFilePath.equals(ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sAUTON_DIR, ConfigurationNames.sDEFAULT_AUTON_DIR)))
       {
         return new File[] {
            new File(mFilePath + "/StackAndMoveToLandmark"),
         };
       }
       return new File[] {};
    }

    public boolean isFile()
    {
        boolean isFile = false;
        try
        {
            isFile = ((FileConnection) Connector.open(mFilePath, Connector.READ_WRITE)).exists();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return isFile;
    }

    public String getName()
    {
        return mFilePath;
    }

    public String getAbsolutePath()
    {
        return mFilePath;
    }

    public String toString()
    {
        return "File[" + mFilePath + "]";
    }

}
