/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.Order;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import akka.japi.pf.DeciderBuilder;
import bookstore.local.order.OrderRequest;
import java.io.FileNotFoundException;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Robert
 */
public class OrdersTaker extends AbstractActor{

    private final OrdersSaver ordersSaver;
    
    public OrdersTaker(){
        ordersSaver = new OrdersSaver();
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderRequest.class, r -> {
                    System.out.println("Taking order: title " + r.getTitle());
                    ordersSaver.save(r.getTitle());
                    getSender().tell(new OrderAck(r.getTitle()), getSelf());
                })
                .build();
    }
    
    
    
    private static SupervisorStrategy ofos = 
        new OneForOneStrategy(
                -1,
                Duration.Inf(),
                DeciderBuilder
                        .match(FileNotFoundException.class, e->restart())
                        .match(Exception.class, e->escalate())
                        .build()
        );
    
    @Override
    public SupervisorStrategy supervisorStrategy(){
        return ofos;
    }
}
