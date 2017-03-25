package pl.edu.agh.dsrg.sr.protos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
import org.jgroups.util.Util;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatState;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction.ActionType;

public class Coordinator extends ReceiverAdapter implements Runnable {
    JChannel channel;
    ClientPanel cp;
    ChatState cs;
    View oldView;
    
    public Coordinator(ClientPanel cp) throws Exception{
       	this.cp = cp;
       	cs = ChatState.newBuilder().getDefaultInstanceForType();
    	System.setProperty("java.net.preferIPv4Stack", "true");
        channel=new JChannel(false); 
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
        channel.getState(null, 10000);
    }

    public void viewAccepted(View newView){
    	System.out.println("** view: " + newView);
    	System.out.println("In group:");
    	channel.getView();
    	for (Address adress : newView.getMembers()){
    		System.out.println(adress.toString());
    	}
    	System.out.println("Left:");
    	if(oldView != null)
	    	for (Address address : newView.leftMembers(oldView, newView)){
	        	System.out.println(address.toString());    		
	    	}
    	oldView = newView;
    	
    }
    
    private boolean stateHasEntry(ChatAction chatAction){
		for(ChatAction entry : cs.getStateList()){
			if(entry.equals(chatAction)) return true;
		}
    	return false;
    }
    
    private void removeStateEntry(ChatAction chatAction){
		for(ChatAction entry : cs.getStateList()){
			if(entry.equals(chatAction)){
				
			}
		}    	
    }
    
    public void receive(Message msg){
    	try {
			ChatAction chatAction = ChatAction.parseFrom(msg.getBuffer());
			List<ChatAction> registered = new ArrayList<ChatAction>(); 
			registered.addAll(cs.getStateList());
	    	synchronized(registered){	
				if(chatAction.getAction().equals(ActionType.JOIN)){
					System.out.println("Adding " + chatAction.toString());
					if(!registered.contains(chatAction)){
						registered.add(chatAction);
					}	
				}
				else{
					System.out.println("Removing " + chatAction.toString());
					registered.remove(chatAction);
				}

				cs = ChatState.newBuilder().addAllState(registered).build();
				System.out.println("State after: "); 
				for(ChatAction entry: registered) System.out.println(entry.toString());
	    	}
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
    }
    
    
    private void eventLoop(){

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	while(true){
            try {
            	
                //Message msg=new Message(null, null, line);
                //channel.send(msg);
            }

            catch(Exception e) {

            }
    	}
    }
    
    public void generateChatAction(String channelName, String userName, ActionType actionType) throws Exception{
    	System.out.println("Generating chat action from " + channelName + userName +  actionType);
    	ChatAction res = ChatAction.newBuilder().setAction(actionType).setChannel(channelName).setNickname(userName).build();
    	channel.send(new Message(null, null, res.toByteArray()));
    }
    
    public void setState(InputStream input) throws Exception{
    	cs = ChatState.parseFrom( input );
		System.out.println("State after synchronization: "); 
		for(ChatAction entry: cs.getStateList()) System.out.println(entry.toString());
		
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
