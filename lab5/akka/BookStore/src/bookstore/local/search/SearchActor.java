/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.search;

import akka.actor.AbstractActor;
import bookstore.remote.search.PriceOfBookAnswer;
import bookstore.remote.search.TitleNotFoundAnswer;

/**
 *
 * @author Robert
 */
public class SearchActor extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, r -> {
                    getContext().actorSelection(r.getRemotePath()).tell(r, getSender());
                })
                .match(PriceOfBookAnswer.class, a->{
                    System.out.println("Title " + a.getTitle() + " has price " + a.getPrice());
                })
                .match(TitleNotFoundAnswer.class, a -> {
                    System.out.println(a.getTitle() + " was not found in a databases");
                })
                .build();
    }
    
}
