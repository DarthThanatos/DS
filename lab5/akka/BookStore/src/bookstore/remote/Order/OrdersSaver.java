/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.Order;

import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.restart;
import akka.japi.pf.DeciderBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Robert
 */
public class OrdersSaver {
    private File ordersFile;
    private BufferedWriter bw;
    
    public OrdersSaver(){
        ordersFile = new File("orders.txt");
    }
    
    public synchronized void save(String title) throws FileNotFoundException, IOException{
        System.out.println("OrdersSaver saving " + title);
        FileWriter fw = new FileWriter(ordersFile,true);
        bw = new BufferedWriter(fw);
        bw.write(title + "\n");
        System.out.println("Written " + title + " to the file orders.txt");
        bw.close();
    }
    
}
