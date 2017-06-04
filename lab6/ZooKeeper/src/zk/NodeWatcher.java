package zk;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class NodeWatcher implements Watcher{
	ProcessHandle p;
	CountDownLatch connectedSignal;
	String cmd;
	ZooKeeper zk;
	String path;
	
	//Cares only about znode_tmp
	public NodeWatcher(CountDownLatch connectedSignal, String path, ZooKeeper zk, String cmd, ProcessHandle p){
		this.cmd = cmd;
		this.connectedSignal = connectedSignal;
		this.zk = zk;
		this.path = path;
		this.p = p;
		try{
			 Stat stat = zk.exists(path, false);
	         if(stat!= null) { //has to check, otherwise ensemble may return KeeperErrorCode = NoNode
	        	 zk.getChildren(path,new ChildrenWatcher(connectedSignal, path, zk), null);
	         } else {
	            System.out.println("Node " + path + " does not exists");
	         }
		}catch(Exception e){
			e.printStackTrace();
		}
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
       else if(we.getType() == Event.EventType.NodeCreated) {
			
           try {
        	  System.out.println("Creating window");
        	  p.createProcess(cmd);
        	  zk.exists(path,new NodeWatcher(connectedSignal, path, zk, cmd, p) );
           } catch(Exception ex) {
              System.out.println(ex.getMessage());
           }
       }
       else if(we.getType() == Event.EventType.NodeDeleted) {
           try {
        	   if(p!= null){
        		   System.out.println("Deleting window");
        		   p.endProcess();
        	   }
         	  zk.exists(path,new NodeWatcher(connectedSignal, path, zk, cmd, p) );
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
       }
   }
}

