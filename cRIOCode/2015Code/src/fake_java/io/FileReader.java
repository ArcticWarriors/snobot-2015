
package fake_java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sun.squawk.microedition.io.FileConnection;

import javax.microedition.io.Connector;

public class FileReader 
{
   private final String mPath;
   
   public FileReader(String aFilePath)
   {
      mPath = aFilePath;
   }
   
   InputStreamReader getReader() throws IOException
   {
      
      FileConnection fileConnection = (FileConnection) Connector.open(mPath, Connector.READ);
      if (!fileConnection.exists()) {
         return null;
      }

      InputStream inputStream = fileConnection.openInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(
              inputStream);
      
      return inputStreamReader;
   }

}
