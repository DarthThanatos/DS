package javaudpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class JavaUdpServer {

    public static void main(String args[])
    {
        System.out.println("JAVA UDP SERVER");
        DatagramSocket socket = null;
        int portNumber = 9876;
        
        try{
            socket = new DatagramSocket(portNumber);
            byte[] receiveBuffer = new byte[1024];
            
            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String msg = new String(receivePacket.getData());
                System.out.println("received msg: " + msg);

                // TODO: send response
                // hint: receivePacket.getAddress();
                byte[] sendBuffer = "Pong Java Udp".getBytes();     // response to send                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
          if (socket != null) {
              socket.close();
            }
        } 
    }    
}
