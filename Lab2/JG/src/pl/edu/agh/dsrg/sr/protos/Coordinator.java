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
    	System.out.println("** view: " + newView);
    	System.out.println("In group:");
    	for (Address adress : newView.getMembers()){
    		System.out.println("\t-> " + adress.toString());
    	}
    	System.out.println("Left:");
    	if(oldView != null){
			List<ChatAction> registered = new ArrayList<ChatAction>(); 
			registered.addAll(cs.getStateList());
    		Address[][] diff = View.diff(oldView, newView);
    		System.out.println("Old view is not null " + diff.length + " " + diff[0].length + " " + diff[1].length);
    		for(int i = 0 ; i < diff[1].length; i++){
    			for(int j = 0; j < oldView.getMembers().size(); j++){
    				if(oldView.get(j).toString().equals(diff[1][i].toString())){
    					System.out.println("Removing: " + registered.get(j).toString().replace("\n",";"));
    					synchronized(registered){
    						registered.remove(j);
    					}
    				}
    			}
    		}
			cs = ChatState.newBuilder().addAllState(registered).build();
    	}
    	oldView = newView;    	
    }
    
    
    private void removeStateEntry(List<ChatAction> registered, ChatAction chatAction){
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
					System.out.println("Adding " + chatAction.toString().replace("\n", "; ") + " with src " + msg.src());
					registered.add(chatAction);
					actionUndertook = "adding";
					registrationMap.put(msg.src().toString(), chatAction);
				}
				else{
					System.out.println("Removing " + chatAction.toString());
					removeStateEntry(registered,chatAction);
					registrationMap.remove(msg.src().toString());
					actionUndertook = "removing";
				}

				cs = ChatState.newBuilder().addAllState(registered).build();
				System.out.println("State after: " + actionUndertook); 
				for(ChatAction entry: registered) System.out.println("\t->" + entry.toString().replace("\n", "; "));
	    	}
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
    }
    
    
    private void eventLoop(){
    	while(true);
    }
    
    public void generateChatAction(String channelName, String userName, ActionType actionType) throws Exception{
    	System.out.println("Generating chat action from " + channelName + userName +  actionType);
    	ChatAction res = ChatAction.newBuilder().setAction(actionType).setChannel(channelName).setNickname(userName).build();
    	channel.send(new Message(null, null, res.toByteArray()));
    }
    
    public void setState(InputStream input) throws Exception{
    	cs = ChatState.parseFrom( input );
		System.out.println("After setState: "); 
		for(ChatAction entry: cs.getStateList()) System.out.println("\t->" + entry.toString().replace("\n","; "));
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
