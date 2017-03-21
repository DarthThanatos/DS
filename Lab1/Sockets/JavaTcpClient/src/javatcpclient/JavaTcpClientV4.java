package javatcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class JavaTcpClientV4 {

    public static void main(String[] args) throws IOException {
        int nb = args.length == 1 ? Integer.parseInt(args[0]) : 9; 
		System.out.println("args = " + Integer.toString(nb));		
		int response; 
        System.out.println("JAVA TCP CLIENT");
        String hostName = "localhost";
        int portNumber = 12345;
        Socket socket = null;
        byte[] inBuf = new byte[1024];
		DataOutputStream out = null;
		DataInputStream in = null;
		
        try {
            // create socket 
            socket = new Socket(hostName, portNumber);
            System.out.println("Connected to server; is closed: " + socket.isClosed());
            // in & out streams
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());            
            
            // send msg, read response
            out.writeInt(nb);
            response = in.readInt();
            System.out.println("received response: " + Integer.toString(response));
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                socket.close();
            }
			out.close();
			in.close();
        }
    }
    
}
