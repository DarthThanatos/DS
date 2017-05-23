/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import akka.japi.pf.DeciderBuilder;
import bookstore.local.order.OrderRequest;
import bookstore.remote.Order.OrdersTaker;
import bookstore.remote.search.SearchManager;
import bookstore.remote.stream.Streamer;
import scala.concurrent.duration.Duration;

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
 
    @Override
    public void postStop(){
        System.out.println(getSelf().path() + " of tmp terminated");
    }
    
    private static SupervisorStrategy ofos = 
        new OneForOneStrategy(
                -1,
                Duration.Inf(),
                DeciderBuilder
                        .match(Exception.class, e->restart())
                        .build()
        );
    
    @Override
    public SupervisorStrategy supervisorStrategy(){
        return ofos;
    }
    
}
