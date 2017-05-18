package akkalab.z3;

import akka.actor.AbstractActor;
import akkalab.*;

public class Z3_SinkActor extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CloseSystemMsg.class, c -> {
                    System.out.println("Actor shutting down system" );
                    getContext().system().terminate();
                })
                .match(Integer.class, s->{System.out.println("Actor printing integer: " + s);})
                .build();
    }
}
