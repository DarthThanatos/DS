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
                    String remotePath = "akka.tcp://remote_system@127.0.0.1:3552/user/remote/OrdersTaker";
                    System.out.println("Got order request for title: " + r.getTitle()
                        + "; sending to " + remotePath);
                    getContext().actorSelection(remotePath).tell(r, getSelf());
                })
                .match(OrderAck.class, a -> {
                    System.out.println("Bookstore acknowledged "  + a.getAcknowledgedTitle());
                })
                .build();
    }
    
}
