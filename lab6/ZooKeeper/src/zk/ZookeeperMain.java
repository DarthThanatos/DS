package zk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

public class ZookeeperMain{
   private static ZooKeeper zk;
   private static ZooKeeperConnection conn;
   

   public static void printTree(String parent, int indent) throws Exception{
	   String path = parent;
       List <String> children = zk.getChildren(path, true);
       for(int i = 0; i < children.size(); i++){
    	    for( int j = 0; j < indent; j++) System.out.print("| ");
       		System.out.println(children.get(i)); //Print children's
       		printTree(path + "/" + children.get(i), indent+1);
       }
	   
   }
   
   public static void main(String[] args) throws InterruptedException,KeeperException {
      String path = "/znode_tmp"; // Assign path to the znode
      if(args.length == 0){
    	  System.out.println("Usage: java ZKExists code_to_execute");
    	  System.exit(-1);
      }
		
      try {
         conn = new ZooKeeperConnection();
         zk = conn.connect("localhost");
         final CountDownLatch connectedSignal = new CountDownLatch(1);
         
         Stat stat =  zk.exists(path,new NodeWatcher(connectedSignal, args[0]) );

         if(stat!= null) {
        	 zk.getChildren(path,new ChildrenWatcher(connectedSignal, path, zk), null);
         } else {
            System.out.println("Node " + path + " does not exists");
         }
         
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String line = "";
         while (true){
   	      System.out.println("Type q to exit, type t to show the tree");
   	      line =  br.readLine();
   	      if (line.equals("q")) break;
   	      if(line.equals("t")) {
   	    	  System.out.println(path);
   	    	  printTree(path,1);
   	      }
         }
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }

   }

}
