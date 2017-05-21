/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local;

import bookstore.local.search.SearchActor;
import bookstore.local.search.SearchRequest;
import bookstore.local.order.OrderRequest;
import bookstore.local.order.OrderActor;
import bookstore.local.stream.StreamActor;
import bookstore.local.stream.StreamRequest;
import akka.actor.AbstractActor;
import akka.actor.Props;
import java.util.*;

/**
 *
 * @author Robert
 */
public class LocalMainActor extends AbstractActor{
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, r -> {
                    getContext().actorSelection("StreamActor").tell(r, getSelf());
                })
                .match(SearchRequest.class, r -> {
                    getContext().actorSelection("SearchActor").tell(r, getSelf());
                })
                .match(OrderRequest.class, r -> {
                    getContext().actorSelection("OrderActor").tell(r, getSelf());
                })
                .build();
    }
    
    
    @Override 
    public void preStart(){
        getContext().actorOf(Props.create(SearchActor.class), "SearchActor");
        getContext().actorOf(Props.create(OrderActor.class), "OrderActor");
        getContext().actorOf(Props.create(StreamActor.class), "StreamActor");
        
    }
}
