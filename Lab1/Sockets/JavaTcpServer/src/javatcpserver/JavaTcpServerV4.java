package javatcpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaTcpServerV4 {

    public static void main(String[] args) throws IOException {
        
        System.out.println("JAVA TCP SERVER");  
        int portNumber = 12345;
        ServerSocket serverSocket = null;
        try {
            // create socket
            serverSocket = new ServerSocket(portNumber);
            
            while(true){
                
				int nb = 0;
                // accept client
                Socket clientSocket = serverSocket.accept();     
                System.out.println("client connected");
                
                // in & out streams
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                
                // read msg, send response
				
                nb = in.readInt();
                System.out.println("received msg: " + Integer.toString(nb));
				nb ++;
                out.writeInt(nb);
       
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
