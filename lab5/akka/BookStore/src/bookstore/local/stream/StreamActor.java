/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.stream;

import akka.actor.AbstractActor;
import akka.util.ByteString;
import bookstore.remote.stream.EndOfFileStream;

/**
 *
 * @author Robert
 */
public class StreamActor extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, r -> {
                    getContext()
                            .actorSelection(r.getRemotePath())
                            .tell(r, getSelf());
                })
                .match(ByteString.class, b -> {
                    System.out.println(b.utf8String());
                })
                .match(EndOfFileStream.class, e -> {
                    System.out.println("Ended streaming " + e.getTitle());
                    getContext().actorSelection(e.getTempPath()
                            .replace("akka", "akka.tcp")
                            .replace("remote_system", "remote_system@127.0.0.1:3552"))
                        .tell(e, getSelf());
                })
                .build();
    }
    
}
