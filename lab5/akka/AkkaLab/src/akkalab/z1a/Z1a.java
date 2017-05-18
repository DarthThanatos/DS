package akkalab.z1a;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Z1a {

    public static void main(String[] args) throws Exception {

        // create actor system & actors
        final ActorSystem system = ActorSystem.create("local_system");
        final ActorRef actor = system.actorOf(Props.create(Z1a_MathActor.class), "math");
        System.out.println("Z1a Started. Commands: 'hi', 'm [nb1] [nb2]', 'd [nb1] [nb2]', 'q'");
        
        // read line & send to actor
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
            actor.tell(line, null);     // send message to actor
        }

        // finish
        system.terminate();
    }
    
}