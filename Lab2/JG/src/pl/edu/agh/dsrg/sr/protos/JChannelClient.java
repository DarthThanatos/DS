package pl.edu.agh.dsrg.sr.protos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.protocols.SEQUENCER;
import org.jgroups.protocols.pbcast.FLUSH;
import org.jgroups.stack.ProtocolStack;

import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation;
import pl.edu.agh.dsrg.sr.protos.BankOperationProtos.BankOperation.OperationType;


public class JChannelClient {
    JChannel channel;
    
    public JChannelClient() throws Exception{
    	
    }

    private void start() throws Exception {
        channel=new JChannel(); // use the default config, udp.xml
        ProtocolStack stack= channel.getProtocolStack();
        //channel.setProtocolStack(stack);
        stack.addProtocol(new SEQUENCER());
        	 //.addProtocol(new FLUSH());
        stack.init();
        channel.connect("operation");
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
        try {
				BankOperation operation = generateOperation();
				byte[] opBytes = operation.toByteArray();
                Message msg=new Message(null, null, opBytes);
                channel.send(msg);
        }
            catch(Exception e) {
        } 	
    }
    
    public static void main(String[] args) throws Exception {
        new JChannelClient().start();
    }
	
}
