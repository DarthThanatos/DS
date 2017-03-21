package pl.edu.agh.dsrg.sr.protos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation;
import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation.OperationType;

public class Client {

	private static BankOperation generateOperation(){
		BankOperation operation;
		operation = BankOperation.newBuilder()
								 .setValue(Math.random()/100+1.0)
								 .setType(OperationType.MULTIPLY).build();
		 System.out.println(new String(operation.toByteArray()));
		 System.out.println(operation.toString());
		 return operation;
		
	}
	
	public static void main(String [] args){

		 String ip = "224.0.0.7";
		 int port = 6789;
		 MulticastSocket udpServer;

		 /*
		 Thread.sleep((long)(Math.random()*10));*/
		 try {
			InetAddress address = InetAddress.getByName(ip);
			udpServer = new MulticastSocket();
			udpServer.joinGroup(address);
			for (int i = 0; i < 2000; i++){
				BankOperation operation = generateOperation();
				byte[] opBytes = operation.toByteArray();
				DatagramPacket packet = new DatagramPacket(opBytes, opBytes.length, address, port);
				udpServer.send(packet);
			}
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
}
