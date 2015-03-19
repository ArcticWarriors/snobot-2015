package javax.microedition.io;

import java.io.IOException;

import com.sun.squawk.microedition.io.FileConnection;

public class Connector {

    public static final String READ = "read";
    public static final String WRITE = "write";
    public static String READ_WRITE = "readwrite";

    public static FileConnection open(String path, String connector) throws IOException {
        return new FileConnection(path, connector);
    }


}
