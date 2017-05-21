/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
    
    public synchronized void save(String title){
        System.out.println("OrdersSaver saving " + title);
        try{
            FileWriter fw = new FileWriter(ordersFile,true);
            bw = new BufferedWriter(fw);
            bw.write(title + "\n");
            System.out.println("Written " + title + " to the file orders.txt");
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
