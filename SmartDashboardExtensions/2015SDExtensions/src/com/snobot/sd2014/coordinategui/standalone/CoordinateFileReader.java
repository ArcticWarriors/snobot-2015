package com.snobot.sd2014.coordinategui.standalone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.sd2014.coordinategui.Coordinate;

/**
 * Reads a file containing a list of coordinates to add to the field
 * @author Rich
 */
public class CoordinateFileReader
{

    private static final String sDELIMETER = ",";

    private CoordinateFileReader()
    {
    }

    /**
     * Reads a file containing a list of coordinates.
     * @param aFile The file to read
     * @return The list of coordinates that were read
     */
    public static List<Coordinate> readFile(String aFile)
    {
        String line;
        ArrayList<Coordinate> data = new ArrayList<Coordinate>();

        try
        {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, sDELIMETER);
                Coordinate c = new Coordinate();

                String first = st.nextElement().toString();
                String second = st.nextElement().toString();
                if(first.equalsIgnoreCase("Position"))
                {
                    String third = st.nextElement().toString();
                    //They are swapped in the file... probably a TODO
                    c.x = Double.parseDouble(third);
                    c.y = Double.parseDouble(second);
                }
                else
                {
                    c.x = Double.parseDouble(first);
                    c.y = Double.parseDouble(second);
                }

                if (st.hasMoreElements())
                {
                    c.angle = Double.parseDouble(st.nextElement().toString());
                }

                data.add(c);
            }
            
            br.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }

        return data;
    }
}
