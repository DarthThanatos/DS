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
import org.jgroups.protocols.SEQUENCER;
import org.jgroups.protocols.UDP;
import org.jgroups.protocols.UFC;
import org.jgroups.protocols.UNICAST3;
import org.jgroups.protocols.VERIFY_SUSPECT;
import org.jgroups.protocols.pbcast.FLUSH;
import org.jgroups.protocols.pbcast.GMS;
import org.jgroups.protocols.pbcast.NAKACK2;
import org.jgroups.protocols.pbcast.STABLE;
import org.jgroups.stack.ProtocolStack;

import com.google.protobuf.InvalidProtocolBufferException;

import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation;
import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation.OperationType;

public class JChannelServer extends ReceiverAdapter{

    JChannel channel;
    //String user_name=System.getProperty("user.name", "na");    
    //System.setProperty("java.net.preferIPv4Stack", "true");

    private void start() throws Exception {
        channel=new JChannel(); // use the default config, udp.xml
        ProtocolStack stack= channel.getProtocolStack();
        stack.addProtocol(new SEQUENCER())
        	 .addProtocol(new FLUSH());
        //stack.init();
        channel.setReceiver(this);
        channel.connect("operation");
        eventLoop();
        channel.close();
    }
    
    private void eventLoop(){
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
        	
        }   	
    }
    
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
    	byte[] raw_stream = msg.getBuffer();
    	try {
			BankOperation operation = BankOperation.parseFrom(raw_stream);
			System.out.println(msg.getSrc() + ": type: " +  operation.getType() +" val:" + operation.getValue());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
       
    }
    
    public static void main(String[] args) throws Exception {
        new JChannelServer().start();
    }
}
