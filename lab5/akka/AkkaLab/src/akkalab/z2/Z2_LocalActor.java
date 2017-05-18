package akkalab.z2;

import akkalab.*;
import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Z2_LocalActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s->{                
                    String path = "akka.tcp://remote_system@127.0.0.1:3552/user/remote";//"akka://remote_system/user/remote";
                    ActorSelection remote = getContext().actorSelection(path);
                    remote.tell(s, getSelf());                    
                })
                .match(Answer.class, a -> {
                    System.out.println(a.getAnswer());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
