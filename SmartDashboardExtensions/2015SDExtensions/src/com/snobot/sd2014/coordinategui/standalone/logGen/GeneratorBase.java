package com.snobot.sd2014.coordinategui.standalone.logGen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.snobot.sd2014.coordinategui.Coordinate;

public class GeneratorBase
{
    private BufferedWriter bw;
    public GeneratorBase(String aFile) throws IOException
    {
        bw = new BufferedWriter(new FileWriter(aFile));
    }
    
    public void writeCoord(Coordinate c) throws IOException
    {
        bw.write(c.x + ", " + c.y + ", " + c.angle + "\n");
    }
    
    public void flush() throws IOException
    {
        bw.flush();
    }
}
