package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*
;

public class ServerThread extends Thread {
	
	LinkedBlockingQueue<String> msgQueue; 
	Socket connectedClient = null;
	BufferedReader inFromClient = null;
	PrintWriter outToClient = null;
	private String clientName;

	public ServerThread(Socket client, String clientName, LinkedBlockingQueue<String> msgQueue) {
		System.out.println( "The Client " +  client.getInetAddress() + ":" + client.getPort() + " with id: " + clientName + " is connected");
		connectedClient = client;
		this.msgQueue = msgQueue;
		this.clientName = clientName;
		try{
			inFromClient = new BufferedReader(new InputStreamReader (connectedClient.getInputStream()));
			outToClient = new PrintWriter(connectedClient.getOutputStream(),true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getClientName(){
		return clientName;
	}
	
	public boolean isClosed(){
		return connectedClient.isClosed();
	}
	
	public void run() {
		try {
			while(true){
				String clientString = inFromClient.readLine();
				msgQueue.put(clientString);
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Client " + clientName +  " disconnected");
			try {
				connectedClient.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
		}
	}

	public void sendResponse (String senderName, String responseString){
		String response = senderName + " wrote: " + responseString + "\n";
		System.out.println("Sending: \'" + response.replace("\n", "") + "\' to: " + clientName);
		try{
			outToClient.println(response);
		}
		catch(Exception e){
			System.out.println("Client: " + clientName + " is already closed!");
		}
	}
}