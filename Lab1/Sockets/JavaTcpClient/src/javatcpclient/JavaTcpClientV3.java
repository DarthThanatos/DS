package javatcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class JavaTcpClientV3 {

    public static void main(String[] args) throws IOException {
                
        System.out.println("JAVA TCP CLIENT");
        String hostName = "localhost";
        int portNumber = 12345;
        Socket socket = null;
        byte[] inBuf = new byte[1024];
        try {
            // create socket 
            socket = new Socket(hostName, portNumber);
            
            // in & out streams
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            InputStream in = socket.getInputStream();            
            
            // send msg, read response
            out.println("Ping Java Tcp");
            in.read(inBuf);
            System.out.println("received response: " + new String(inBuf));
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                socket.close();
            }
        }
    }
    
}
