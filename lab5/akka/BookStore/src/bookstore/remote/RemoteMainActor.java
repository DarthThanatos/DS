/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote;

import akka.actor.AbstractActor;
import akka.actor.Props;
import bookstore.local.order.OrderRequest;
import bookstore.remote.Order.OrdersTaker;
import bookstore.remote.search.SearchManager;
import bookstore.remote.stream.Streamer;

/**
 *
 * @author Robert
 */
public class RemoteMainActor extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
    
    @Override 
    public void preStart(){
        getContext().actorOf(Props.create(OrdersTaker.class), "OrdersTaker");
        getContext().actorOf(Props.create(SearchManager.class), "SearchManager");
        getContext().actorOf(Props.create(Streamer.class), "Streamer");
    }
    
}
