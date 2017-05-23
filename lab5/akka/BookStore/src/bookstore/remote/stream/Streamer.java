/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.stream;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.resume;
import akka.japi.pf.DeciderBuilder;
import bookstore.local.stream.StreamRequest;
import java.io.FileNotFoundException;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Robert
 */
public class Streamer extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, r->{
                    String remoteRecipentPath = getSender().path().toString();
                    System.out.println("Streamer: got client " + remoteRecipentPath);
                    ActorRef temp = getContext()
                            .actorOf(
                                    Props.create(
                                            TempStreamSender.class,
                                            () -> new TempStreamSender("books/" + r.getTitle() + ".txt",remoteRecipentPath)
                                    )
                            );
                    temp.tell(r, getSender());
                })
                .build();
    }
    
    @Override
    public void postStop(){
        System.out.println(getSelf().path() + " of tmp terminated");
    }
    
        private static SupervisorStrategy ofos = 
            new OneForOneStrategy(
                    -1,
                    Duration.Inf(),
                    DeciderBuilder
                            .match(Exception.class, e->escalate())
                            .build()
            );
    @Override
    public SupervisorStrategy supervisorStrategy(){
        return ofos;
    }
}
