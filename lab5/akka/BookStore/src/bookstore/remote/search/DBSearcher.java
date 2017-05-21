/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import akka.actor.AbstractActor;
import bookstore.local.search.SearchRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
                .match(SearchRequest.class, r -> {
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
    
    
    
    private int searchDB(String searchedTitle) throws Exception{
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
