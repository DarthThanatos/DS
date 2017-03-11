package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {	
	static DatagramSocket udpServer = null;
	static Socket server = null;
	static Scanner scanner = null;
	static String clientName = null;
	static boolean shouldContinue = true;
	
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
					if(response.length() != 0) System.out.println(response);

				}
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("ServerHandler: Connection closed");
				shouldContinue = false;
			}
		}
	}
	
	public static void main(String[]args){
		try{
			if(args.length != 3){
				System.out.println("Usage: java Client ip port id");
				System.exit(-1);
			}
			scanner = new Scanner(System.in);
			String ip = args[0];
			int port = Integer.parseInt(args[1]);
			clientName = args[2];
			
			String asciiPath = "../AsciiArt.txt";
			BufferedReader asciiArt = new BufferedReader(new FileReader(new File(asciiPath)));
			
			int len = 1024;
			char[] cbuf = new char[len];
			asciiArt.read(cbuf, 0, len);
			System.out.println(new String(cbuf));
			
			server = new Socket(ip, port);
			udpServer = new DatagramSocket(port);
			ServerMsgHandler serverMsgHandler = new ServerMsgHandler();
			serverMsgHandler.start();
			new PrintWriter(server.getOutputStream(),true).println(clientName + "\n");
			while(shouldContinue){
				System.out.println("You are: " + clientName + "; Type S to send sth to Server or Q to quit:");
				String modeLetter = scanner.nextLine();
				char mode = modeLetter.length() > 0 ? modeLetter.charAt(0) : 'X';
				switch(mode){
					case 'S':
						sendMsg();
						break;
					case 'M':
						break;
					case 'N':
						break;
					case 'Q':
						shouldContinue = false;
						break;
					default:
						System.out.println("Wrong mode selected");
						break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Usage: java Client ip port id");
		}
		finally{
			try {
				server.close();
				System.out.println("Exiting");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
