/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.stream;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import bookstore.local.stream.StreamRequest;

/**
 *
 * @author Robert
 */
public class Streamer extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, r->{
                    ActorRef temp = getContext().actorOf(Props.create(TempStreamSender.class, () -> new TempStreamSender("books/" + r.getTitle() + ".txt")));
                    temp.tell(r, getSender());
                })
                .build();
    }
    
}
