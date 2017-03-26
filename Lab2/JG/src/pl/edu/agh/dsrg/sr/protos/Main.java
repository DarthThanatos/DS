package pl.edu.agh.dsrg.sr.protos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatState;

public class Main {
    	
    public static void main(String[] args) throws Exception {
    	try{
    		String userName = args[0]; 
    		Coordinator coord = new Coordinator(userName);
    		new Thread(coord).start();
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    		while(true){
    			System.out.println("Type the channel you want to be at the moment (multicast ip) or exit to leave");
    			System.out.println("Type l to list all channels with their users");
                System.out.print("> "); System.out.flush();
                String line =in.readLine().toLowerCase();
                if(line.startsWith("exit")) break;
                if(line.startsWith("l") && line.length() == 1) {
                	coord.listChannels();
                	continue;
                }
                String multicastIp = line;                
                System.out.println("Switching control to channel " + multicastIp);
                try{
                	new JChannelClient(userName, multicastIp, coord).start();
                }catch(SocketException e){
                	System.out.println("Not a valid address");
                }
                catch(UnknownHostException e){
                	System.out.println("Not a valid address");                	
                }
    		}
    		System.exit(0);
    	}catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Usage: java JChannelClient username");
    	}
    	catch(NullPointerException e){
    		
    	}
    }
}
