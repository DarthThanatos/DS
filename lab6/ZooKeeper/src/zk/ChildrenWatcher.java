package zk;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;

public class ChildrenWatcher implements Watcher {
	Process p = null;
	CountDownLatch connectedSignal;
	String path;
	ZooKeeper zk;
	
	public ChildrenWatcher(CountDownLatch connectedSignal, String path, ZooKeeper zk){
		this.path = path;
		this.connectedSignal = connectedSignal;
		this.zk = zk;
	}
	 

   private int countDescendants(String parent) throws KeeperException, InterruptedException{
	   String path = parent;
	   int lvl_counter = 0;
       List <String> children = zk.getChildren(path, true);
       for(int i = 0; i < children.size(); i++){
       		lvl_counter += countDescendants(path + "/" + children.get(i));
       }
       return lvl_counter + children.size();
   }
	
    public void process(WatchedEvent we) {
		System.out.println("Got event: " + we.getType());
       if (we.getType() == Event.EventType.None) {
          switch(we.getState()) {
             case Expired:
             connectedSignal.countDown();
             break;
          }
					
       } 
       else if(we.getType() == Event.EventType.NodeChildrenChanged){
           try {
           	
          	 	System.out.println("Descendants amount: " + countDescendants(path));
             } catch(Exception ex) {
                System.out.println(ex.getMessage());
             }
       }
    }
 }
