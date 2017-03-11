package Server;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
	static LinkedBlockingQueue<String> msgQueue;
	static Set<ServerThread> clientsHandlers;
	
	private static class MsgHandler extends Thread{
		String msg;
		String[] msgParts;
		
		public void run(){
			while(true){
				try {
					msg = msgQueue.take();
					if(msg.length()!=0){
						msgParts = msg.split(" "); //client_id, msgText
						for (ServerThread clientHandler : clientsHandlers){
							if(!clientHandler.getClientName().equals(msgParts[0])){		
								if(!clientHandler.isClosed())
									clientHandler.sendResponse(msgParts[0], msgParts[1].replace("%20", " "));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static class ServerGC extends Thread{
		public void run(){
			while(true){
				for(ServerThread clientHandler: clientsHandlers){
					if (clientHandler.isClosed()){
						System.out.println("Deleting " + clientHandler.getClientName() + "...");
						clientsHandlers.remove(clientHandler);
						System.out.println("Deleted " + clientHandler.getClientName());
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static class TCPChannel extends Thread{
		public TCPChannel(){
			
		}
		
		public void run(){
			
		}
	}

	
	private static class UDPChannel extends Thread{
		public UDPChannel(){
			
		}
		
		public void run(){
			
		}
	}
	
	public static void main(String[] args){
		ServerSocket server = null;
		DatagramSocket udpServer = null;
		try{
			int N = 10; //amount of Threads in the pool
			String ip = args[0];
			int port = Integer.parseInt(args[1]); //reading first ip, then port
			msgQueue = new LinkedBlockingQueue<String>();
			clientsHandlers =  Collections.synchronizedSet(new HashSet<ServerThread>());
			ExecutorService executor = Executors.newFixedThreadPool(N); //pool of threads for effeciency
			server = new ServerSocket (port, N, InetAddress.getByName(ip));
			udpServer = new DatagramSocket();
			
			MsgHandler msgHandler = new MsgHandler(); //sending incoming msgs to appropriate clients
			msgHandler.start();
			
			ServerGC serverGC = new ServerGC(); //garbage collector deleting "stale" clients
			serverGC.start();
			
			System.out.println ("TCPServer Waiting for client on port " + args[1]);
			while(true) {
				Socket connected = server.accept();
				String clientName = new BufferedReader(new InputStreamReader(connected.getInputStream())).readLine();
				ServerThread serverThread = new ServerThread(connected, clientName, msgQueue);
				clientsHandlers.add(serverThread);
				executor.execute(serverThread);
			}
		}
		catch(Exception e){
			System.out.println("Usage: java Server ip port");
			e.printStackTrace();
		}
		finally{
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
