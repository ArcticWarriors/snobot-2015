
package fake_java.io;

public class File 
{
   
   public File(String mLogFilePath)
   {
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
        return false;
    }

    public File[] listFiles()
    {
        return new File[] {};
    }

    public boolean isFile()
    {
        return false;
    }

    public String getName()
    {
        return "";
    }

    public String getAbsolutePath()
    {
        return "";
    }

}
