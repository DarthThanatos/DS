package pl.edu.agh.dsrg.sr.protos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation;
import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation.OperationType;


public class JChannelClient extends ReceiverAdapter{
    JChannel channel;
    private static String userName = null;
    
    public JChannelClient() throws Exception{
    	System.setProperty("java.net.preferIPv4Stack", "true");
    	System.setProperty("user.name", userName);
        channel=new JChannel(false); 
        ProtocolStack stack=  new ProtocolStack();
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
        channel.connect("operation");
    	
    }

    public void viewAccepted(View newView){
    	System.out.println("** view: " + newView);
    	newView.getMembers();
    }
    
    public void receive(Message msg){
    	System.out.println(msg.getSrc() + ":" + msg.getObject());
    }
    
    private void start() throws Exception {
        eventLoop();
        channel.close();
    }

	private static BankOperation generateOperation(){
		BankOperation operation;
		operation = BankOperation.newBuilder()
								 .setValue(Math.random()/100+1.0)
								 .setType(OperationType.MULTIPLY).build();
		 System.out.println(new String(operation.toByteArray()));
		 System.out.println(operation.toString());
		 return operation;
		
	}
    
    private void eventLoop(){

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	while(true){
            try {
                System.out.print("> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit"))
                    break;
                line="[" + userName + "] " + line;
                Message msg=new Message(null, null, line);
                channel.send(msg);

            }

            catch(Exception e) {

            }
    	}
    }
    
    public static void main(String[] args) throws Exception {
    	try{
    		userName = args[0]; 
    		new JChannelClient().start();
    	}catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Usage: java JChannelClient username");
    	}
    }
	
}
