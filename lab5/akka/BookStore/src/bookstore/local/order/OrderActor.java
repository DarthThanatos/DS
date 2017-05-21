/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.order;

import akka.actor.AbstractActor;
import bookstore.remote.Order.OrderAck;

/**
 *
 * @author Robert
 */
public class OrderActor extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderRequest.class, r->{
                    System.out.println("Got order request for title: " + r.getTitle()
                        + "; sending to " + r.getRemotePath());
                    getContext().actorSelection(r.getRemotePath()).tell(r, getSelf());
                })
                .match(OrderAck.class, a -> {
                    System.out.println("Bookstore acknowledged "  + a.getAcknowledgedTitle());
                })
                .build();
    }
    
}
