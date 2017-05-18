/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akkalab.z1b;

import akka.actor.AbstractActor;
import akka.actor.TypedActor.Receiver;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author Robert
 */
public class Z1b_DivideWorker extends AbstractActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);  
    private int counter = 0;
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    String result = divide(s); 
                    counter ++;
                    getSender().tell("result: " + result + " operations amount: " + counter, getSelf());
                })
                .matchAny(o -> log.info("Divider received unknown message"))
                .build();
    }
 
    private String divide(String s){
        String[] split = s.split(" ");
        int a = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        return (a/b) + "";
    }   
}
