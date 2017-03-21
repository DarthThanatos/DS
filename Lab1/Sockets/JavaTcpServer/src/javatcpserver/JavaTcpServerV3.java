package javatcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaTcpServerV3 {

    public static void main(String[] args) throws IOException {
        
        System.out.println("JAVA TCP SERVER");  
        int portNumber = 12345;
        ServerSocket serverSocket = null;
        byte[] inBuf = new byte[1024];
        try {
            // create socket
            serverSocket = new ServerSocket(portNumber);
            
            while(true){
                
                // accept client
                Socket clientSocket = serverSocket.accept();     
                System.out.println("client connected");
                
                // in & out streams
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                InputStream in = clientSocket.getInputStream();
                
                // read msg, send response
				
                in.read(inBuf);
                System.out.println("received msg: " + new String(inBuf));
                out.println("Pong Java Tcp");
       
            }
        } catch (IOException e) {            
            e.printStackTrace();
        }
        finally{
            if (serverSocket != null){
                serverSocket.close();
            }
        }
    }
    
}
