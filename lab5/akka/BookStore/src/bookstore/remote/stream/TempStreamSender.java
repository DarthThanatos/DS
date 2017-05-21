/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.stream;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.FileIO;
import akka.stream.javadsl.Framing;
import akka.stream.javadsl.Sink;
import akka.util.ByteString;
import bookstore.local.stream.StreamRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import scala.Int;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Robert
 */
public class TempStreamSender extends AbstractActor{

    private final String bookPath;
    
    public TempStreamSender(String bookPath){
        this.bookPath = bookPath;
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, r ->{
                    System.out.println("Stream sender running " + bookPath + " path: " + getSelf().path());
                    Path file = Paths.get(bookPath);
                    Materializer materializer = ActorMaterializer.create(getContext());
                    Sink<ByteString, NotUsed> sinkOfClient = Sink.actorRef(getSender(), new EndOfFileStream(r.getTitle(), getSelf().path().toString()));
                    FileIO
                        .fromPath(file)
                        .via(Framing.delimiter(ByteString.fromString("\n"), Int.MaxValue()))
                        .throttle(1, Duration.create(1, TimeUnit.SECONDS), 1, ThrottleMode.shaping())
                        .to(sinkOfClient)
                        .run(materializer);
                    
                        /*.thenRun(() -> {
                            System.out.println("Sender terminating");
                            getContext().stop(getSelf());
                        });*/
                })
                .match(EndOfFileStream.class, e -> {
                            System.out.println("Sender terminating");
                            getContext().stop(getSelf());
                        }
                )
                .build();
    }
    
    
    
}
