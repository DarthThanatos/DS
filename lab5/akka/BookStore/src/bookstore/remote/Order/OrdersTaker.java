/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.Order;

import akka.actor.AbstractActor;
import bookstore.local.order.OrderRequest;

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
                    System.out.println("Taking order: title " + r.getTitle() + "; for path: " + r.getLocalPath());
                    ordersSaver.save(r.getTitle());
                    getSender().tell(new OrderAck(r.getTitle()), getSelf());
                })
                .build();
    }
    
}
