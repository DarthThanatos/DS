package pl.edu.agh.dsrg.sr.protos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.jgroups.JChannel;

import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation;
import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation.OperationType;

public class Server {
	

	public static void main(String []args){
		MulticastSocket udpServer = null;
		 try{
			 String ip = "224.0.0.7";
			 int port = 6789;
			 InetAddress address = InetAddress.getByName(ip);
			 udpServer = new MulticastSocket(port);
			 udpServer.joinGroup(address);
			 for (int i = 0; i < 2000; i++){
					byte[] receiveBuffer = new byte[1024];
	                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
	                udpServer.receive(receivePacket);
	                byte[] raw_stream = new String(receivePacket.getData(), 0,receivePacket.getLength()).getBytes();
	       		 	BankOperation operation = BankOperation.parseFrom(raw_stream);
	                System.out.println("Got: type: " + operation.getType() + " val: " +  operation.getValue());
			 }
	         udpServer.leaveGroup(address);
		 }
		 catch(Exception e){
			 
		 }
		 finally{
			 udpServer.close();
		 }
	}
}
