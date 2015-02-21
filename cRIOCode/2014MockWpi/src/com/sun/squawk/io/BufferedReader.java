package com.sun.squawk.io;

import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReader {

    private java.io.BufferedReader br;

    public BufferedReader(InputStreamReader reader) {
        br = new java.io.BufferedReader(reader);
    }

    public String readLine() throws IOException
    {
        return br.readLine();
    }

    public void close() throws IOException
    {
        br.close();
    }

}
