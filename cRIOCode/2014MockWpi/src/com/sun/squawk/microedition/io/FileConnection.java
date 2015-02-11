package com.sun.squawk.microedition.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;

public class FileConnection {
    
    private File mFile;
    private InputStream mInStream;
    private OutputStream mOutStream;

    public FileConnection(String path, String connector) throws IOException {
        mFile = new File(path);
        
        if(connector.equals(Connector.READ))
        {
            mInStream = new FileInputStream(mFile);
        }
        else if (connector.equals(Connector.WRITE))
        {
            mOutStream = new FileOutputStream(mFile);
        }
    }

    public boolean exists() {
        return mFile.exists();
    }

    public InputStream openInputStream() throws FileNotFoundException {
        return new FileInputStream(mFile);
    }

    public void close() throws IOException {
    	if(mInStream != null)
    	{
    		mInStream.close();
    	}
    	
    	if(mOutStream != null)
    	{
    		mOutStream.close();
    	}
    }

    public void create() {
    	System.err.println("Not implemented...");
    }

    public OutputStream openOutputStream() throws FileNotFoundException {
        return new FileOutputStream(mFile);
    }

    public boolean isDirectory()
    {
        return mFile.isDirectory();
    }

}
