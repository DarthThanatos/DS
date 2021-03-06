package akkalab.z1b;

import akka.actor.AbstractActor;
import akka.actor.AllForOneStrategy;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

public class Z1b_MathActor extends AbstractActor {

    // for logging
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private static SupervisorStrategy ofos = 
            new OneForOneStrategy(
                    -1,
                    Duration.Inf(),
                    DeciderBuilder
                            .match(ArithmeticException.class, e ->  resume())
                            .match(Exception.class, e->restart())
                            .build()
            );
    
    private static SupervisorStrategy afos = 
            new AllForOneStrategy(
                    -1,
                    Duration.Inf(),
                    DeciderBuilder
                            .match(ArithmeticException.class, e ->  resume())
                            .match(Exception.class, e->restart())
                            .build()
            );
    @Override
    public SupervisorStrategy supervisorStrategy(){
        return afos;
    }
    
    // must be implemented -> creates initial behaviour
    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    if (s.equals("hi")) {
                        System.out.println("hello");
                    } else if (s.startsWith("m")) {
                        context().child("multiplyWorker").get().tell(s, getSelf()); // send task to child
                    } else if(s.startsWith("d")){
                        context().child("divideWorker").get().tell(s, getSelf());
                    } else if (s.startsWith("result")) {
                        System.out.println(s);              // result from child
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    // optional
    @Override
    public void preStart() throws Exception {
        context().actorOf(Props.create(Z1b_MultiplyWorker.class), "multiplyWorker");
        context().actorOf(Props.create(Z1b_DivideWorker.class), "divideWorker");
    }

//    private static SupervisorStrategy strategy
//            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
//                    // todo: match arithmetic exception
//                    matchAny(o -> restart()).
//                    build());
//
//    @Override
//    public SupervisorStrategy supervisorStrategy() {
//        return strategy;
//    }

}
