/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local;

import bookstore.local.search.SearchRequest;
import bookstore.local.order.OrderRequest;
import bookstore.local.stream.StreamRequest;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Robert
 */
public class LocalMain {
        
    public static void main(String [] args) throws Exception{
        File configFile = new File("remote_app.conf");
        Config config = ConfigFactory.parseFile(configFile);
        
        // create actor system & actors        
        final ActorSystem system = ActorSystem.create("local_system", config);
        final ActorRef local = system.actorOf(Props.create(LocalMainActor.class), "local");
        
        System.out.println("Started z2 local with path: " + local.path());
        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean shouldContinue = true;
        while (shouldContinue) {
            for (Options option : Options.values()){
                System.out.println("-> " + option);
            }
            System.out.println("Pick one");
            System.out.println("Local client>");
            String line = br.readLine();
            Options pickedOption;
            try{
                 pickedOption = Options.valueOf(line.toUpperCase());
            }catch(IllegalArgumentException e){
                System.out.println("Not a valid option, press enter to continue");
                br.readLine();
                continue;
            }
            switch(pickedOption){
                case STREAM:
                    sendStreamRq(local, br);
                    break;
                case ORDER:
                    sendOrderRq(local, br);
                    break;
                case SEARCH:
                    sendSearchRq(local, br);
                    break;
                case QUIT:
                    shouldContinue = false;
                    break;
                
            }
            //local.tell(line, null);
        }
        system.terminate();
        
    }
    
    private static void sendStreamRq(ActorRef local, BufferedReader br) throws Exception{
        System.out.println("Type the title u want to stream");
        String title = br.readLine();
        local.tell(new StreamRequest(title), null);
        
    }
    
    private static void sendSearchRq(ActorRef local, BufferedReader br) throws Exception{
        System.out.println("Type the title u want to check");
        String title = br.readLine();
        local.tell(new SearchRequest(title), null);
    }
    
    private static void sendOrderRq(ActorRef local, BufferedReader br) throws Exception{
        System.out.println("Type the title u want to order");
        String title = br.readLine();
        local.tell(new OrderRequest(title), null);
    }
}

enum Options {
    STREAM, SEARCH, ORDER, QUIT
}
