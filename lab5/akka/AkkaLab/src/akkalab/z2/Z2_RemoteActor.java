package akkalab.z2;

import akkalab.*;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Z2_RemoteActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    System.out.println("Remote got: " + s);
                    getSender().tell(new Answer(s.toUpperCase()), getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
