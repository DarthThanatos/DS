package akkalab.z3;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
public class Z3 {

    public static void main(String[] argv) throws Exception {

        final ActorSystem system = ActorSystem.create("stream_system");
        final Materializer materializer = ActorMaterializer.create(system);
        final ActorRef actor = system.actorOf(Props.create(Z3_SinkActor.class), "sink");

        final Source<Integer, NotUsed> source = Source.range(2, 5);      
        final Flow factorialFlow = Flow.of(Integer.class).scan (1, (acc, next) -> {return acc * next; });
        final Sink<Integer, NotUsed> sinkOfFactorials = Sink.actorRef(actor, new CloseSystemMsg());
        
        final Sink<Integer, NotUsed> sinkOfFactorialsCombined = factorialFlow.toMat(sinkOfFactorials, Keep.right());
        source.runWith(sinkOfFactorialsCombined, materializer);
    }
}
