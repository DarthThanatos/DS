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
   public  static final String ROOT =  "/znode_tmp";

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
      if(args.length == 0){
    	  System.out.println("Usage: java ZKExists code_to_execute");
    	  System.exit(-1);
      }

      ProcessHandle p = new ProcessHandle();
      try {
         conn = new ZooKeeperConnection();
         zk = conn.connect("localhost");
         final CountDownLatch connectedSignal = new CountDownLatch(1);
         zk.exists(ROOT,new NodeWatcher(connectedSignal, ROOT, zk, args[0], p) );
         
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String line = "";
         while (true){
   	      System.out.println("Type q to exit, type t to show the tree");
   	      line =  br.readLine();
   	      if (line.equals("q")) break;
   	      if(line.equals("t")) {
   	    	  System.out.println(ROOT);
   	    	  printTree(ROOT, 1);
   	      }
         }
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }
      finally{
          p.endProcess();
      }

   }

}
