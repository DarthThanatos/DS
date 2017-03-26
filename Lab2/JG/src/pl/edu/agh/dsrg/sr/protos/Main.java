package pl.edu.agh.dsrg.sr.protos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatState;

public class Main {
    
	private static void listChannels(Coordinator coord){
		ChatState cs = coord.getState();
		HashMap <String, ArrayList<String>> channelsMap = new HashMap<String,ArrayList<String>>();
		for (ChatAction entry : cs.getStateList()){
			if(!channelsMap.containsKey(entry.getChannel())){
				channelsMap.put(entry.getChannel(), new ArrayList<String>());
			}
			ArrayList<String> users = channelsMap.get(entry.getChannel());
			users.add(entry.getNickname());
		}
		for(String channel: channelsMap.keySet()){
			System.out.println("In channel " + channel + ": ");
			for (String user : channelsMap.get(channel)){
				System.out.println("\t->" + user);
			}
		}
	}
	
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
                	listChannels(coord);
                	continue;
                }
                String multicastIp = line;                
                System.out.println("Switching control to channel " + multicastIp);
                new JChannelClient(userName, multicastIp, coord).start();
    		}
    		System.exit(0);
    	}catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Usage: java JChannelClient username");
    	}
    }
}
