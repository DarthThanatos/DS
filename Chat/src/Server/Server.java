package Server;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
	static ServerSocket server = null;
	static DatagramSocket udpServer = null;
	
	static Lock lock = null;
	static Lock udpLock = null;
	
	static LinkedBlockingQueue<String> msgQueue;
	static LinkedBlockingQueue<String> UDPmsgQueue;
	static HashMap<String,DatagramPacket> udpDests;

	static HashMap<String, List<String>> groupsAssignment = null;
	static HashMap<String, List<String>> clientGroups = null;
	static HashMap<String, String> recentClientMulticastGroup = null;
	
	static Set<ServerThread> clientsHandlers;
	static int N = 10; //amount of Threads in the pool
	static int port;
	static String ip;
	
	private static class MsgHandler extends Thread{
		String msg;
		String[] msgParts;
		
		public void run(){
			while(true){
				try {
					msg = msgQueue.take();
					if(msg.length()!=0){
						msgParts = msg.split(" "); //client_id, msgText
						lock.lock();
						Iterator<ServerThread> iter = clientsHandlers.iterator();
						while(iter.hasNext()){
							ServerThread clientHandler = iter.next();
							if(!clientHandler.getClientName().equals(msgParts[0])){		
								if(!clientHandler.isClosed())
									clientHandler.sendResponse(msgParts[0], msgParts[1].replace("%20", " "));
							}
							clientHandler = null;
						}
						lock.unlock();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private static class ServerGC extends Thread{
		private void cleanHashMap(HashMap map, List<String> deletedNames){
			Iterator destIter = udpDests.entrySet().iterator();
			while(destIter.hasNext()){
				Map.Entry entry = (Map.Entry)destIter.next();
				if(deletedNames.contains(entry.getKey())){
					destIter.remove();
				}
			}
		}
		
		public void run(){
			while(true){
				lock.lock();
				Iterator<ServerThread> iter = clientsHandlers.iterator();
				ArrayList<String> deletedNames = new ArrayList<String>();
				while(iter.hasNext()){
					ServerThread clientHandler = iter.next();
					if (clientHandler.isClosed()){
						deletedNames.add(clientHandler.getClientName());
						System.out.println("Deleting entry " + clientHandler.getClientName() + "...");
						iter.remove();
						System.out.println("Deleted " + clientHandler.getClientName());
					}
					clientHandler = null;
				}
				lock.unlock();
				udpLock.lock();
				cleanHashMap(udpDests,deletedNames);
				cleanHashMap(clientGroups, deletedNames);
				for( String deletedUser: deletedNames)recentClientMulticastGroup.remove(deletedUser);
				for( List<String> usersInGroup : groupsAssignment.values()){
					for (String deletedUser : deletedNames)
						usersInGroup.remove(deletedUser);
				}
				
				udpLock.unlock();
				
				if(!deletedNames.isEmpty()){
					System.out.println("Remaining clients: " + Integer.toString(udpDests.size()));
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
		ExecutorService executor = null; 
		
		public TCPChannel(){
			msgQueue = new LinkedBlockingQueue<String>();
			clientsHandlers =  Collections.synchronizedSet(new HashSet<ServerThread>());
			executor = Executors.newFixedThreadPool(N); //pool of threads for effeciency
			try {
				server = new ServerSocket (port, N, InetAddress.getByName(ip));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void run(){
			System.out.println ("TCPServer Waiting for client on port " + port);
			try {
				while(true) {
					Socket connected = server.accept();
					String clientName = new BufferedReader(new InputStreamReader(connected.getInputStream())).readLine();
					lock.lock();
					ServerThread serverThread = new ServerThread(connected, clientName, msgQueue);
					clientsHandlers.add(serverThread);
					executor.execute(serverThread);
					serverThread = null;
					lock.unlock();
				}
			} catch (IOException e) {
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
	
	private static String getClientName(DatagramPacket p){
		String res = "";
		for(String clientName : udpDests.keySet()){
			if(udpDests.get(clientName).getAddress().equals(p.getAddress()) &&
						(udpDests.get(clientName).getPort() == p.getPort()))
			res = clientName;	
		}
		return res;
	}
	
	private static class UDPThread extends Thread{
		DatagramPacket dest;
		
		public UDPThread(DatagramPacket dest){
			this.dest = dest;
		}
		
		public void run(){
			Iterator iter = udpDests.entrySet().iterator();
			udpLock.lock();  
			String clientName = getClientName(dest);
			try {
				while(iter.hasNext()){
					Map.Entry entry =  ((Map.Entry)iter.next());
					DatagramPacket registerPacket = (DatagramPacket) entry.getValue();
					if((!registerPacket.getAddress().equals(dest.getAddress()) ||
							!(registerPacket.getPort() == dest.getPort())) &&
							clientGroups.get(entry.getKey()).contains(recentClientMulticastGroup.get(clientName))){ //default = broadcast	
						registerPacket.setData(new String (clientName + " sends greetings:\n " + new String(dest.getData(),0, dest.getLength())).getBytes());
						udpServer.send(registerPacket);
						System.out.println("Sent multimedia to " + entry.getKey() + " from " + clientName);
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				udpLock.unlock();
			}
		}
	}
	
	private static class UDPChannel extends Thread{
		ExecutorService executor = null; 
		public UDPChannel(){
			try {
				executor = Executors.newFixedThreadPool(N);
				udpServer = new DatagramSocket(port);
				UDPmsgQueue = new LinkedBlockingQueue<String>();
				udpDests = new HashMap<String,DatagramPacket>();
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
		
		public void run(){
			try{
				while(true){
					byte[] receiveBuffer = new byte[1024];
	                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
	                udpServer.receive(receivePacket);
	                String msg = new String(receivePacket.getData(),0,receivePacket.getLength());
	                String[] msgParts = msg.split(" "); //either msg or (register,name)
                	DatagramPacket destPacket = new DatagramPacket(msg.getBytes(),msg.length(),receivePacket.getAddress(),receivePacket.getPort());
	                if(msgParts[0].equals("register")){
	                	udpLock.lock();
	                	if(udpDests.containsKey(msgParts[1])){
	                		destPacket.setData(new String("nameExists").getBytes());
	                		udpServer.send(destPacket);
	                		udpLock.unlock();
	                		continue;
	                	}
                		destPacket.setData(new String("nameRegistered").getBytes());
                		udpServer.send(destPacket);
	                	udpDests.put(msgParts[1],destPacket);
	                	
	                	groupsAssignment.get("broadcast").add(msgParts[1]); //assigning user to the default, broadcast group
	                	clientGroups.put(msgParts[1], new ArrayList<String>());
	                	clientGroups.get(msgParts[1]).add("broadcast");
	                	recentClientMulticastGroup.put(msgParts[1], "broadcast");
	                	
	                	System.out.println("UDP: Registered " + msgParts[1] + " with udp destination: " + receivePacket.getAddress() + ":" + receivePacket.getPort());
	                	udpLock.unlock();
	                }
	                else{
	                	udpLock.lock();
	                	if(msg.split("@")[0].equals("multicast")){
	                		String clientName = msg.split("@")[1];
	                		String groupName = msg.split("@")[2];
	                		String content = msg.split("@")[3];
	                		
	                		List<String> userGroupsList = clientGroups.get(clientName);
	                		if(!userGroupsList.contains(groupName)) userGroupsList.add(groupName);
	                		if(!groupsAssignment.containsKey(groupName)){
	                			groupsAssignment.put(groupName, new ArrayList<String>());
	                			groupsAssignment.get(groupName).add(clientName);
	                		}
	                		else{
	                			if(!groupsAssignment.get(groupName).contains(clientName)){
		                			groupsAssignment.get(groupName).add(clientName);	                				
	                			}
	                		}
	                		recentClientMulticastGroup.put(clientName, groupName); 
	                		
	                		destPacket.setData(content.getBytes());
	                	}
	                	else{//broadcast
	                		String clientName = getClientName(destPacket);
	                		recentClientMulticastGroup.put(clientName, "broadcast");
	                	}
	                	UDPThread udpThread = new UDPThread(destPacket);
	                	executor.execute(udpThread);
	                	udpLock.unlock();
	                }
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				udpServer.close();
			}
		}
	}
	
	public static void main(String[] args){
		try{
			ip = args[0];
			port = Integer.parseInt(args[1]); //reading first ip, then port
			
			lock = new ReentrantLock();
			udpLock = new ReentrantLock();
			
			groupsAssignment = new HashMap<String,List<String>>();
			groupsAssignment.put("broadcast", new ArrayList<String>()); //setting up default group
			
			clientGroups = new HashMap<String, List<String>>();
			recentClientMulticastGroup = new HashMap<String, String>();
			
			TCPChannel tcpChannel = new TCPChannel();
			tcpChannel.start();
			
			UDPChannel udpChannel = new UDPChannel();
			udpChannel.start();
			
			MsgHandler msgHandler = new MsgHandler(); //sending incoming msgs to appropriate clients
			msgHandler.start();
			
			ServerGC serverGC = new ServerGC(); //garbage collector deleting "stale" clients
			serverGC.start();

		}
		catch(Exception e){
			System.out.println("Usage: java Server ip port");
			e.printStackTrace();
		}
	}
}
