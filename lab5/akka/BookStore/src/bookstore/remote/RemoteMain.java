/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 * @author Robert
 */
public class RemoteMain {
    public static void main(String[] args) throws Exception {

        // config
        File configFile = new File("remote_app2.conf");
        Config config = ConfigFactory.parseFile(configFile);
        
        // create actor system & actors
        final ActorSystem system = ActorSystem.create("remote_system", config);
        system.actorOf(Props.create(RemoteMainActor.class), "remote");

        System.out.println("Started remote bookstore system");
        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type sth to demolish bookstore");
        System.out.println(">");
        br.readLine();

        system.terminate();
    }   
}
