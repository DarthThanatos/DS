/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.stream;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.util.ByteString;

/**
 *
 * @author Robert
 */
public class StreamGateway extends AbstractActor{

    private final ActorRef actorToTerminate;
    private final String remoteRecipentPath;
    
    public StreamGateway(ActorRef actorToTerminate, String remoteRecipentPath){
        this.actorToTerminate = actorToTerminate;
        this.remoteRecipentPath = remoteRecipentPath;
        System.out.println("=========================================");
        System.out.println("StreamGateway recipient:" + remoteRecipentPath);
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ByteString.class, b -> {
                    getContext().actorSelection(remoteRecipentPath).tell(b,getSender());
                    System.out.println(b.utf8String());
                })
                .match(EndOfFileStream.class, e -> {
                    getContext().actorSelection(remoteRecipentPath).tell(e,getSender());
                    System.out.println("Terminating " + actorToTerminate);
                    getContext().stop(actorToTerminate);
                })
                .build();
    }
 
    @Override
    public void postStop(){
        System.out.println(getSelf().path() + " of type StreamGateway terminated");
    }   
}
