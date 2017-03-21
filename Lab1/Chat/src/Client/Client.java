package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {	
	static DatagramSocket udpServer = null;
	static Socket server = null;
	
	static String ip;
	static int port;
	
	static Scanner scanner = null;
	static String clientName = null;
	static boolean shouldContinue = true;
	static Lock lock = null;
	
	static int actualAsciiLen;
	
	private static void sendMsg(){
		System.out.println("Type your msg to server: ");
		String input = scanner.nextLine();
		String msg = clientName + " " + input.replace(" ", "%20");
		try {
			new PrintWriter(server.getOutputStream(),true).println(msg + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static class ServerMsgHandler extends Thread{
		public void run(){
			try {
				BufferedReader inFromServer = new BufferedReader( new InputStreamReader(server.getInputStream()));
				while(shouldContinue){
					String response = inFromServer.readLine();
					lock.lock();
					if(response.length() != 0) System.out.println(response);
					lock.unlock();
				}
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("ServerHandler: Connection closed");
				shouldContinue = false;
			}
		}
	}
	
	private static class UDPServerMsgHandler extends Thread{
		public void run(){
			byte[] receiveBuffer = new byte[actualAsciiLen];
			while(shouldContinue){
				DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, actualAsciiLen);
				try {
					udpServer.receive(receivedPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String response = new String(receivedPacket.getData());
				lock.lock();
				if(response.length() != 0) System.out.println(response);
				lock.unlock();
			}
		}
	}
	
	private static void sendMultimedia(char[] cbuf){
		try {
			InetAddress address = InetAddress.getByName(ip);
			DatagramPacket packet = new DatagramPacket(new String(cbuf).getBytes(), actualAsciiLen, address, port);
			udpServer.send(packet);
			System.out.println("Sent ascii art msg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void sendMultimediaMulticast(char[] cbuf, String clientName){
		try {
			System.out.println("Type the name of your multicast group:");
			String groupName = scanner.nextLine();
			if(groupName.length() == 0) {
				System.out.println("Bad group name!");
				return;
			}
			InetAddress address = InetAddress.getByName(ip);
			DatagramPacket packet = new DatagramPacket(("multicast@" + clientName + "@" + groupName + "@" + new String(cbuf)).getBytes(), actualAsciiLen, address, port);
			udpServer.send(packet);
			System.out.println("Sent ascii art msg on multicast");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[]args){
		try{
			if(args.length != 3){
				System.out.println("Usage: java Client ip port id");
				System.exit(-1);
			}
			scanner = new Scanner(System.in);
			ip = args[0];
			port = Integer.parseInt(args[1]);
			clientName = args[2];
			lock = new ReentrantLock();
			
			String asciiPath = "../AsciiArt.txt";
			BufferedReader asciiArt = new BufferedReader(new FileReader(new File(asciiPath)));
			
			int len = 1024;
			char[] cbuf = new char[len];
			actualAsciiLen = asciiArt.read(cbuf, 0, len);
			
			udpServer = new DatagramSocket();
			
			/*registrations in udp channel*/
			
			byte[] registerBuf = new String("register " + clientName).getBytes();
			InetAddress address = InetAddress.getByName(ip);
			DatagramPacket registerPacket = new DatagramPacket(registerBuf, registerBuf.length, address, port);
			udpServer.send(registerPacket);
			

			byte[] registerResponseBuffer = new byte[1024];
            DatagramPacket registerResponsePacket = new DatagramPacket(registerResponseBuffer, registerResponseBuffer.length);
			udpServer.receive(registerResponsePacket);

			if(new String(registerResponsePacket.getData(),0,registerResponsePacket.getLength()).equals("nameRegistered")){	
				/*registrations in tcp channel*/
				server = new Socket(ip, port);
				new PrintWriter(server.getOutputStream(),true).println(clientName + "\n");
				
				ServerMsgHandler serverMsgHandler = new ServerMsgHandler();
				serverMsgHandler.start();
				UDPServerMsgHandler udpServerMsgHandler = new UDPServerMsgHandler();
				udpServerMsgHandler.start();
				
				while(shouldContinue){
					System.out.println("You are: " + clientName + ";\n->Type S to send sth to Server\n->Type M to send multimedia via UDP\n->Type N to send multimedia on multicast\n->Type Q to quit:");
					String modeLetter = scanner.nextLine();
					char mode = modeLetter.length() > 0 ? modeLetter.charAt(0) : 'X';
					switch(mode){
						case 'S':
							sendMsg();
							break;
						case 'M':
							sendMultimedia(cbuf);
							break;
						case 'N':
							sendMultimediaMulticast(cbuf, clientName);
							break;
						case 'Q':
							shouldContinue = false;
							break;
						default:
							System.out.println("Wrong mode selected");
							break;
					}
				}
			}
			else{
				System.out.println("Name already exists!");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Usage: java Client ip port id");
		}
		finally{
			try {
				if(server != null)server.close();
				System.out.println("Exiting");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
