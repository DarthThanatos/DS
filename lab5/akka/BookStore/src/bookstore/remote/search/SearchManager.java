/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import bookstore.local.search.SearchRequest;
import java.util.HashMap;

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
                
        public SearchResult(String title){
            found = false;
            price = 0;
            count = 0;
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
                    SearchResult defRes = new SearchResult(r.getSearchedTitle());
                    actualSearchResults.put(r.getUUID(), defRes);
                    
                    getContext().actorSelection("DB1Searcher").tell(r, getSelf());
                    getContext().actorSelection("DB2Searcher").tell(r, getSelf());
                })
                .match(DBSearchResult.class, r -> {
                        System.out.println("Got DBRes");
                        SearchResult actualRes = actualSearchResults.get(r.getUUID());
                        actualRes.updateResult(r.titleFound(), r.getPrice());
                        if(actualRes.count == actualRes.expectedReports){
                            System.out.println("Ready to send reply");
                            if(actualRes.found){
                                PriceOfBookAnswer answer = new PriceOfBookAnswer(actualRes.title, actualRes.price);
                                getContext().actorSelection(answer.getRemotePath()).tell(answer, getSelf());
                            }
                            else{
                                TitleNotFoundAnswer notFoundAnswer = new TitleNotFoundAnswer(actualRes.title);
                                getContext().actorSelection(notFoundAnswer.getRemotePath()).tell(notFoundAnswer, getSelf());                                
                            }
                        }
                })
                .build();
    }
    
    
    @Override
    public void preStart(){
        getContext().actorOf(new RoundRobinPool(5).props(Props.create(DBSearcher.class, () -> new DBSearcher("DB1.txt"))), "DB1Searcher");
        getContext().actorOf(new RoundRobinPool(5).props(Props.create(DBSearcher.class, () -> new DBSearcher("DB2.txt"))), "DB2Searcher");
    }
}
