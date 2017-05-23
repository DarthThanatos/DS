/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.stream;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;
import akka.japi.pf.DeciderBuilder;
import akka.stream.ActorMaterializer;
import akka.stream.Attributes;
import akka.stream.Materializer;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.FileIO;
import akka.stream.javadsl.Framing;
import akka.stream.javadsl.Sink;
import akka.util.ByteString;
import bookstore.local.stream.StreamRequest;
import java.io.FileNotFoundException;
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
    private final ActorRef streamGateway;
    
    public TempStreamSender(String bookPath, String remoteRecipentPath){
        this.bookPath = bookPath;
        this.streamGateway = getContext().actorOf(Props.create(StreamGateway.class, () -> new StreamGateway(getSelf(), remoteRecipentPath)));
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, r ->{
                    System.out.println("Stream sender running " + bookPath + " path: " + getSelf().path());
                    Path file = Paths.get(bookPath);
                    Materializer materializer = ActorMaterializer.create(getContext());
                    Sink<ByteString, NotUsed> sinkOfClient = Sink.actorRef(streamGateway, new EndOfFileStream(r.getTitle()));

                    FileIO
                        .fromPath(file)
                        .via(Framing.delimiter(ByteString.fromString("\n"), Int.MaxValue()))
                        .throttle(1, Duration.create(1, TimeUnit.SECONDS), 1, ThrottleMode.shaping())
                        .to(sinkOfClient)
                        .run(materializer);
                        
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
