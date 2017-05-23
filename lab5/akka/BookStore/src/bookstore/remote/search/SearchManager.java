/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import akka.japi.pf.DeciderBuilder;
import akka.routing.RoundRobinPool;
import bookstore.local.search.SearchRequest;
import java.io.FileNotFoundException;
import java.util.HashMap;

import java.util.UUID;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Robert
 */
public class SearchManager extends AbstractActor{

    private HashMap<String, SearchResult> actualSearchResults = new HashMap<>(); 
    // ^ put result is or-wise object
       
    class SearchResult{
        public boolean found;
        public int price;
        public int count;
        public int expectedReports = 2;
        public String title;
        public ActorRef target;
                
        public SearchResult(String title, ActorRef target){
            found = false;
            price = 0;
            count = 0;
            this.target = target;
            this.title = title;
        }
        
        public synchronized void updateResult(boolean found, int price){
            count ++;
            this.found |= found;
            if(found) this.price = price;
        }
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, r ->{ 
                    SearchResult defRes = new SearchResult(r.getSearchedTitle(), getSender());
                    SearchTriggerMsg trigger = new SearchTriggerMsg(r);
                    actualSearchResults.put(trigger.getUUID(), defRes);
                    
                    getContext().actorSelection("DB1Searcher").tell(trigger, getSelf());
                    getContext().actorSelection("DB2Searcher").tell(trigger, getSelf());
                })
                .match(DBSearchResult.class, r -> {
                        System.out.println("Got DBRes");
                        SearchResult actualRes = actualSearchResults.get(r.getUUID());
                        actualRes.updateResult(r.titleFound(), r.getPrice());
                        if(actualRes.count == actualRes.expectedReports){
                            System.out.println("Ready to send reply");
                            if(actualRes.found){
                                PriceOfBookAnswer answer = new PriceOfBookAnswer(actualRes.title, actualRes.price);
                                System.out.println("Target of price info: " + actualRes.target.path());
                                actualRes.target.tell(answer, getSelf());
                            }
                            else{
                                TitleNotFoundAnswer notFoundAnswer = new TitleNotFoundAnswer(actualRes.title);
                                System.out.println("Target of not found: " + actualRes.target.path());
                                actualRes.target.tell(notFoundAnswer, getSelf());
                            }
                            actualSearchResults.remove(r.getUUID()); // reply sent, no need to store this in a db
                        }
                })
                .build();
    }
    
    
    @Override
    public void preStart(){
        getContext().actorOf(new RoundRobinPool(5).props(Props.create(DBSearcher.class, () -> new DBSearcher("DB1.txt"))), "DB1Searcher");
        getContext().actorOf(new RoundRobinPool(5).props(Props.create(DBSearcher.class, () -> new DBSearcher("DB2.txt"))), "DB2Searcher");
    }
    
    
    private static SupervisorStrategy ofos = 
        new OneForOneStrategy(
                -1,
                Duration.Inf(),
                DeciderBuilder
                        .match(Exception.class, e->escalate())
                        .build()
        );
    
    @Override
    public SupervisorStrategy supervisorStrategy(){
        return ofos;
    }
}
