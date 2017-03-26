package pl.edu.agh.dsrg.sr.protos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

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

import com.google.protobuf.InvalidProtocolBufferException;

import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatAction.ActionType;
import pl.edu.agh.dsrg.sr.protos.ChatOperationProtos.ChatMessage;


public class JChannelClient extends ReceiverAdapter{
    JChannel channel;
    private static String userName = null;
    private static String multicastIp = null; //"230.0.0.36"
    private Coordinator coord;
    
    private void atExit(){
        Runtime.getRuntime().addShutdownHook(
	        new Thread()
	        {
	            public void run()
	            {
	            	try {
						coord.generateChatAction(multicastIp, userName,ActionType.LEAVE);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        } 
        );
    }
    
    public JChannelClient(String userName, String multicastIp, Coordinator coord) throws Exception{
    	this.userName = userName;
    	this.multicastIp = multicastIp;
    	this.coord = coord;
    	System.setProperty("java.net.preferIPv4Stack", "true");
        channel=new JChannel(false); 
        channel.setName(userName);
        ProtocolStack stack=  new ProtocolStack();
        channel.setProtocolStack(stack);
        stack.addProtocol(new UDP().setValue("mcast_group_addr",InetAddress.getByName(multicastIp)))
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
        channel.connect(multicastIp);
    	this.coord.generateChatAction(multicastIp, userName, ActionType.JOIN);
    }
    
    public void receive(Message msg){
    	try {
			System.out.println(ChatMessage.parseFrom(msg.getBuffer()).getMessage());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
    }
    
    
    private void eventLoop(){
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	while(true){
            try {
                System.out.print("[Type quit to go back to menu " + userName + " " + multicastIp + "]> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit")){
					coord.generateChatAction(multicastIp, userName,ActionType.LEAVE);
                    break;
                }
                if(line.startsWith("l") && line.length() == 1) {
                	coord.listChannels();
                	continue;
                }
                line="[" + userName + "] " + line;
                ChatMessage chatMsg = ChatMessage.newBuilder().setMessage(line).build();
                Message msg=new Message(null, null, chatMsg.toByteArray());
                channel.send(msg);
            }

            catch(Exception e) {

            }
    	}
    }

	public void start() {
		eventLoop();
		channel.close();
	}
	
}
