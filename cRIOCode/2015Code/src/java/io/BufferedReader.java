
package java.io;

public class BufferedReader 
{
   private com.sun.squawk.io.BufferedReader mReader;

   public BufferedReader(FileReader fileReader) throws IOException
   {
      InputStreamReader reader = fileReader.getReader();
      mReader = new com.sun.squawk.io.BufferedReader(reader);
   }

   public String readLine() throws IOException
   {
      return mReader.readLine();
   }

   public void close() throws IOException
   {
      mReader.close();
   }

}
