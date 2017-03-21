package javaudpclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class JavaUdpClientV2 {

    public static void main(String args[]) throws Exception
    {
        System.out.println("JAVA UDP CLIENT");
        DatagramSocket socket = null;
		DatagramSocket serverSocket = new DatagramSocket(9877);
        int portNumber = 9876;
		byte[] receiveBuffer = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
      
        try {
            socket = new DatagramSocket(); 
            InetAddress address = InetAddress.getByName("localhost");            
            byte[] sendBuffer = "Ping Java Udp".getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
            socket.send(sendPacket);
            // TODO: receive response
			serverSocket.receive(receivePacket);
			System.out.println(new String(receivePacket.getData()));
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
