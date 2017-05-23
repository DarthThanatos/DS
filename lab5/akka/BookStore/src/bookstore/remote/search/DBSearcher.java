/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import akka.actor.AbstractActor;
import akka.actor.AllForOneStrategy;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import akka.japi.pf.DeciderBuilder;
import bookstore.local.search.SearchRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Robert
 */
public class DBSearcher extends AbstractActor{

    private final String databaseName;
    
    public DBSearcher(String databaseName){
        this.databaseName = databaseName;
    }
    
    class TitleNotFoundException extends Exception{
        public TitleNotFoundException(String msg){
            super(msg);
        }
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchTriggerMsg.class, r -> {
                    try{
                        int price = searchDB(r.getSearchedTitle());
                        System.out.println("Found title: " + r.getSearchedTitle() + "; its price: " + price);
                        getSender().tell(new DBSearchResult(databaseName, r.getUUID(), r.getSearchedTitle(), true, price), getSelf());
                    }catch(TitleNotFoundException e){
                        System.out.println(e.getMessage());
                        getSender().tell(new DBSearchResult(databaseName, r.getUUID(), r.getSearchedTitle(), false, 0), getSelf());
                    }
                })
                .build();
    }
    
    
        private static SupervisorStrategy ofos = 
            new AllForOneStrategy(
                    -1,
                    Duration.Inf(),
                    DeciderBuilder
                            .match(FileNotFoundException.class, e ->  resume())
                            .match(Exception.class, e -> escalate())
                            .build()
            );
    @Override
    public SupervisorStrategy supervisorStrategy(){
        return ofos;
    }
    
    private int searchDB(String searchedTitle) throws FileNotFoundException, IOException, TitleNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(new File(databaseName)));
        String entry;
        while((entry = br.readLine())!= null){
            String title = entry.split(" ")[0];
            int price = Integer.parseInt(entry.split(" ")[1]);
            if (title.equals(searchedTitle)){
                br.close();
                return price;
            }
        }
        throw new TitleNotFoundException(searchedTitle + " was not found in database: " + databaseName);
    }
}
