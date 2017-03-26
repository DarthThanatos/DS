package pl.edu.agh.dsrg.sr.protos;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.protocols.BARRIER;
import org.jgroups.protocols.FD_ALL;
import org.jgroups.protocols.FD_SOCK;
import org.jgroups.protocols.FRAG2;
import org.jgroups.protocols.MERGE3;
import org.jgroups.protocols.MFC;
import org.jgroups.protocols.PING;
import org.jgroups.protocols.UDP;
import org.jgroups.protocols.UFC;
import org.jgroups.protocols.UNICAST3;
import org.jgroups.protocols.VERIFY_SUSPECT;
import org.jgroups.protocols.pbcast.FLUSH;
import org.jgroups.protocols.pbcast.GMS;
import org.jgroups.protocols.pbcast.NAKACK2;
import org.jgroups.protocols.pbcast.STABLE;
import org.jgroups.protocols.pbcast.STATE_TRANSFER;
import org.jgroups.stack.ProtocolStack;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.InvalidProtocolBufferException;

import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatState;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction.ActionType;

public class Coordinator extends ReceiverAdapter implements Runnable {
    JChannel channel;
    ChatState cs;
    View oldView;
    
    HashMap<String, ChatAction> registrationMap;
    
    public ChatState getState(){
    	return cs;
    }
    
    public Coordinator(String userName) throws Exception{
       	cs = ChatState.newBuilder().getDefaultInstanceForType();
    	System.setProperty("java.net.preferIPv4Stack", "true");
    	registrationMap = new HashMap<String,ChatAction>();
        channel=new JChannel(false);
        channel.setName(userName);
        ProtocolStack stack=  new ProtocolStack();
        channel.setProtocolStack(stack);
        stack.addProtocol(new UDP())
        .addProtocol(new PING())
        .addProtocol(new MERGE3())
        .addProtocol(new FD_SOCK())
        .addProtocol(new FD_ALL().setValue("timeout", 12000).setValue("interval", 3000))
        .addProtocol(new VERIFY_SUSPECT())
        .addProtocol(new BARRIER())
        .addProtocol(new NAKACK2())
        .addProtocol(new UNICAST3())
        .addProtocol(new STABLE())
        .addProtocol(new GMS())
        .addProtocol(new UFC())
        .addProtocol(new MFC())
        .addProtocol(new FRAG2())
        .addProtocol(new STATE_TRANSFER())
        .addProtocol(new FLUSH());
        stack.init();
        channel.setReceiver(this);
        channel.connect("ChatManagement321321");
        channel.getState(null, 1000);	
    }

	public void viewAccepted(View newView){
    	if(oldView != null){
        	for(Address address: View.leftMembers(oldView, newView)){
        		//System.out.println("\t->" + address.toString().replace("\n",";") + " has left");
        	}
			List<ChatAction> registered = new ArrayList<ChatAction>(); 
			registered.addAll(cs.getStateList());
			for(Address address : View.leftMembers(oldView, newView)){
				//System.out.println("Client " + address + " crashed; Removing all entries concerning them" );
				Iterator<ChatAction> iter = registered.iterator();
				while(iter.hasNext()){
					ChatAction entry = iter.next();
					/* removes entry where nickname matches*/
					if(address.toString().equals(entry.getNickname())){
						//System.out.println("Removing " + entry.toString().replace("\n", ";"));
						iter.remove();
					}
				}
			}
			cs = ChatState.newBuilder().addAllState(registered).build();
			//System.out.println("State after change of view:"); 
			//for(ChatAction entry: registered) System.out.println("\t->" + entry.toString().replace("\n", "; "));
    	}
    	oldView = newView;    	
    }
    
    
	public void listChannels(){
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
	
    private void removeStateEntry(List<ChatAction> registered, ChatAction chatAction){
    	/* removes entry where all fields match*/
		Iterator<ChatAction> iter = registered.iterator();
		while(iter.hasNext()){
			ChatAction entry = iter.next();
			if(chatAction.getChannel().equals(entry.getChannel()) &&
			   chatAction.getNickname().equals(entry.getNickname())){
				iter.remove();
			}
		}
    }
    
    public void receive(Message msg){
    	try {
			ChatAction chatAction = ChatAction.parseFrom(msg.getBuffer());
			List<ChatAction> registered = new ArrayList<ChatAction>(); 
			registered.addAll(cs.getStateList());
			String actionUndertook = "";
	    	synchronized(registered){	
				if(chatAction.getAction().equals(ActionType.JOIN)){
					//System.out.println("Adding " + chatAction.toString().replace("\n", "; ") + " with src " + msg.src());
					registered.add(chatAction);
					actionUndertook = "adding";
					registrationMap.put(msg.src().toString(), chatAction);
				}
				else{
					//System.out.println("Removing " + chatAction.toString());
					removeStateEntry(registered,chatAction);
					registrationMap.remove(msg.src().toString());
					actionUndertook = "removing";
				}

				cs = ChatState.newBuilder().addAllState(registered).build();
				//System.out.println("State after: " + actionUndertook); 
				//for(ChatAction entry: registered) System.out.println("\t->" + entry.toString().replace("\n", "; "));
	    	}
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
    }
    
    
    private void eventLoop(){
    	while(true);
    }
    
    public void generateChatAction(String channelName, String userName, ActionType actionType) throws Exception{
    	//System.out.println("Generating chat action from " + channelName + userName +  actionType);
    	ChatAction res = ChatAction.newBuilder().setAction(actionType).setChannel(channelName).setNickname(userName).build();
    	channel.send(new Message(null, null, res.toByteArray()));
    }
    
    public void setState(InputStream input) throws Exception{
    	cs = ChatState.parseFrom( input );
		//System.out.println("After setState: "); 
		//for(ChatAction entry: cs.getStateList()) System.out.println("\t->" + entry.toString().replace("\n","; "));
    }
    
    public void getState(OutputStream output) throws Exception{
    	output.write(cs.toByteArray());
    }

	@Override
	public void run() {
		try {
	        eventLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
